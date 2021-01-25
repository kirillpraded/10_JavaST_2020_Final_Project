package by.praded.ask_and_go.entity;

import java.util.List;
import java.util.Objects;

/**
 * @author Kiryl Praded
 * 12.12.2020
 * <p>
 * Representation of tag.
 * @see Entity
 */
public class Tag extends Entity {
    /**
     * Content of the tag.
     */
    private String text;

    /**
     * List of questions with current tag.
     *
     * @see Question
     */
    private List<Question> questions;

    /**
     * Method to get tag content.
     *
     * @return value of the field {@link Tag#text}.
     */
    public String getText() {
        return text;
    }

    /**
     * Method to set {@link Tag#text} of the text.
     *
     * @param tagText - text of the tag.
     */
    public void setText(String tagText) {
        this.text = tagText;
    }

    /**
     * Method to get list of questions that corresponds current tag.
     *
     * @return value of the field {@link Tag#questions}.
     */
    public List<Question> getQuestions() {
        return questions;
    }

    /**
     * Method to set field {@link Tag#questions} of the tag.
     *
     * @param questions - list of questions that corresponds current tag.
     */
    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    /**
     * Indicates whether some other {@link Tag} is "equal to" this one.
     *
     * @param o - some other {@link Tag} to check if they are the same.
     * @return {@code true}, if two tags are the same, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Tag tag = (Tag) o;
        return Objects.equals(text, tag.text) &&
                Objects.equals(questions, tag.questions);
    }

    /**
     * Method to find a hash code value for the {@link Tag}.
     *
     * @return hash code value for the tag.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), text, questions);
    }

    /**
     * Method to generate string representation of the {@link Tag}.
     *
     * @return string representation of the tag.
     */
    @Override
    public String toString() {
        return "Tag{"
                + super.toString()
                + "text='" + text + '\''
                + ", questions=" + questions
                + '}';
    }
}
