package by.praded.ask_and_go.controller.command;

import by.praded.ask_and_go.controller.command.impl.ShowUserCommand;
import by.praded.ask_and_go.controller.command.impl.ChangeLanguageCommand;
import by.praded.ask_and_go.controller.command.impl.ChangePasswordCommand;
import by.praded.ask_and_go.controller.command.impl.DeleteAnswerCommand;
import by.praded.ask_and_go.controller.command.impl.DeleteQuestionCommand;
import by.praded.ask_and_go.controller.command.impl.FindQuestionsCommand;
import by.praded.ask_and_go.controller.command.impl.LoginCommand;
import by.praded.ask_and_go.controller.command.impl.LogoutCommand;
import by.praded.ask_and_go.controller.command.impl.PersonalInfoEditCommand;
import by.praded.ask_and_go.controller.command.impl.RegistrationCommand;
import by.praded.ask_and_go.controller.command.impl.ShowCategoriesCommand;
import by.praded.ask_and_go.controller.command.impl.ShowLoginPageCommand;
import by.praded.ask_and_go.controller.command.impl.ShowPersonalInfoEditPageCommand;
import by.praded.ask_and_go.controller.command.impl.ShowQuestionCommand;
import by.praded.ask_and_go.controller.command.impl.ShowQuestionsInCategoryCommand;
import by.praded.ask_and_go.controller.command.impl.ShowRegistrationPageCommand;
import by.praded.ask_and_go.controller.command.impl.UpdateProfileImageCommand;
import by.praded.ask_and_go.controller.command.impl.WelcomePageCommand;
import by.praded.ask_and_go.controller.command.impl.admin.AdminAddCategoryCommand;
import by.praded.ask_and_go.controller.command.impl.admin.AdminDeleteCategoryCommand;
import by.praded.ask_and_go.controller.command.impl.admin.AdminEditCategoryCommand;
import by.praded.ask_and_go.controller.command.impl.admin.ShowAdminPageCommand;
import by.praded.ask_and_go.controller.command.impl.admin.ShowCategoryEditPageCommand;
import by.praded.ask_and_go.controller.command.impl.admin.ShowUserEditPageCommand;
import by.praded.ask_and_go.controller.command.impl.admin.UpdateUserRoleCommand;
import by.praded.ask_and_go.controller.command.impl.admin.UserDeleteCommand;
import by.praded.ask_and_go.controller.command.impl.moderator.BlockAuthorCommand;
import by.praded.ask_and_go.controller.command.impl.writer.AddAnswerCommand;
import by.praded.ask_and_go.controller.command.impl.writer.AnswerEditCommand;
import by.praded.ask_and_go.controller.command.impl.writer.AskQuestionCommand;
import by.praded.ask_and_go.controller.command.impl.writer.MakeAnswerCorrectCommand;
import by.praded.ask_and_go.controller.command.impl.writer.MakeQuestionClosedCommand;
import by.praded.ask_and_go.controller.command.impl.writer.QuestionEditCommand;
import by.praded.ask_and_go.controller.command.impl.writer.ShowAnswerEditFormCommand;
import by.praded.ask_and_go.controller.command.impl.writer.ShowAskPageCommand;
import by.praded.ask_and_go.controller.command.impl.writer.ShowQuestionEditFormCommand;

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
        GET_REPO.put("/app/user-edit", new ShowPersonalInfoEditPageCommand());
        GET_REPO.put("/app/main", new WelcomePageCommand());
        GET_REPO.put("/app/login", new ShowLoginPageCommand());
        GET_REPO.put("/app/logout", new LogoutCommand());
        GET_REPO.put("/app/registration", new ShowRegistrationPageCommand());
        GET_REPO.put("/app/admin", new ShowAdminPageCommand());
        GET_REPO.put("/app/admin/edit-user", new ShowUserEditPageCommand());
        //getRepository.put("/admin/delete-user", new AdminUserDeleteCommand());
        GET_REPO.put("/app/admin/edit-category", new ShowCategoryEditPageCommand());
        GET_REPO.put("/app/categories", new ShowCategoriesCommand());
        GET_REPO.put("/app/questions", new ShowQuestionsInCategoryCommand());
        GET_REPO.put("/app/ask", new ShowAskPageCommand());
        GET_REPO.put("/app/question", new ShowQuestionCommand());
        GET_REPO.put("/app/edit-question", new ShowQuestionEditFormCommand());
        GET_REPO.put("/app/edit-answer", new ShowAnswerEditFormCommand());
        GET_REPO.put("/app/user", new ShowUserCommand());
        GET_REPO.put("/app/search", new FindQuestionsCommand());
        POST_REPO.put("/app/delete-answer", new DeleteAnswerCommand());
        POST_REPO.put("/app/login", new LoginCommand());
        POST_REPO.put("/app/user-edit", new PersonalInfoEditCommand());
        POST_REPO.put("/app/change-password", new ChangePasswordCommand());
        POST_REPO.put("/app/update-image", new UpdateProfileImageCommand());
        /*

            зачем в пост репозитории команда которая обрабатывает гет запрос
            при изменении роли пользователя после пост запроса невозможно сделать следующий форвард
            request.getRequestDispatcher("/admin").forward(request, response);
            происходит пост форвард на /admin
            в результате которого сервлет не может найти в пост репозитории такого урла
            и выкидывает 500(у меня в приложении сделано под 404) ошибку.
         */
        POST_REPO.put("/app/admin", new ShowAdminPageCommand());
        /*
            по этой же причине нужен пост маппинг на /question.
            Если пользователь пытается добавить некорректный
            ответ(не проходит валидацию) нужно форварднуть его и передать некоторые параметры
            что в целом невозможно без форварда
         */
        POST_REPO.put("/app/lang", new ChangeLanguageCommand());
        POST_REPO.put("/app/question", new ShowQuestionCommand());
        POST_REPO.put("/app/update-correct", new MakeAnswerCorrectCommand());
        POST_REPO.put("/app/close", new MakeQuestionClosedCommand());
        POST_REPO.put("/app/delete-question", new DeleteQuestionCommand());
        POST_REPO.put("/app/block-author", new BlockAuthorCommand());
        POST_REPO.put("/app/registration", new RegistrationCommand());
        POST_REPO.put("/app/admin/edit-user", new UpdateUserRoleCommand());
        POST_REPO.put("/app/admin/delete-user", new UserDeleteCommand());
        POST_REPO.put("/app/admin/add-category", new AdminAddCategoryCommand());
        POST_REPO.put("/app/admin/delete-category", new AdminDeleteCategoryCommand());
        POST_REPO.put("/app/admin/edit-category", new AdminEditCategoryCommand());
        POST_REPO.put("/app/categories", new ShowCategoriesCommand());
        POST_REPO.put("/app/questions", new ShowQuestionsInCategoryCommand());
        POST_REPO.put("/app/ask", new AskQuestionCommand());
        POST_REPO.put("/app/answer", new AddAnswerCommand());
        POST_REPO.put("/app/edit-question", new QuestionEditCommand());
        POST_REPO.put("/app/edit-answer", new AnswerEditCommand());
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
