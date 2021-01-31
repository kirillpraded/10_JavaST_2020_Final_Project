package by.praded.ask_and_go.service.util;

import java.util.Objects;

/**
 * @author Kiryl Praded
 * 22.12.2020
 * <p>
 * Validator class for categories.
 * @see by.praded.ask_and_go.entity.Category
 */
public final class CategoryValidator {
    /**
     * Method to validate name of category.
     *
     * @param name - name of category to validate.
     * @return {@code true} if category name is valid, {@code false} otherwise.
     * @see by.praded.ask_and_go.entity.Category
     */
    public static boolean validateCategoryName(String name) {
        return Objects.nonNull(name) && name.length() > 0 && name.length() <= 50;
    }
}
