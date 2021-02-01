package by.praded.ask_and_go.controller.command.impl;

import by.praded.ask_and_go.controller.command.Command;
import by.praded.ask_and_go.controller.util.Attribute;
import by.praded.ask_and_go.dao.exception.ConnectionPoolException;
import by.praded.ask_and_go.dao.exception.DaoException;
import by.praded.ask_and_go.entity.User;
import by.praded.ask_and_go.service.Service;
import by.praded.ask_and_go.service.exception.ValidationException;
import by.praded.ask_and_go.service.impl.ServiceProvider;
import by.praded.ask_and_go.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Kiryl Praded
 * 12.01.2021
 *
 * Command serves to edit user's personal info.
 * @see Command
 */
public class PersonalInfoEditCommand implements Command {
    /**
     * Logger for event logging.
     */
    private static final Logger logger = LogManager.getLogger(PersonalInfoEditCommand.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String email = request.getParameter(Attribute.EMAIL);
        String firstName = request.getParameter(Attribute.FIRST_NAME);
        String lastName = request.getParameter(Attribute.LAST_NAME);
        User authUser = (User) request.getSession().getAttribute(Attribute.AUTH_USER);


        User user = new User();
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(authUser.getUsername());
        user.setId(authUser.getId());

        UserService userService = ServiceProvider.getInstance().takeService(Service.USER);
        try {
            userService.updatePersonalInfo(user);
            logger.info(String.format("User[%d] update personal info.", authUser.getId()));
            authUser.setLastName(lastName);
            authUser.setFirstName(firstName);
            authUser.setEmail(email);
            response.sendRedirect(request.getContextPath() + "/app/user?user_id=" + user.getId());
        } catch (ConnectionPoolException | DaoException e) {
            logger.error("It's impossible to process request", e);
            request.setAttribute(Attribute.USER, user);
            request.setAttribute(Attribute.ERROR_MESSAGE, "database.error");
            request.getRequestDispatcher("/WEB-INF/jsp/personal-edit.jsp").forward(request, response);
        } catch (ValidationException e) {
            e.getAttributes().forEach(request::setAttribute);
            request.setAttribute(Attribute.USER, user);
            request.getRequestDispatcher("/WEB-INF/jsp/personal-edit.jsp").forward(request, response);
        }
    }
}
