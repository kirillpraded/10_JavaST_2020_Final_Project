package by.praded.ask_and_go.controller.command.impl.writer;

import by.praded.ask_and_go.controller.command.Command;
import by.praded.ask_and_go.controller.util.Attribute;
import by.praded.ask_and_go.dao.exception.ConnectionPoolException;
import by.praded.ask_and_go.dao.exception.DaoException;
import by.praded.ask_and_go.entity.Answer;
import by.praded.ask_and_go.entity.Question;
import by.praded.ask_and_go.entity.User;
import by.praded.ask_and_go.service.AnswerService;
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
 * 31.12.2020
 * <p>
 * Command serves to add answer to the question.
 * @see Command
 */
public class AddAnswerCommand implements Command {
    /**
     * Logger for event logging.
     */
    private static final Logger logger = LogManager.getLogger(AddAnswerCommand.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String answerText = request.getParameter(Attribute.TEXT);
        try {
            Long questionId = Long.parseLong(request.getParameter(Attribute.QUESTION_ID));
            Answer answer = new Answer();
            answer.setAuthor((User) request.getSession().getAttribute(Attribute.AUTH_USER));
            answer.setText(answerText.replaceAll("<","&lt;")
                    .replaceAll(">", "&gt;"));
            Question question = new Question();

            question.setId(questionId);
            answer.setQuestion(question);

            AnswerService answerService = ServiceProvider.getInstance().takeService(Service.ANSWER);

            answerService.addAnswer(answer);
            logger.info(String.format("Answer to the question %d successfully added", questionId));
            response.sendRedirect(request.getContextPath()
                    + "/app/question?question_id="
                    + questionId
                    + "&page="
                    + request.getParameter(Attribute.PAGE));

        } catch (ConnectionPoolException | DaoException e) {
            logger.error("It's impossible to process request", e);
            request.setAttribute(Attribute.CONNECTION_ERROR_MESSAGE, "database.error");
            request.setAttribute(Attribute.ANSWER_TEXT, answerText);
            request.getRequestDispatcher("/app/question").forward(request, response);
        } catch (ValidationException e) {
            e.getAttributes().forEach(request::setAttribute);
            request.setAttribute(Attribute.ANSWER_TEXT, answerText);
            request.getRequestDispatcher("/app/question").forward(request, response);
            logger.debug("Answer validation error.");
        }
    }
}
