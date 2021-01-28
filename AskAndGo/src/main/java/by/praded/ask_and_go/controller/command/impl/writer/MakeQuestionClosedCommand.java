package by.praded.ask_and_go.controller.command.impl.writer;

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
 * 08.01.2021
 * <p>
 * Command serves to close question.
 * @see Command
 */
public class MakeQuestionClosedCommand implements Command {
    /**
     * Logger for event logging.
     */
    private static final Logger logger = LogManager.getLogger(MakeQuestionClosedCommand.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String questionId = request.getParameter(Attribute.QUESTION_ID);
        try {
            Long questionIdLong = Long.parseLong(questionId);
            QuestionService questionService = ServiceProvider.getInstance().takeService(Service.QUESTION);
            questionService.updateQuestionIsClosed(questionIdLong);
            logger.info(String.format("Question[%s] is closed.", questionId));
            response.sendRedirect(request.getContextPath() + "/question?question_id=" + questionId);
        } catch (ConnectionPoolException | DaoException e) {
            logger.error("It's impossible to process request", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "error-page.server");
        }
    }
}
