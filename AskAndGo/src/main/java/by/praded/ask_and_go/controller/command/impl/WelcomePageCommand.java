package by.praded.ask_and_go.controller.command.impl;

import by.praded.ask_and_go.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Kiryl Praded
 * 16.12.2020
 * <p>
 * Command serves to process the display welcome page request.
 * @see Command
 */
public class WelcomePageCommand implements Command {
    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/WEB-INF/jsp/welcome.jsp").forward(request, response);
    }
}
