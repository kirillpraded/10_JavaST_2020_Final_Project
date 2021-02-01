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
 * 10.01.2021
 * <p>
 * Command serves to editing answer.
 * @see Command
 */
public class AnswerEditCommand implements Command {
    /**
     * Logger for event logging.
     */
    private static final Logger logger = LogManager.getLogger(AnswerEditCommand.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Answer answer = new Answer();
        try {
            String content = request.getParameter(Attribute.TEXT)
                    .replaceAll("<","&lt;")
                    .replaceAll(">", "&gt;");
            answer.setText(content);
            answer.setId(Long.parseLong(request.getParameter(Attribute.ANSWER_ID)));

            User author = new User();
            author.setId(Long.parseLong(request.getParameter(Attribute.USER_ID)));
            answer.setAuthor(author);

            String questionId = request.getParameter(Attribute.QUESTION_ID);
            Question question = new Question();
            question.setId(Long.parseLong(questionId));
            answer.setQuestion(question);

            AnswerService answerService = ServiceProvider.getInstance().takeService(Service.ANSWER);
            answerService.updateAnswerText(answer);
            logger.info(String.format("Answer id=%d successfully updated", answer.getId()));
            response.sendRedirect(request.getContextPath()
                    + "/app/question?question_id="
                    + questionId
                    + "&page="
                    + request.getParameter(Attribute.PAGE));

        } catch (ConnectionPoolException | DaoException e) {
            logger.error("It's impossible to process request", e);
            request.setAttribute(Attribute.MESSAGE, "database.error");
            request.setAttribute(Attribute.ANSWER, answer);
            request.getRequestDispatcher("/WEB-INF/jsp/answer-edit.jsp").forward(request, response);
        } catch (ValidationException e) {
            logger.debug("Answer text isn't valid");
            e.getAttributes().forEach(request::setAttribute);
            request.setAttribute(Attribute.ANSWER, answer);
            request.getRequestDispatcher("/WEB-INF/jsp/answer-edit.jsp").forward(request, response);
        }

    }

}
