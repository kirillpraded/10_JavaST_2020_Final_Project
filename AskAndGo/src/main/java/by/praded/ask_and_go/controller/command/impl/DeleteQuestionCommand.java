package by.praded.ask_and_go.controller.command.impl;

import by.praded.ask_and_go.controller.command.Command;
import by.praded.ask_and_go.controller.util.Attribute;
import by.praded.ask_and_go.dao.exception.ConnectionPoolException;
import by.praded.ask_and_go.dao.exception.DaoException;
import by.praded.ask_and_go.service.QuestionService;
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
 * 09.01.2021
 * <p>
 * Command serves to deleting a question.
 * @see Command
 */
public class DeleteQuestionCommand implements Command {
    /**
     * Logger for event logging.
     */
    private static final Logger logger = LogManager.getLogger(DeleteQuestionCommand.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String questionId = request.getParameter(Attribute.QUESTION_ID);
        try {
            Long questionIdLong = Long.parseLong(questionId);

            QuestionService questionService = ServiceProvider.getInstance().takeService(Service.QUESTION);

            questionService.deleteQuestion(questionIdLong);
            logger.info(String.format("Question[%s] deleted.", questionId));
            response.sendRedirect(request.getContextPath() + "/app/categories");
        } catch (ConnectionPoolException | DaoException e) {
            logger.error("It's impossible to process request", e);
            request.setAttribute(Attribute.MESSAGE, "database.error");
            request.getRequestDispatcher("/app/question").forward(request, response);
        }
    }
}
