package by.praded.ask_and_go.controller.command.impl.moderator;

import by.praded.ask_and_go.controller.command.Command;
import by.praded.ask_and_go.controller.util.Attribute;
import by.praded.ask_and_go.dao.exception.DaoException;
import by.praded.ask_and_go.entity.Role;
import by.praded.ask_and_go.entity.User;
import by.praded.ask_and_go.dao.exception.ConnectionPoolException;
import by.praded.ask_and_go.service.Service;
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
 * 10.01.2021
 *
 * Command serves to block user. Blocking means setting role `READER`.
 * @see Command
 */
public class BlockAuthorCommand implements Command {
    /**
     * Logger for event logging.
     */
    private static final Logger logger = LogManager.getLogger(BlockAuthorCommand.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String userId = request.getParameter(Attribute.USER_ID);
        try {
            User user = new User();
            user.setId(Long.parseLong(userId));
            user.setRole(Role.READER);
            UserService userService =  ServiceProvider.getInstance().takeService(Service.USER);

            userService.updateUserRole(user);
            logger.info(String.format("User[%d] successfully blocked.", user.getId()));
            response.sendRedirect(request.getContextPath()
                    + "/question?question_id="
                    + request.getParameter(Attribute.QUESTION_ID));
        } catch (ConnectionPoolException | DaoException e) {
            logger.error("It's impossible to process request", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "error-page.server");
        }
    }
}
