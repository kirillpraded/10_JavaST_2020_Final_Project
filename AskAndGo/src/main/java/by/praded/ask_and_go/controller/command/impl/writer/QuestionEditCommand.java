package by.praded.ask_and_go.controller.command.impl.writer;

import by.praded.ask_and_go.controller.command.Command;
import by.praded.ask_and_go.dao.exception.ConnectionPoolException;
import by.praded.ask_and_go.dao.exception.DaoException;
import by.praded.ask_and_go.entity.Question;
import by.praded.ask_and_go.entity.User;
import by.praded.ask_and_go.service.QuestionService;
import by.praded.ask_and_go.service.Service;
import by.praded.ask_and_go.service.exception.ValidationException;
import by.praded.ask_and_go.service.impl.ServiceProvider;
import by.praded.ask_and_go.service.util.QuestionValidator;
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
 * Command serves to edit question.
 * @see Question
 */
public class QuestionEditCommand implements Command {
    /**
     * Logger for event logging.
     */
    private static final Logger logger = LogManager.getLogger(QuestionEditCommand.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String title = request.getParameter("title")
                .replaceAll("<","&lt;")
                .replaceAll(">", "&gt;");

        String text = request.getParameter("text")
                .replaceAll("<","&lt;")
                .replaceAll(">", "&gt;");

        Question question = new Question();
        try {
            question.setTitle(title);
            question.setText(text);
            question.setId(Long.parseLong(request.getParameter("question_id")));
            User author = new User();
            author.setId(Long.parseLong(request.getParameter("user_id")));
            question.setAuthor(author);
            QuestionService questionService = ServiceProvider.getInstance().takeService(Service.QUESTION);
            questionService.updateQuestion(question);
            logger.info(String.format("Question[%d] is updated", question.getId()));
            response.sendRedirect(request.getContextPath() + "/question?question_id=" + question.getId());
        } catch (ConnectionPoolException | DaoException e) {
            logger.error("It's impossible to process request", e);
            request.setAttribute("message", "database.error");
            forwardBack(request, response, question);
        } catch (ValidationException e) {
            e.getAttributes().forEach(request::setAttribute);
            forwardBack(request, response, question);
        }

    }

    private void forwardBack(HttpServletRequest request, HttpServletResponse response,
                             Question question) throws ServletException, IOException {

        request.setAttribute("question", question);
        request.getRequestDispatcher("/WEB-INF/jsp/question-edit.jsp").forward(request, response);
    }
}
