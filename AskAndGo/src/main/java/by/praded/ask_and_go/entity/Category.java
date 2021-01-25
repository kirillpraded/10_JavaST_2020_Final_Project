package by.praded.ask_and_go.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Kiryl Praded
 * 09.12.2020
 * <p>
 * Representation of the Category.
 * @see Entity
 */
public class Category extends Entity {
    /**
     * Name of the category.
     */
    private String name;

    /**
     * Parent of the category.
     */
    private Category parent;

    /**
     * List of question in current category.
     *
     * @see Question
     */
    private List<Question> questions;

    /**
     * List of subcategories of category.
     */
    private List<Category> subcategories;

    /**
     * Class constructor.
     * Initializes fields {@link Category#questions} and {@link Category#subcategories}.
     */
    public Category() {
        questions = new ArrayList<>();
        subcategories = new ArrayList<>();
    }

    /**
     * Method to get parent category.
     *
     * @return value of the field {@link Category#parent}.
     */
    public Category getParent() {
        return parent;
    }

    /**
     * Method to set parent of the category.
     *
     * @param parent - value to set for field {@link Category#parent}.
     */
    public void setParent(Category parent) {
        this.parent = parent;
    }

    /**
     * Method to get list of subcategories.
     *
     * @return value of the field {@link Category#subcategories}.
     */
    public List<Category> getSubcategories() {
        return subcategories;
    }

    /**
     * Method to set list of subcategories of the category.
     *
     * @param subcategories - value to set for field {@link Category#subcategories}.
     */
    public void setSubcategories(List<Category> subcategories) {
        this.subcategories = subcategories;
    }

    /**
     * Method to get list of questions in concrete category.
     *
     * @return value of the field {@link Category#questions}.
     */
    public List<Question> getQuestions() {
        return questions;
    }

    /**
     * Method to set list questions in concrete category.
     *
     * @param questions - value to set for field {@link Category#questions}.
     */
    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    /**
     * Method to get name of the category.
     *
     * @return value of the field {@link Category#name}.
     */
    public String getName() {
        return name;
    }

    /**
     * Method to set name of the category
     *
     * @param name - value to set for field {@link Category#name}.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Indicates whether some other {@link Category} is "equal to" this one.
     *
     * @param o - some other {@link Category} to check if they are the same.
     * @return {@code true}, if two categories are the same, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Category category = (Category) o;
        return Objects.equals(name, category.name) &&
                Objects.equals(parent, category.parent) &&
                Objects.equals(questions, category.questions) &&
                Objects.equals(subcategories, category.subcategories);
    }

    /**
     * Method to find a hash code value for the {@link Category}.
     *
     * @return hash code value for the category.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, parent, questions, subcategories);
    }

    /**
     * Method to generate string representation of the {@link Category}.
     *
     * @return string representation of the category.
     */
    @Override
    public String toString() {
        return "Category{"
                + super.toString()
                + "name='" + name + '\''
                + ", parent=" + parent
                + ", questions=" + questions
                + ", subcategories=" + subcategories
                + '}';
    }
}
