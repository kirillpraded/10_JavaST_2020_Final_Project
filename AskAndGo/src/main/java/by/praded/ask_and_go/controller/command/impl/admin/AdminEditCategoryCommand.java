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
 * Command serves to edit category.
 * @see Command
 */
public class AdminEditCategoryCommand implements Command {
    /**
     * Logger for event logging.
     */
    private static final Logger logger = LogManager.getLogger(AdminEditCategoryCommand.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            Long id = Long.parseLong(request.getParameter(Attribute.CATEGORY_ID));

            CategoryService service = ServiceProvider.getInstance().takeService(Service.CATEGORY);

            Category category = new Category();
            category.setId(id);
            category.setName(request.getParameter(Attribute.CATEGORY_NAME)
                    .replaceAll("<","&lt;")
                    .replaceAll(">", "&gt;"));


            if (!request.getParameter(Attribute.PARENT_ID).isEmpty()) {
                Category parent = new Category();
                parent.setId(Long.parseLong(request.getParameter(Attribute.PARENT_ID)));
                category.setParent(parent);
                //попытка сделать категорию своим же наследником недопустима
                if (!parent.getId().equals(category.getId())) {
                    service.updateCategory(category);
                    request.setAttribute(Attribute.CATEGORY_SUCCESS, "category.update-success");
                } else {
                    request.setAttribute(Attribute.CATEGORY_ERROR, "category.update-error");
                }
            } else {
                service.updateCategory(category);
                logger.info(String.format("Category[%d] updated.", category.getId()));
            }

        } catch (ConnectionPoolException | DaoException e) {
            logger.error("It's impossible to process request", e);
            request.setAttribute(Attribute.CATEGORY_ERROR, "database.error");
        } catch (ValidationException e) {
            e.getAttributes().forEach(request::setAttribute);
        }
        request.getRequestDispatcher("/app/admin").forward(request, response);
    }


}
