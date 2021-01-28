package by.praded.ask_and_go.controller.command.impl.admin;

import by.praded.ask_and_go.controller.command.Command;
import by.praded.ask_and_go.controller.util.Attribute;
import by.praded.ask_and_go.dao.exception.ConnectionPoolException;
import by.praded.ask_and_go.dao.exception.DaoException;
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
 * Command serves to delete user.
 * @see Command
 */
public class UserDeleteCommand implements Command {
    /**
     * Logger for event logging.
     */
    private static final Logger logger = LogManager.getLogger(UserDeleteCommand.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            UserService service = ServiceProvider.getInstance().takeService(Service.USER);
            Long id = Long.parseLong(request.getParameter(Attribute.ID));
            service.deleteUser(id);
            logger.info(String.format("User[%d] successfully deleted.", id));
            request.setAttribute(Attribute.MSG_USER_SUCCESS, "user.delete.success");
        } catch (ConnectionPoolException | DaoException e) {
            logger.error("It's impossible to process request", e);
            request.setAttribute(Attribute.MSG_USER_ERROR, "database.error");
        }
        request.getRequestDispatcher("/admin").forward(request, response);
    }

}
