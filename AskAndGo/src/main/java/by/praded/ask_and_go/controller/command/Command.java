package by.praded.ask_and_go.controller.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Kiryl Praded
 * 19.12.2020
 * <p>
 * Default realization of the command-pattern.
 */
public interface Command {

    /**
     * Method to execute the command.
     *
     * @param request  - http request to process.
     * @param response - specimen of response.
     * @throws IOException      on redirecting/forwarding request.
     * @throws ServletException on forwarding request.
     */
    void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;
}
