package by.praded.ask_and_go.controller.command.impl.writer;

import by.praded.ask_and_go.controller.command.Command;
import by.praded.ask_and_go.dao.exception.DaoException;
import by.praded.ask_and_go.entity.Answer;
import by.praded.ask_and_go.entity.Question;
import by.praded.ask_and_go.dao.exception.ConnectionPoolException;
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
 * 08.01.2021
 *
 * Command serves to mark an answer as correct.
 * @see Command
 */
public class MakeAnswerCorrectCommand implements Command {
    /**
     * Logger for event logging.
     */
    private static final Logger logger = LogManager.getLogger(MakeAnswerCorrectCommand.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String questionId = request.getParameter("question_id");
        String answerId = request.getParameter("answer_id");
        try {
            AnswerService answerService = ServiceProvider.getInstance().takeService(Service.ANSWER);
            Answer answer = new Answer();

            Question question = new Question();
            question.setId(Long.parseLong(questionId));

            answer.setQuestion(question);

            answer.setId(Long.parseLong(answerId));
            answerService.updateAnswerIsCorrect(answer);
            logger.info(String.format("Answer[%s] is updated correct.", answerId));
            response.sendRedirect(request.getContextPath() + "/question?question_id=" + questionId);
        } catch (ConnectionPoolException | DaoException e) {
            logger.error("It's impossible to process request", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "error-page.server");
        }
    }
}
