package by.praded.ask_and_go.controller.command.impl;

import by.praded.ask_and_go.controller.command.Command;
import by.praded.ask_and_go.controller.util.Attribute;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Kiryl Praded
 * 11.01.2021
 * <p>
 * Command serves to change language.
 * @see Command
 */
public class ChangeLanguageCommand implements Command {
    /**
     * Logger for event logging.
     */
    private static final Logger logger = LogManager.getLogger(ChangeLanguageCommand.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String language = request.getParameter(Attribute.LANG);
        String locale;
        switch (language) {
            case "rus":
                locale = "ru_RU";
                break;
            case "bel":
                locale = "by_BY";
                break;
            case "eng":
            default:
                locale = "en_US";
        }

        session.setAttribute(Attribute.LOCALE, locale);
        Cookie localeCookie = new Cookie(Attribute.LOCALE, locale);
        response.addCookie(localeCookie);
        logger.debug(String.format("User successfully changed locale to %s", locale));
        response.sendRedirect(request.getHeader("referer"));
    }
}
