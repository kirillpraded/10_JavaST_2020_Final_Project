package by.praded.ask_and_go.controller.command.impl;

import by.praded.ask_and_go.controller.command.Command;
import by.praded.ask_and_go.controller.util.Attribute;
import by.praded.ask_and_go.dao.exception.ConnectionPoolException;
import by.praded.ask_and_go.dao.exception.DaoException;
import by.praded.ask_and_go.entity.User;
import by.praded.ask_and_go.service.Service;
import by.praded.ask_and_go.service.UserService;
import by.praded.ask_and_go.service.exception.BadCredentialsException;
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
 * 12.01.2021
 * <p>
 * Command serves to change user's password.
 * @see Command
 */
public class ChangePasswordCommand implements Command {
    /**
     * Logger for event logging.
     */
    private static final Logger logger = LogManager.getLogger(ChangePasswordCommand.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String oldPassword = request.getParameter(Attribute.OLD_PASSWORD);
        String newPassword = request.getParameter(Attribute.NEW_PASSWORD);
        String newPasswordConfirmation = request.getParameter(Attribute.PASSWORD_CONFIRM);
        User user = (User) request.getSession().getAttribute(Attribute.AUTH_USER);

        try {
            user.setPassword(oldPassword);
            UserService userService = ServiceProvider.getInstance().takeService(Service.USER);
            userService.changePassword(user, newPassword, newPasswordConfirmation);
            logger.info(String.format("User[%d] changed password", user.getId()));
            response.sendRedirect(request.getContextPath() + "/user?user_id=" + user.getId());
        } catch (BadCredentialsException e) {
            logger.info(String.format("User[%d] entered wrong password.", user.getId()));
            forwardBack(request, response, user, Attribute.PASSWORD_ERROR, "password.wrong");
        } catch (ConnectionPoolException | DaoException e) {
            logger.error("It's impossible to process request", e);
            forwardBack(request, response, user, Attribute.ERROR_MSG, "database.error");
        } catch (ValidationException e) {
            logger.debug("Validation exception");
            e.getAttributes().forEach(request::setAttribute);
            request.setAttribute(Attribute.USER, user);
            request.getRequestDispatcher("/WEB-INF/jsp/personal-edit.jsp").forward(request, response);
        }
    }

    private void forwardBack(HttpServletRequest request, HttpServletResponse response,
                             User user, String attrName, String attrValue) throws ServletException, IOException {
        request.setAttribute(Attribute.USER, user);
        request.setAttribute(attrName, attrValue);
        request.getRequestDispatcher("/WEB-INF/jsp/personal-edit.jsp").forward(request, response);
    }
}
