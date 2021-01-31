package by.praded.ask_and_go.controller;

import by.praded.ask_and_go.controller.command.Command;
import by.praded.ask_and_go.controller.command.CommandProvider;
import by.praded.ask_and_go.dao.exception.ConnectionPoolException;
import by.praded.ask_and_go.dao.pool.ConnectionPool;
import by.praded.ask_and_go.util.ResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author Kiryl Praded
 * 04.12.2020
 * <p>
 * Main servlet of application.
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,      // 5 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class DispatcherServlet extends HttpServlet {
    /**
     * Logger for event logging.
     */
    private static final Logger logger = LogManager.getLogger(DispatcherServlet.class);

    /**
     * Command provider to get {@link Command} by request URI.
     */
    private CommandProvider commandProvider;
    /**
     * Pool of connections. For initializing during application deployment and destroying during application destroying.
     */
    private ConnectionPool connectionPool;

    /**
     * Servlet initialization method
     *
     * @throws ServletException - exception of the servlet.
     */
    @Override
    public void init() throws ServletException {
        commandProvider = CommandProvider.getInstance();
        try {
            ResourceManager resourceManager = ResourceManager.getInstance();
            String driver = resourceManager.findConfigurationValue("database.driver");
            String url = resourceManager.findConfigurationValue("database.url");
            String login = resourceManager.findConfigurationValue("database.user");
            String password = resourceManager.findConfigurationValue("database.password");
            int poolSize = Integer.parseInt(resourceManager.findConfigurationValue("database.poolSize"));
            connectionPool = ConnectionPool.getInstance();
            connectionPool.init(driver, url, login, password, poolSize);
            logger.info("Application initialized");
        } catch (ConnectionPoolException e) {
            logger.error("It is impossible to initialize application", e);
            destroy();
        }
    }

    /**
     * Servlet destroying method.
     */
    @Override
    public void destroy() {
        try {
            connectionPool.destroyPool();
        } catch (ConnectionPoolException e) {
            logger.error("It is impossible to destroy pool.", e);
        }
    }

    /**
     * Method to process post-requests.
     *
     * @param request  - request body.
     * @param response - response for the request.
     * @throws ServletException - exception of the servlet.
     * @throws IOException      - input-output exception.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Command command = commandProvider
                .providePostCommand(request.getRequestURI().replace(request.getContextPath(), ""));
        processCommand(command, request, response);
    }

    /**
     * Method to process get-requests.
     *
     * @param request  - request body.
     * @param response - response for the request.
     * @throws ServletException - exception of the servlet.
     * @throws IOException      - input-output exception.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Command command = commandProvider
                .provideGetCommand(request.getRequestURI().replace(request.getContextPath(), ""));
        processCommand(command, request, response);
    }

    /**
     * Method to handle commands.
     *
     * @param command - command to handle.
     * @param request - request body.
     * @param response - response for the request.
     * @throws ServletException - exception of the servlet.
     * @throws IOException      - input-output exception.
     */
    private void processCommand(Command command,
                                HttpServletRequest request,
                                HttpServletResponse response) throws IOException, ServletException {
        if (Objects.nonNull(command)) {
            command.execute(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
