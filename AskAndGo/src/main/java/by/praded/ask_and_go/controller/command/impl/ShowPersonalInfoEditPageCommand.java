package by.praded.ask_and_go.controller.command.impl;

import by.praded.ask_and_go.controller.command.Command;
import by.praded.ask_and_go.controller.util.Attribute;
import by.praded.ask_and_go.dao.exception.ConnectionPoolException;
import by.praded.ask_and_go.dao.exception.DaoException;
import by.praded.ask_and_go.service.Service;
import by.praded.ask_and_go.service.impl.ServiceProvider;
import by.praded.ask_and_go.service.UserService;
import by.praded.ask_and_go.service.exception.EntityNotExistsException;
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
 *
 * Command serves for displaying personal info editing page.
 * @see Command
 */
public class ShowPersonalInfoEditPageCommand implements Command {
    /**
     * Logger for event logging.
     */
    private static final Logger logger = LogManager.getLogger(ShowPersonalInfoEditPageCommand.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String userId = request.getParameter(Attribute.USER_ID);
        if (Objects.nonNull(userId) && !userId.isEmpty()) {
            try {
                Long userIdLong = Long.parseLong(userId);
                UserService userService = ServiceProvider.getInstance().takeService(Service.USER);

                request.setAttribute(Attribute.USER, userService.findUserById(userIdLong));
                request.getRequestDispatcher("/WEB-INF/jsp/personal-edit.jsp").forward(request, response);
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
