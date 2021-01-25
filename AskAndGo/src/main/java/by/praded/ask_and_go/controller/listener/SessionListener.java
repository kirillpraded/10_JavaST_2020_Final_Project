package by.praded.ask_and_go.controller.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author Kiryl Praded
 * 21.01.2021
 * <p>
 * Listener for sessions.
 */
@WebListener
public class SessionListener implements HttpSessionListener {
    /**
     * Logger to log session events.
     */
    public static final Logger logger = LogManager.getLogger(SessionListener.class);

    /**
     * Method to register session creating.
     *
     * @param event - session event
     */
    @Override
    public void sessionCreated(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        logger.info("created session; id: {}", session::getId);
    }

    /**
     * Method to register session destroying.
     *
     * @param event - session event.
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        logger.info("Session destroyed.");
    }
}
