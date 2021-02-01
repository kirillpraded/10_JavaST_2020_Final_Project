package by.praded.ask_and_go.controller.command.impl.writer;

import by.praded.ask_and_go.controller.command.Command;
import by.praded.ask_and_go.controller.util.Attribute;
import by.praded.ask_and_go.dao.exception.ConnectionPoolException;
import by.praded.ask_and_go.dao.exception.DaoException;
import by.praded.ask_and_go.entity.Category;
import by.praded.ask_and_go.service.CategoryService;
import by.praded.ask_and_go.service.Service;
import by.praded.ask_and_go.service.exception.EntityNotExistsException;
import by.praded.ask_and_go.service.impl.ServiceProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * @author Kiryl Praded
 * 31.12.2020
 * <p>
 * Command serves to process the display request the ask page.
 * @see Command
 */
public class ShowAskPageCommand implements Command {
    /**
     * Logger for event logging.
     */
    private static final Logger logger = LogManager.getLogger(ShowAskPageCommand.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            CategoryService categoryService = ServiceProvider.getInstance().takeService(Service.CATEGORY);

            List<Category> categories = categoryService.findAllCategories();
            request.setAttribute(Attribute.CATEGORIES, categories);

            String categoryId = request.getParameter(Attribute.CATEGORY_ID);
            if (Objects.nonNull(categoryId) && !categoryId.isEmpty()) {
                Long categoryIdLong = Long.parseLong(categoryId);
                Category category = categoryService.findCategoryById(categoryIdLong);

                //Если пользователь в параметр категори айди вводит не конечную категорию
                //абьюзит запрет добавления вопроса в не конечную категорию
                // если !category.subcategries.isEmpty() - запретить пользователю добавлять в эту категорию

                if (category.getSubcategories().isEmpty()) {
                    request.setAttribute(Attribute.CURRENT_CATEGORY, category);
                } else {
                    request.setAttribute(Attribute.ERROR_CATEGORY_MESSAGE, "category.error");
                }
            }
            request.getRequestDispatcher("/WEB-INF/jsp/ask-form.jsp").forward(request, response);
        } catch (EntityNotExistsException | NumberFormatException e) {
            logger.debug("Page not found", e);
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "error-page.not-found");
        } catch (ConnectionPoolException | DaoException e) {
            logger.error("It's impossible to process request", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "error-page.server");
        }
    }
}
