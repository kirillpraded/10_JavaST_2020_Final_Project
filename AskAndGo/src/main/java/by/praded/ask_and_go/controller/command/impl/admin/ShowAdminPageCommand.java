package by.praded.ask_and_go.controller.command.impl.admin;

import by.praded.ask_and_go.controller.command.Command;
import by.praded.ask_and_go.controller.util.Attribute;
import by.praded.ask_and_go.dao.exception.ConnectionPoolException;
import by.praded.ask_and_go.dao.exception.DaoException;
import by.praded.ask_and_go.entity.Category;
import by.praded.ask_and_go.entity.User;
import by.praded.ask_and_go.service.CategoryService;
import by.praded.ask_and_go.service.Service;
import by.praded.ask_and_go.service.impl.ServiceProvider;
import by.praded.ask_and_go.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 * @author Kiryl Praded
 * 20.12.2020
 * <p>
 * Command serves for admin tools displaying.
 * @see Command
 */
public class ShowAdminPageCommand implements Command {
    /**
     * Logger for event logging.
     */
    private static final Logger logger = LogManager.getLogger(ShowAdminPageCommand.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        ServiceProvider factory = ServiceProvider.getInstance();
        CategoryService categoryService = factory.takeService(Service.CATEGORY);
        UserService userService = factory.takeService(Service.USER);
        try {
            List<User> users = userService.findAllUsers();
            request.setAttribute(Attribute.ADMIN_USERS, users);
            List<Category> categories = categoryService.findAllCategories();
            request.setAttribute(Attribute.ADMIN_CATEGORIES, categories);
            request.getRequestDispatcher("/WEB-INF/jsp/admin.jsp").forward(request, response);
        } catch (ConnectionPoolException | DaoException e) {
            logger.error("It's impossible to process request", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "error-page.server");
        }

    }

}
