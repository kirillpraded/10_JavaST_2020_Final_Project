package by.praded.ask_and_go.entity;

import java.util.List;
import java.util.Objects;

/**
 * @author Kiryl Praded
 * 10.12.2020
 * <p>
 * Representation of the question.
 * @see Message
 */
public class Question extends Message {
    /**
     * Title of the question
     */
    private String title;

    /**
     * Category of the question.
     *
     * @see Category
     */
    private Category category;

    /**
     * Name of the image, pinned to this question.
     */
    private String imageName;

    /**
     * Marks that question is contains correct answer.
     */
    private boolean containsCorrectAnswer;

    /**
     * Marks that current question is closed.
     */
    private boolean isClosed;

    /**
     * List of answers.
     *
     * @see Answer
     */
    private List<Answer> answers;

    /**
     * List of tags of the question.
     *
     * @see Tag
     */
    private List<Tag> tags;

    /**
     * Method to get the title of the question.
     *
     * @return value of the field {@link Question#title}.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Method to set {@link Question#title} of the question.
     *
     * @param title - title to set.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Method to get question category.
     *
     * @return value of the field {@link Question#category}.
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Method to set {@link Question#category} of the question.
     *
     * @param category - category to set.
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * Method to get question answers.
     *
     * @return value of the field {@link Question#answers}.
     * @see Answer
     */
    public List<Answer> getAnswers() {
        return answers;
    }


    /**
     * Method to set {@link Question#answers} of the question.
     *
     * @param answers - answers to set.
     */
    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    /**
     * Method to get name of the image that pinned to the current question.
     *
     * @return value of the field {@link Question#imageName}.
     */
    public String getImageName() {
        return imageName;
    }

    /**
     * Method to set {@link Question#imageName} of the question.
     *
     * @param imageName - name of the image to set.
     */
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    /**
     * Method to get list of tags that corresponds current question.
     *
     * @return value of the field {@link Question#tags}.
     */
    public List<Tag> getTags() {
        return tags;
    }

    /**
     * Method to set {@link Question#tags} of the question.
     *
     * @param tags - list of tags to set.
     */
    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    /**
     * Method to get value of the field {@link Question#containsCorrectAnswer}.
     *
     * @return value of the field {@link Question#containsCorrectAnswer}.
     */
    public boolean isContainsCorrectAnswer() {
        return containsCorrectAnswer;
    }

    /**
     * Method to set field {@link Question#containsCorrectAnswer}.
     *
     * @param containsCorrectAnswer - value to field that marks that question contains correct answer or no.
     */
    public void setContainsCorrectAnswer(boolean containsCorrectAnswer) {
        this.containsCorrectAnswer = containsCorrectAnswer;
    }

    /**
     * Method to get value of the field {@link Question#isClosed}.
     *
     * @return value of the field {@link Question#isClosed}.
     */
    public boolean isClosed() {
        return isClosed;
    }

    /**
     * Method to set field {@link Question#isClosed} .
     *
     * @param closed - value to field that marks if question is closed.
     */
    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    /**
     * Indicates whether some other {@link Question} is "equal to" this one.
     *
     * @param o - some other {@link Question} to check if they are the same.
     * @return {@code true}, if two questions are the same, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Question question = (Question) o;
        return containsCorrectAnswer == question.containsCorrectAnswer &&
                isClosed == question.isClosed &&
                Objects.equals(title, question.title) &&
                Objects.equals(category, question.category) &&
                Objects.equals(imageName, question.imageName) &&
                Objects.equals(answers, question.answers) &&
                Objects.equals(tags, question.tags);
    }

    /**
     * Method to find a hash code value for the {@link Question}.
     *
     * @return hash code value for the question.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title, category, imageName, containsCorrectAnswer, isClosed, answers, tags);
    }

    /**
     * Method to generate string representation of the {@link Question}.
     *
     * @return string representation of the question.
     */
    @Override
    public String toString() {
        return "Question{"
                + super.toString()
                + "title='" + title
                + '\'' + ", category=" + category
                + ", imageName='" + imageName
                + '\'' + ", containsCorrectAnswer=" + containsCorrectAnswer
                + ", isClosed=" + isClosed
                + ", answers=" + answers
                + ", tags=" + tags + '}';
    }
}
