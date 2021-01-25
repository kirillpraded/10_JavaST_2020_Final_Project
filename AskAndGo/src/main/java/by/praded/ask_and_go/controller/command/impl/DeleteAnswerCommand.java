package by.praded.ask_and_go.controller.command.impl;

import by.praded.ask_and_go.controller.command.Command;
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
        String answerId = request.getParameter("answer_id");
        try {
            Long answerIdLong = Long.parseLong(answerId);

            AnswerService answerService = ServiceProvider
                    .getInstance()
                    .takeService(Service.ANSWER);

            answerService.deleteAnswer(answerIdLong);
            logger.info(String.format("Answer[%s] deleted.", answerId));

            response.sendRedirect(request.getContextPath()
                    + "/question?question_id="
                    + request.getParameter("question_id"));

        } catch (ConnectionPoolException | DaoException e) {
            logger.error("It's impossible to process request", e);
            request.setAttribute("message", "database.error");
            request.getRequestDispatcher("/question").forward(request, response);
        }
    }
}
