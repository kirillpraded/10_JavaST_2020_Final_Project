package by.praded.ask_and_go.controller.command.impl.writer;

import by.praded.ask_and_go.controller.command.Command;
import by.praded.ask_and_go.controller.util.Attribute;
import by.praded.ask_and_go.dao.exception.ConnectionPoolException;
import by.praded.ask_and_go.dao.exception.DaoException;
import by.praded.ask_and_go.entity.Question;
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

/**
 * @author Kiryl Praded
 * 10.01.2021
 * <p>
 * Command serves to process the display request the question edit page.
 * @see Command
 */
public class ShowQuestionEditFormCommand implements Command {
    /**
     * Logger for event logging.
     */
    private static final Logger logger = LogManager.getLogger(ShowQuestionEditFormCommand.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            Long questionId = Long.parseLong(request.getParameter(Attribute.QUESTION_ID));
            QuestionService questionService = ServiceProvider.getInstance().takeService(Service.QUESTION);
            Question question = questionService.findQuestionById(questionId, 1);

            request.setAttribute(Attribute.QUESTION, question);
            request.getRequestDispatcher("/WEB-INF/jsp/question-edit.jsp").forward(request, response);
        } catch (ConnectionPoolException | DaoException e) {
            logger.error("It's impossible to process request", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "error-page.server");
        } catch (EntityNotExistsException | NumberFormatException e) {
            logger.debug("Page not found", e);
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "error-page.not-found");
        }
    }
}
