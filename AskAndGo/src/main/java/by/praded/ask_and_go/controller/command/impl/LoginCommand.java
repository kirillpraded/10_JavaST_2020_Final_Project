package by.praded.ask_and_go.controller.command.impl;

import by.praded.ask_and_go.controller.command.Command;
import by.praded.ask_and_go.controller.util.Attribute;
import by.praded.ask_and_go.dao.exception.ConnectionPoolException;
import by.praded.ask_and_go.dao.exception.DaoException;
import by.praded.ask_and_go.entity.User;
import by.praded.ask_and_go.service.Service;
import by.praded.ask_and_go.service.impl.ServiceProvider;
import by.praded.ask_and_go.service.UserService;
import by.praded.ask_and_go.service.exception.BadCredentialsException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

/**
 * @author Kiryl Praded
 * 17.12.2020
 * <p>
 * Command serves for user authentication.
 */
public class LoginCommand implements Command {
    /**
     * Logger for event logging.
     */
    private static final Logger logger = LogManager.getLogger(LoginCommand.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter(Attribute.USERNAME);
        String password = request.getParameter(Attribute.PASSWORD);

        ServiceProvider factory = ServiceProvider.getInstance();
        UserService userService = factory.takeService(Service.USER);
        try {
            User user = userService.authenticate(username, password);

            HttpSession session = request.getSession();
            session.setAttribute(Attribute.AUTH_USER, user);

            logger.info(String.format("User[%d] successfully signed in.", user.getId()));
            if (Objects.nonNull(session.getAttribute("referer"))) {
                response.sendRedirect((String) session.getAttribute("referer"));
            } else {
                response.sendRedirect(request.getContextPath() + "/categories");
            }
        } catch (BadCredentialsException e) {
            logger.info(String.format("%s tried to sign in with wrong password", username));
            forwardBack(request, response, username, "password.wrong");
        } catch (ConnectionPoolException | DaoException e) {
            logger.error("It's impossible to process request", e);
            forwardBack(request, response, username, "database.error");
        }
    }

    private void forwardBack(HttpServletRequest request, HttpServletResponse response,
                             String username, String message) throws ServletException, IOException {
        request.setAttribute(Attribute.MESSAGE, message);
        request.setAttribute(Attribute.USERNAME, username);
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
    }

}
