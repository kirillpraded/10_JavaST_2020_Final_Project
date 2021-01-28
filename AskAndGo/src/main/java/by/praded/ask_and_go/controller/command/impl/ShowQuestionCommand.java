package by.praded.ask_and_go.controller.command.impl;

import by.praded.ask_and_go.controller.command.Command;
import by.praded.ask_and_go.controller.util.Attribute;
import by.praded.ask_and_go.dao.exception.ConnectionPoolException;
import by.praded.ask_and_go.dao.exception.DaoException;
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
import java.util.Objects;

/**
 * @author Kiryl Praded
 * 30.12.2020
 * <p>
 * Command serves to process the display question request.
 * @see Command
 */
public class ShowQuestionCommand implements Command {
    /**
     * Logger for event logging.
     */
    private static final Logger logger = LogManager.getLogger(ShowQuestionCommand.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String questionId = request.getParameter(Attribute.QUESTION_ID);
        if (Objects.nonNull(questionId) && !questionId.isEmpty()) {
            try {
                Long questionIdLong = Long.parseLong(questionId);
                QuestionService questionService = ServiceProvider.getInstance().takeService(Service.QUESTION);

                request.setAttribute(Attribute.QUESTION, questionService.findQuestionById(questionIdLong));
                request.getRequestDispatcher("/WEB-INF/jsp/question.jsp").forward(request, response);
            } catch (NumberFormatException | EntityNotExistsException e) {
                logger.debug("Page not found", e);
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "error-page.not-found");
            } catch (ConnectionPoolException | DaoException e) {
                logger.error("It's impossible to process request", e);

                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "error-page.server");
            }
        } else {
            logger.debug("Page not found");
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "error-page.not-found");
        }
    }
}
