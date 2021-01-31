package by.praded.ask_and_go.controller.command.impl;

import by.praded.ask_and_go.controller.command.Command;
import by.praded.ask_and_go.controller.util.Attribute;
import by.praded.ask_and_go.dao.exception.ConnectionPoolException;
import by.praded.ask_and_go.dao.exception.DaoException;
import by.praded.ask_and_go.entity.User;
import by.praded.ask_and_go.service.Service;
import by.praded.ask_and_go.service.UserService;
import by.praded.ask_and_go.service.impl.ServiceProvider;
import by.praded.ask_and_go.util.ResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Kiryl Praded
 * 17.01.2021
 * <p>
 * Command serves to update user profile image request.
 * @see Command
 */
public class UpdateProfileImageCommand implements Command {
    /**
     * Logger for event logging.
     */
    private static final Logger logger = LogManager.getLogger(UpdateProfileImageCommand.class);

    /**
     * Path to directory where to save images.
     */
    private static final String USER_IMAGE_PATH = ResourceManager
            .getInstance()
            .findConfigurationValue("path.image.user");

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Attribute.AUTH_USER);
        ServletContext servletContext = request.getServletContext();
        String appPath = servletContext.getRealPath("");
        Part fileToUpload = request.getPart(Attribute.IMAGE);
        String[] splitted = fileToUpload.getContentType().split("/");
        if (!splitted[0].equals("image")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            String fileName = user.getUsername() + "." + splitted[1];
            File file = new File(appPath + USER_IMAGE_PATH + fileName);
            try (InputStream inputStream = fileToUpload.getInputStream();
                 FileOutputStream fileOutputStream = new FileOutputStream(file, false)) {
                byte[] buffer = new byte[inputStream.available()];
                while (inputStream.read(buffer) != -1) { // считывает из буфера в новый файл
                    fileOutputStream.write(buffer);
                }
            }
            User updImageUser = new User();
            updImageUser.setId(user.getId());
            updImageUser.setProfileImage(fileName);

            UserService userService = ServiceProvider.getInstance().takeService(Service.USER);

            try {
                userService.updateProfileImage(updImageUser);

                logger.info(String.format("User[%d] successfully updated profile image", user.getId()));
                user.setProfileImage(fileName);
                response.sendRedirect(request.getContextPath() + "/user?user_id=" + user.getId());
            } catch (ConnectionPoolException | DaoException e) {
                logger.error("It's impossible to update user image in database", e);
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "database.error");

            }
        }
    }
}
