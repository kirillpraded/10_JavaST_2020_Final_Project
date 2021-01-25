package by.praded.ask_and_go.controller.command.impl;

import by.praded.ask_and_go.controller.command.Command;
import by.praded.ask_and_go.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Kiryl Praded
 * 19.12.2020
 * <p>
 * Command serves to logout.
 * @see Command
 */
public class LogoutCommand implements Command {
    /**
     * Logger for event logging.
     */
    private static final Logger logger = LogManager.getLogger(LogoutCommand.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = (User) request.getSession().getAttribute("auth_user");
        logger.info(String.format("User[%d] is logged out", user.getId()));
        //сессия может быть инвалидирована только один раз, далее просто выкидывается npe
        request.getSession(false).invalidate();
        response.sendRedirect(request.getHeader("referer"));
    }
}
