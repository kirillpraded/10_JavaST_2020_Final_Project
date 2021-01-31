package by.praded.ask_and_go.controller.command;


import by.praded.ask_and_go.controller.command.impl.*;
import by.praded.ask_and_go.controller.command.impl.admin.*;
import by.praded.ask_and_go.controller.command.impl.moderator.BlockAuthorCommand;
import by.praded.ask_and_go.controller.command.impl.writer.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kiryl Praded
 * 16.12.2020
 * <p>
 * Class to provide commands.
 * @see Command
 */
public class CommandProvider {
    /**
     * Static instance of class for singleton.
     */
    private static CommandProvider instance;
    /**
     * Map of commands for `GET` requests.
     */
    private final Map<String, Command> GET_REPO = new HashMap<>();
    /**
     * Map of commands for `POST` requests.
     */
    private final Map<String, Command> POST_REPO = new HashMap<>();

    /**
     * Class constructor. Initializes repositories of commands.
     */
    private CommandProvider() {
        GET_REPO.put("/user-edit", new ShowPersonalInfoEditPageCommand());
        GET_REPO.put("/main", new WelcomePageCommand());
        GET_REPO.put("/login", new ShowLoginPageCommand());
        GET_REPO.put("/logout", new LogoutCommand());
        GET_REPO.put("/registration", new ShowRegistrationPageCommand());
        GET_REPO.put("/admin", new ShowAdminPageCommand());
        GET_REPO.put("/admin/edit-user", new ShowUserEditPageCommand());
        //getRepository.put("/admin/delete-user", new AdminUserDeleteCommand());
        GET_REPO.put("/admin/edit-category", new ShowCategoryEditPageCommand());
        GET_REPO.put("/categories", new ShowCategoriesCommand());
        GET_REPO.put("/questions", new ShowQuestionsInCategoryCommand());
        GET_REPO.put("/ask", new ShowAskPageCommand());
        GET_REPO.put("/question", new ShowQuestionCommand());
        GET_REPO.put("/edit-question", new ShowQuestionEditFormCommand());
        GET_REPO.put("/edit-answer", new ShowAnswerEditFormCommand());
        GET_REPO.put("/user", new ShowUserCommand());
        GET_REPO.put("/search", new FindQuestionsCommand());
        POST_REPO.put("/delete-answer", new DeleteAnswerCommand());
        POST_REPO.put("/login", new LoginCommand());
        POST_REPO.put("/user-edit", new PersonalInfoEditCommand());
        POST_REPO.put("/change-password", new ChangePasswordCommand());
        POST_REPO.put("/update-image", new UpdateProfileImageCommand());
        /*

            зачем в пост репозитории команда которая обрабатывает гет запрос
            при изменении роли пользователя после пост запроса невозможно сделать следующий форвард
            request.getRequestDispatcher("/admin").forward(request, response);
            происходит пост форвард на /admin
            в результате которого сервлет не может найти в пост репозитории такого урла
            и выкидывает 500(у меня в приложении сделано под 404) ошибку.
         */
        POST_REPO.put("/admin", new ShowAdminPageCommand());
        /*
            по этой же причине нужен пост маппинг на /question.
            Если пользователь пытается добавить некорректный
            ответ(не проходит валидацию) нужно форварднуть его и передать некоторые параметры
            что в целом невозможно без форварда
         */
        POST_REPO.put("/lang", new ChangeLanguageCommand());
        POST_REPO.put("/question", new ShowQuestionCommand());
        POST_REPO.put("/update-correct", new MakeAnswerCorrectCommand());
        POST_REPO.put("/close", new MakeQuestionClosedCommand());
        POST_REPO.put("/delete-question", new DeleteQuestionCommand());
        POST_REPO.put("/block-author", new BlockAuthorCommand());
        POST_REPO.put("/registration", new RegistrationCommand());
        POST_REPO.put("/admin/edit-user", new UpdateUserRoleCommand());
        POST_REPO.put("/admin/delete-user", new UserDeleteCommand());
        POST_REPO.put("/admin/add-category", new AdminAddCategoryCommand());
        POST_REPO.put("/admin/delete-category", new AdminDeleteCategoryCommand());
        POST_REPO.put("/admin/edit-category", new AdminEditCategoryCommand());
        POST_REPO.put("/categories", new ShowCategoriesCommand());
        POST_REPO.put("/questions", new ShowQuestionsInCategoryCommand());
        POST_REPO.put("/ask", new AskQuestionCommand());
        POST_REPO.put("/answer", new AddAnswerCommand());
        POST_REPO.put("/edit-question", new QuestionEditCommand());
        POST_REPO.put("/edit-answer", new AnswerEditCommand());
    }

    /**
     * Static method to get instance of the class.
     *
     * @return value of {@link CommandProvider#instance}.
     */
    public static CommandProvider getInstance() {
        if (instance == null) {
            instance = new CommandProvider();
        }
        return instance;
    }

    /**
     * Method to choose a {@link Command} by its name.
     *
     * @param name - the name of the command.
     * @return the {@link Command} implementation corresponding to the {@code name}
     */
    public Command provideGetCommand(String name) {
        return GET_REPO.get(name);
    }

    /**
     * Method to choose a {@link Command} by its name.
     *
     * @param name - the name of the command.
     * @return the {@link Command} implementation corresponding to the {@code name}
     */
    public Command providePostCommand(String name) {
        return POST_REPO.get(name);
    }
}
