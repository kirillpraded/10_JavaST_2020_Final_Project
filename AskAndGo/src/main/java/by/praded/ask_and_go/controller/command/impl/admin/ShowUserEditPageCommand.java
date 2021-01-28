package by.praded.ask_and_go.controller.command.impl.admin;

import by.praded.ask_and_go.controller.command.Command;
import by.praded.ask_and_go.controller.util.Attribute;
import by.praded.ask_and_go.dao.exception.ConnectionPoolException;
import by.praded.ask_and_go.dao.exception.DaoException;
import by.praded.ask_and_go.entity.User;
import by.praded.ask_and_go.service.Service;
import by.praded.ask_and_go.service.UserService;
import by.praded.ask_and_go.service.exception.EntityNotExistsException;
import by.praded.ask_and_go.service.impl.ServiceProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Kiryl Praded
 * 31.12.2020
 * <p>
 * Command serves to process the display request the user edit page.
 * @see Command
 */
public class ShowUserEditPageCommand implements Command {
    /**
     * Logger for event logging.
     */
    private static final Logger logger = LogManager.getLogger(ShowUserEditPageCommand.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            Long id = Long.parseLong(request.getParameter(Attribute.ID));
            UserService userService = ServiceProvider.getInstance().takeService(Service.USER);
            User user = userService.findUserById(id);

            request.setAttribute(Attribute.USER, user);
            request.getRequestDispatcher("/WEB-INF/jsp/user-edit.jsp").forward(request, response);
        } catch (EntityNotExistsException | NumberFormatException e) {
            logger.debug("Page not found", e);
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "error-page.not-found");
        } catch (ConnectionPoolException | DaoException e) {
            logger.error("It's impossible to process request", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "error-page.server");
        }
    }
}
