package by.praded.ask_and_go.controller.command.impl.admin;

import by.praded.ask_and_go.controller.command.Command;
import by.praded.ask_and_go.controller.util.Attribute;
import by.praded.ask_and_go.dao.exception.ConnectionPoolException;
import by.praded.ask_and_go.dao.exception.DaoException;
import by.praded.ask_and_go.entity.Category;
import by.praded.ask_and_go.service.CategoryService;
import by.praded.ask_and_go.service.Service;
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
 * 24.12.2020
 * <p>
 * Command serves to add category to database.
 * @see Command
 */
public class AdminAddCategoryCommand implements Command {
    /**
     * Logger for event logging.
     */
    private static final Logger logger = LogManager.getLogger(AdminAddCategoryCommand.class);


    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String categoryParentId = request.getParameter(Attribute.PARENT_ID);
        String categoryName = request.getParameter(Attribute.NAME)
                .replaceAll("<","&lt;")
                .replaceAll(">", "&gt;");
        try {
            Category category = new Category();
            category.setName(categoryName);

            if (!categoryParentId.isEmpty()) {
                Category parent = new Category();
                parent.setId(Long.parseLong(categoryParentId));
                category.setParent(parent);
            }

            CategoryService categoryService = ServiceProvider.getInstance().takeService(Service.CATEGORY);

            categoryService.createCategory(category);
            logger.info(String.format("Category successfully created \"%s\"", categoryName));
            request.setAttribute(Attribute.CATEGORY_SUCCESS, "category.add-success");

        } catch (ConnectionPoolException e) {
            logger.error("It's impossible to process request", e);
            putErrorAttributes(request, categoryName, "database.error", categoryParentId);
        } catch (DaoException e) {
            logger.info(String.format("Trying to add existing category %s, parent:%s", categoryName, categoryParentId));
            putErrorAttributes(request, categoryName, "category.exists", categoryParentId);
        } catch (ValidationException e) {
            logger.debug(String.format("Category name isn't valid: %s", categoryName));
            request.setAttribute(Attribute.CATEGORY_NAME, categoryName);
            request.setAttribute(Attribute.PARENT_IDENTITY, categoryParentId);
            e.getAttributes().forEach(request::setAttribute);
        }
        request.getRequestDispatcher("/admin").forward(request, response);

    }

    private void putErrorAttributes(HttpServletRequest request, String categoryName,
                                    String error, String categoryId) {
        request.setAttribute(Attribute.CATEGORY_ERROR, error);
        request.setAttribute(Attribute.CATEGORY_NAME, categoryName);
        request.setAttribute(Attribute.PARENT_IDENTITY, categoryId);
    }
}
