package by.praded.ask_and_go.controller.command.impl;

import by.praded.ask_and_go.controller.command.Command;
import by.praded.ask_and_go.dao.exception.ConnectionPoolException;
import by.praded.ask_and_go.dao.exception.DaoException;
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
import java.util.Objects;

/**
 * @author Kiryl Praded
 * 12.01.2021
 * <p>
 * Command serves to process the display user page request.
 * @see Command
 */
public class ShowUserCommand implements Command {
    /**
     * Logger for event logging.
     */
    private static final Logger logger = LogManager.getLogger(ShowUserCommand.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String userId = request.getParameter("user_id");
        if (Objects.nonNull(userId) && !userId.isEmpty()) {
            try {
                Long userIdLong = Long.parseLong(userId);
                UserService userService = ServiceProvider.getInstance().takeService(Service.USER);

                request.setAttribute("user", userService.findUserById(userIdLong));
                request.getRequestDispatcher("/WEB-INF/jsp/user-profile.jsp").forward(request, response);
            } catch (NumberFormatException | EntityNotExistsException e) {
                logger.debug("Page not found", e);
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "error-page.not-found");
            } catch (ConnectionPoolException | DaoException e) {
                logger.error("It's impossible to process request", e);
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "error-page.server");
            }
        } else {
            logger.debug("Page not found");
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "error-page.not-found");
        }
    }
}
