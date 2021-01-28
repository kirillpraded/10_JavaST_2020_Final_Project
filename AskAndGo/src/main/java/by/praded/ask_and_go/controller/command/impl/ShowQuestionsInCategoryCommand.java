package by.praded.ask_and_go.controller.command.impl;

import by.praded.ask_and_go.controller.command.Command;
import by.praded.ask_and_go.controller.util.Attribute;
import by.praded.ask_and_go.dao.exception.ConnectionPoolException;
import by.praded.ask_and_go.dao.exception.DaoException;
import by.praded.ask_and_go.entity.Category;
import by.praded.ask_and_go.entity.Question;
import by.praded.ask_and_go.service.CategoryService;
import by.praded.ask_and_go.service.QuestionService;
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
 * 27.12.2020
 * <p>
 * Command serves to process the display questions in category request.
 * @see Command
 */
public class ShowQuestionsInCategoryCommand implements Command {
    /**
     * Logger for event logging.
     */
    private static final Logger logger = LogManager.getLogger(ShowQuestionsInCategoryCommand.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String categoryId = request.getParameter(Attribute.CATEGORY_ID);
        if (Objects.nonNull(categoryId) && !categoryId.isEmpty()) {
            try {
                Long categoryIdLong = Long.parseLong(categoryId);

                CategoryService cService = ServiceProvider.getInstance().takeService(Service.CATEGORY);
                Category category = cService.findCategoryById(categoryIdLong);

                QuestionService qService = ServiceProvider.getInstance().takeService(Service.QUESTION);
                List<Question> questions = qService.findQuestionsByCategoryId(categoryIdLong);

                request.setAttribute(Attribute.CATEGORY, category);
                request.setAttribute(Attribute.QUESTIONS, questions);
                request.getRequestDispatcher("/WEB-INF/jsp/questions.jsp").forward(request, response);
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
