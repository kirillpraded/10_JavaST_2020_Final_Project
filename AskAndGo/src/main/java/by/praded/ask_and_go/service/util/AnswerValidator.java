package by.praded.ask_and_go.service.util;

import java.util.Objects;

/**
 * @author Kiryl Praded
 * 04.01.2021
 * <p>
 * Validator class for answers.
 * @see by.praded.ask_and_go.entity.Answer
 */
public class AnswerValidator {

    /**
     * Method to validate text of answer.
     *
     * @param text - text to validate
     * @return {@code true} if text is valid, {@code false} otherwise.
     * @see by.praded.ask_and_go.entity.Answer
     */
    public static boolean validateAnswerText(String text) {
        //можно ввести цензуру ненормативной лексики
        return Objects.nonNull(text) && text.length() >= 10 && text.length() <= 500;
    }

}
