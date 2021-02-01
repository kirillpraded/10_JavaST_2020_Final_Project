package by.praded.ask_and_go.controller.command.impl.admin;

import by.praded.ask_and_go.controller.command.Command;
import by.praded.ask_and_go.controller.util.Attribute;
import by.praded.ask_and_go.dao.exception.ConnectionPoolException;
import by.praded.ask_and_go.dao.exception.DaoException;
import by.praded.ask_and_go.entity.Role;
import by.praded.ask_and_go.entity.User;
import by.praded.ask_and_go.service.Service;
import by.praded.ask_and_go.service.UserService;
import by.praded.ask_and_go.service.impl.ServiceProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Kiryl Praded
 * 20.12.2020
 * <p>
 * Command serves to update user's role.
 * @see Command
 */
public class UpdateUserRoleCommand implements Command {
    /**
     * Logger for event logging.
     */
    private static final Logger logger = LogManager.getLogger(UpdateUserRoleCommand.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        try {
            User user = new User();
            user.setId(Long.parseLong(request.getParameter(Attribute.ID)));
            user.setRole(Role.values()[Integer.parseInt(request.getParameter(Attribute.ROLE))]);
            UserService service = ServiceProvider.getInstance().takeService(Service.USER);

            service.updateUserRole(user);

            logger.info(String.format("User[%d] role[%d] successfully updated.",
                    user.getId(),
                    user.getRole().getIdentity()));

            request.setAttribute(Attribute.MSG_USER_SUCCESS, "user.role-update.success");
        } catch (ConnectionPoolException | DaoException e) {
            logger.error("It's impossible to process request", e);
            request.setAttribute(Attribute.MSG_USER_ERROR, "database.error");
        }
        request.getRequestDispatcher("/app/admin").forward(request, response);
    }

}
