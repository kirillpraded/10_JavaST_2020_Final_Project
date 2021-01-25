package by.praded.ask_and_go.controller.command.impl.writer;

import by.praded.ask_and_go.controller.command.Command;
import by.praded.ask_and_go.dao.exception.ConnectionPoolException;
import by.praded.ask_and_go.dao.exception.DaoException;
import by.praded.ask_and_go.entity.Category;
import by.praded.ask_and_go.entity.Question;
import by.praded.ask_and_go.entity.Tag;
import by.praded.ask_and_go.entity.User;
import by.praded.ask_and_go.service.CategoryService;
import by.praded.ask_and_go.service.QuestionService;
import by.praded.ask_and_go.service.Service;
import by.praded.ask_and_go.service.TagService;
import by.praded.ask_and_go.service.exception.ValidationException;
import by.praded.ask_and_go.service.impl.ServiceProvider;
import by.praded.ask_and_go.service.util.ResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

/**
 * @author Kiryl Praded
 * 27.12.2020
 * <p>
 * Command serves to add new question.
 * @see Command
 */
public class AskQuestionCommand implements Command {
    /**
     * Logger for event logging.
     */
    private static final Logger logger = LogManager.getLogger(AskQuestionCommand.class);

    /**
     * Path to directory where to save images.
     */
    private static final String QUESTION_IMAGE_PATH = ResourceManager
            .getInstance()
            .findConfigurationValue("path.image.question");

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String title = request.getParameter("title")
                .replaceAll("<","&lt;")
                .replaceAll(">", "&gt;");

        String content = request.getParameter("text")
                .replaceAll("<","&lt;")
                .replaceAll(">", "&gt;");

        String tags = request.getParameter("tags")
                .replaceAll("<","&lt;")
                .replaceAll(">", "&gt;");

        String categoryId = request.getParameter("category_id");

        TagService tagService = ServiceProvider.getInstance().takeService(Service.TAG);
        List<Tag> tagsList = tagService.parseTags(tags);
        Category category = null;


        try {
            category = new Category();
            Long categoryIdLong = Long.parseLong(categoryId);
            category.setId(categoryIdLong);
            QuestionService questionService = ServiceProvider.getInstance().takeService(Service.QUESTION);
            User user = (User) request.getSession().getAttribute("auth_user");
            Question question = new Question();
            question.setAuthor(user);
            question.setCategory(category);
            question.setText(content);
            question.setTitle(title);
            question.setTags(tagsList);
            question.setImageName(processImage(request));

            long questionId = questionService.createQuestion(question);
            logger.info(String.format("Question %d successfully added", questionId));
            response.sendRedirect(request.getContextPath() + "/question?question_id=" + questionId);
        } catch (ConnectionPoolException | DaoException e) {
            logger.error("It's impossible to process request", e);
            request.setAttribute("message", "database.error");
            forwardBack(request, response, content, title, tags, category);
        } catch (ValidationException e) {
            CategoryService categoryService = ServiceProvider.getInstance().takeService(Service.CATEGORY);
            e.getAttributes().forEach(request::setAttribute);
            try {
                categoryService.findAllCategories();
                request.setAttribute("categories", categoryService.findAllCategories());

            } catch (ConnectionPoolException | DaoException exc) {
                logger.error("It's impossible to process request", exc);
                request.setAttribute("message", "database.error");
            }
            forwardBack(request, response, content, title, tags, category);
        }
    }

    private void forwardBack(HttpServletRequest request, HttpServletResponse response,
                             String questionText, String questionTitle,
                             String tags, Category category) throws ServletException, IOException {
        request.setAttribute("text", questionText);
        request.setAttribute("title", questionTitle);
        request.setAttribute("tags", tags);
        request.setAttribute("current_category", category);
        request.getRequestDispatcher("/WEB-INF/jsp/ask-form.jsp").forward(request, response);
    }

    private String processImage(HttpServletRequest request) throws IOException, ServletException {
        ServletContext servletContext = request.getServletContext();
        String appPath = servletContext.getRealPath("");
        Part fileToUpload = request.getPart("image");
        String[] splitted = fileToUpload.getContentType().split("/");
        if (!splitted[0].equals("image")) {
            return null;
        } else {
            String fileName = UUID.randomUUID().toString() + "." + splitted[1];
            File file = new File(appPath + QUESTION_IMAGE_PATH + fileName);
            try (InputStream inputStream = fileToUpload.getInputStream();
                 FileOutputStream fileOutputStream = new FileOutputStream(file, false)) {
                byte[] buffer = new byte[inputStream.available()];
                while (inputStream.read(buffer) != -1) {
                    fileOutputStream.write(buffer);
                }
            }
            return fileName;
        }
    }
}
