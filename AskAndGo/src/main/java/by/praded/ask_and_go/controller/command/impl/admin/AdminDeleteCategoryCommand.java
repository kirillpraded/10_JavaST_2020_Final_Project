package by.praded.ask_and_go.controller.command.impl.admin;

import by.praded.ask_and_go.controller.command.Command;
import by.praded.ask_and_go.controller.util.Attribute;
import by.praded.ask_and_go.dao.exception.ConnectionPoolException;
import by.praded.ask_and_go.dao.exception.DaoException;
import by.praded.ask_and_go.service.CategoryService;
import by.praded.ask_and_go.service.Service;
import by.praded.ask_and_go.service.impl.ServiceProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Kiryl Praded
 * 24.12.2020
 * <p>
 * Command serves to delete category.
 * @see Command
 */
public class AdminDeleteCategoryCommand implements Command {
    /**
     * Logger for event logging.
     */
    private static final Logger logger = LogManager.getLogger(AdminDeleteCategoryCommand.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        try {
            Long id = Long.parseLong(request.getParameter(Attribute.CATEGORY_ID));

            CategoryService categoryService = ServiceProvider.getInstance().takeService(Service.CATEGORY);
            categoryService.delete(id);

            logger.info(String.format("Category id=%d successfully deleted", id));
            request.setAttribute(Attribute.CATEGORY_SUCCESS, "category.delete-success");
        } catch (ConnectionPoolException | DaoException e) {
            logger.error("It's impossible to process request", e);
            request.setAttribute(Attribute.CATEGORY_ERROR, "database.error");
        }
        request.getRequestDispatcher("/app/admin").forward(request, response);
    }

}
