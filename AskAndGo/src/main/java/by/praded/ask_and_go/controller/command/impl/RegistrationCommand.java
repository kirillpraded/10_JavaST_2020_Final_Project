package by.praded.ask_and_go.controller.command.impl;

import by.praded.ask_and_go.controller.command.Command;
import by.praded.ask_and_go.controller.util.Attribute;
import by.praded.ask_and_go.dao.exception.ConnectionPoolException;
import by.praded.ask_and_go.dao.exception.DaoException;
import by.praded.ask_and_go.service.Service;
import by.praded.ask_and_go.service.UserService;
import by.praded.ask_and_go.service.exception.ValidationException;
import by.praded.ask_and_go.service.impl.ServiceProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Kiryl Praded
 * 19.12.2020
 * <p>
 * Command serves for user registration.
 * @see Command
 */
public class RegistrationCommand implements Command {
    /**
     * Logger for event logging.
     */
    private static final Logger logger = LogManager.getLogger(RegistrationCommand.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter(Attribute.USERNAME);
        String password = request.getParameter(Attribute.PASSWORD);
        String passwordConfirmation = request.getParameter(Attribute.PASSWORD_CONFIRM);
        String email = request.getParameter(Attribute.EMAIL);
        String firstName = request.getParameter(Attribute.FIRST_NAME);
        String lastName = request.getParameter(Attribute.LAST_NAME);

        ServiceProvider factory = ServiceProvider.getInstance();
        UserService userService = factory.takeService(Service.USER);
        try {
            userService.register(username, password, passwordConfirmation, firstName, lastName, email);
            logger.info(String.format("User '%s' successfully registered", username));
            response.sendRedirect(request.getContextPath() + "/login");
        } catch (ConnectionPoolException e) {
            logger.error("It's impossible to process request", e);
            request.setAttribute(Attribute.MESSAGE, "database.error");
            setAttributes(request, username, firstName, lastName, email);
            request.getRequestDispatcher("/WEB-INF/jsp/registration.jsp").forward(request, response);
        } catch (DaoException e) {
            logger.debug(String.format("User '%s' already exists", username));
            request.setAttribute(Attribute.USERNAME_ERROR, "username.exists");
            setAttributes(request, username, firstName, lastName, email);
            request.getRequestDispatcher("/WEB-INF/jsp/registration.jsp").forward(request, response);
        } catch (ValidationException e) {
            e.getAttributes().forEach(request::setAttribute);
            setAttributes(request, username, firstName, lastName, email);
            request.getRequestDispatcher("/WEB-INF/jsp/registration.jsp").forward(request, response);
        }

    }

    private void setAttributes(HttpServletRequest req, String username, String firstName, String lastName, String email) {
        req.setAttribute(Attribute.USERNAME, username);
        req.setAttribute(Attribute.FIRST_NAME, firstName);
        req.setAttribute(Attribute.LAST_NAME, lastName);
        req.setAttribute(Attribute.EMAIL, email);
    }

}
