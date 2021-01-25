package by.praded.ask_and_go.service.util;

import java.util.Objects;

/**
 * @author Kiryl Praded
 * 27.12.2020
 * <p>
 * Validator class for questions.
 * @see by.praded.ask_and_go.entity.Question
 */
public class QuestionValidator {
    /**
     * Method to validate title of question.
     *
     * @param title - title to validate.
     * @return {@code true} if title is valid, {@code false} otherwise.
     * @see by.praded.ask_and_go.entity.Question
     */
    public static boolean validateTitle(String title) {
        return Objects.nonNull(title) &&
                title.length() >= 3 && title.length() <= 50;
    }

    /**
     * Method to validate text of category.
     *
     * @param text - text to validate.
     * @return {@code true} if text is valid, {@code false} otherwise.
     * @see by.praded.ask_and_go.entity.Question
     */
    public static boolean validateText(String text) {
        return Objects.nonNull(text) && text.length() >= 10 && text.length() <= 500;
    }
}
