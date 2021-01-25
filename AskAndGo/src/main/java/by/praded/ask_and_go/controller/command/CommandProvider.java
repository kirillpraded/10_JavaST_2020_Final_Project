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
    private final Map<String, Command> getRepository = new HashMap<>();
    /**
     * Map of commands for `post` requests.
     */
    private final Map<String, Command> postRepository = new HashMap<>();

    /**
     * Class constructor. Initializes repositories of commands.
     */
    private CommandProvider() {
        getRepository.put("/user-edit", new ShowPersonalInfoEditPageCommand());
        getRepository.put("/main", new WelcomePageCommand());
        getRepository.put("/login", new ShowLoginPageCommand());
        getRepository.put("/logout", new LogoutCommand());
        getRepository.put("/registration", new ShowRegistrationPageCommand());
        getRepository.put("/admin", new ShowAdminPageCommand());
        getRepository.put("/admin/edit-user", new ShowUserEditPageCommand());
        //getRepository.put("/admin/delete-user", new AdminUserDeleteCommand());
        getRepository.put("/admin/edit-category", new ShowCategoryEditPageCommand());
        getRepository.put("/categories", new ShowCategoriesCommand());
        getRepository.put("/questions", new ShowQuestionsInCategoryCommand());
        getRepository.put("/ask", new ShowAskPageCommand());
        getRepository.put("/question", new ShowQuestionCommand());
        getRepository.put("/edit-question", new ShowQuestionEditFormCommand());
        getRepository.put("/edit-answer", new ShowAnswerEditFormCommand());
        getRepository.put("/user", new ShowUserCommand());
        getRepository.put("/search", new FindQuestionsCommand());
        postRepository.put("/delete-answer", new DeleteAnswerCommand());
        postRepository.put("/login", new LoginCommand());
        postRepository.put("/user-edit", new PersonalInfoEditCommand());
        postRepository.put("/change-password", new ChangePasswordCommand());
        postRepository.put("/update-image", new UpdateProfileImageCommand());
        /*

            зачем в пост репозитории команда которая обрабатывает гет запрос
            при изменении роли пользователя после пост запроса невозможно сделать следующий форвард
            request.getRequestDispatcher("/admin").forward(request, response);
            происходит пост форвард на /admin
            в результате которого сервлет не может найти в пост репозитории такого урла
            и выкидывает 500(у меня в приложении сделано под 404) ошибку.
         */
        postRepository.put("/admin", new ShowAdminPageCommand());
        /*
            по этой же причине нужен пост маппинг на /question.
            Если пользователь пытается добавить некорректный
            ответ(не проходит валидацию) нужно форварднуть его и передать некоторые параметры
            что в целом невозможно без форварда
         */
        postRepository.put("/lang", new ChangeLanguageCommand());
        postRepository.put("/question", new ShowQuestionCommand());
        postRepository.put("/update-correct", new MakeAnswerCorrectCommand());
        postRepository.put("/close", new MakeQuestionClosedCommand());
        postRepository.put("/delete-question", new DeleteQuestionCommand());
        postRepository.put("/block-author", new BlockAuthorCommand());
        postRepository.put("/registration", new RegistrationCommand());
        postRepository.put("/admin/edit-user", new UpdateUserRoleCommand());
        postRepository.put("/admin/delete-user", new UserDeleteCommand());
        postRepository.put("/admin/add-category", new AdminAddCategoryCommand());
        postRepository.put("/admin/delete-category", new AdminDeleteCategoryCommand());
        postRepository.put("/admin/edit-category", new AdminEditCategoryCommand());
        postRepository.put("/categories", new ShowCategoriesCommand());
        postRepository.put("/questions", new ShowQuestionsInCategoryCommand());
        postRepository.put("/ask", new AskQuestionCommand());
        postRepository.put("/answer", new AddAnswerCommand());
        postRepository.put("/edit-question", new QuestionEditCommand());
        postRepository.put("/edit-answer", new AnswerEditCommand());
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
        return getRepository.get(name);
    }

    /**
     * Method to choose a {@link Command} by its name.
     *
     * @param name - the name of the command.
     * @return the {@link Command} implementation corresponding to the {@code name}
     */
    public Command providePostCommand(String name) {
        return postRepository.get(name);
    }
}
