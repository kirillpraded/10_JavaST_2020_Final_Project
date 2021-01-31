package by.praded.ask_and_go.service.util;

import by.praded.ask_and_go.entity.Tag;

import java.util.List;

/**
 * @author Kiryl Praded
 * 29.12.2020
 * <p>
 * Validator class for tags.
 * @see Tag
 */
public final class TagValidator {

    /**
     * Method to validate name of tag.
     *
     * @param name - name to validate.
     * @return {@code true} if name is valid, {@code false} otherwise.
     * @see Tag
     */
    public static boolean validateName(String name) {
        return name.length() >= 2 && name.length() <= 32;
    }

    /**
     * Method to validate all tag names in given list.
     *
     * @param tags - list of tags to validate.
     * @return {@code true} if all tag names are valid, {@code false} otherwise.
     * @see Tag
     */
    public static boolean validateNames(List<Tag> tags) {
        for (Tag tag : tags) {
            if ((tag.getText().length() < 2 || tag.getText().length() > 32) && !tag.getText().isEmpty()) {
                return false;
            }
        }
        return true;
    }
}
