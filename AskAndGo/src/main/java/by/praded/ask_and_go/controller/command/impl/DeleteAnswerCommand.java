package by.praded.ask_and_go.controller.command.impl;

import by.praded.ask_and_go.controller.command.Command;
import by.praded.ask_and_go.controller.util.Attribute;
import by.praded.ask_and_go.dao.exception.ConnectionPoolException;
import by.praded.ask_and_go.dao.exception.DaoException;
import by.praded.ask_and_go.service.AnswerService;
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
 * 10.01.2021
 *
 * Command serves to deleting an answer.
 * @see Command
 */
public class DeleteAnswerCommand implements Command {
    /**
     * Logger for event logging.
     */
    private static final Logger logger = LogManager.getLogger(DeleteAnswerCommand.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String answerId = request.getParameter(Attribute.ANSWER_ID);
        try {
            Long answerIdLong = Long.parseLong(answerId);

            AnswerService answerService = ServiceProvider.getInstance().takeService(Service.ANSWER);

            answerService.deleteAnswer(answerIdLong);
            logger.info(String.format("Answer[%s] deleted.", answerId));

            response.sendRedirect(request.getContextPath()
                    + "/app/question?question_id="
                    + request.getParameter(Attribute.QUESTION_ID)
                    + "&page="
                    + request.getParameter(Attribute.PAGE));

        } catch (ConnectionPoolException | DaoException e) {
            logger.error("It's impossible to process request", e);
            request.setAttribute(Attribute.MESSAGE, "database.error");
            request.getRequestDispatcher("/app/question").forward(request, response);
        }
    }
}
