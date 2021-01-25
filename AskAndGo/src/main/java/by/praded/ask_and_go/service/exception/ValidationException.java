package by.praded.ask_and_go.service.exception;

import java.util.Map;

/**
 * @author Kiryl Praded
 * 23.01.2021
 * <p>
 * Custom exception for cases when fiels doesn't passess validation.
 * @see Exception
 */
public class ValidationException extends Exception {
    /**
     * Validation error map.
     */
    private final Map<String, String> attributes;

    /**
     * Constructor of exception.
     *
     * @param attributes - value for the field {@link ValidationException#attributes}.
     */
    public ValidationException(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    /**
     * Method to get value of the field {@link ValidationException#attributes}.
     *
     * @return validation error map {@link ValidationException#attributes}.
     */
    public Map<String, String> getAttributes() {
        return attributes;
    }
}
