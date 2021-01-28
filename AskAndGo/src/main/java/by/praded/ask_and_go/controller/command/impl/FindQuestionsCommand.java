package by.praded.ask_and_go.controller.command.impl;

import by.praded.ask_and_go.controller.command.Command;
import by.praded.ask_and_go.controller.util.Attribute;
import by.praded.ask_and_go.dao.exception.DaoException;
import by.praded.ask_and_go.entity.Question;
import by.praded.ask_and_go.dao.exception.ConnectionPoolException;
import by.praded.ask_and_go.service.QuestionService;
import by.praded.ask_and_go.service.Service;
import by.praded.ask_and_go.service.impl.ServiceProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * @author Kiryl Praded
 * 13.01.2021
 *
 * Command serves to find questions.
 * @see Command
 */
public class FindQuestionsCommand implements Command {
    /**
     * Logger for event logging.
     */
    private static final Logger logger = LogManager.getLogger(FindQuestionsCommand.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String query = request.getParameter(Attribute.QUERY).replaceAll("<","&lt;")
                .replaceAll(">", "&gt;");
        try {
            QuestionService questionService = ServiceProvider.getInstance().takeService(Service.QUESTION);

            Set<Question> questions = questionService.searchQuestionsByTitle(query);
            request.setAttribute(Attribute.QUESTIONS, questions);
            request.getRequestDispatcher("/WEB-INF/jsp/questions.jsp").forward(request, response);
        } catch (ConnectionPoolException | DaoException e) {
            logger.error("It's impossible to process request", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "database.error");
        }
    }
}
