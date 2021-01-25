package by.praded.ask_and_go.entity;

import java.util.Objects;

/**
 * @author Kiryl Praded
 * 10.12.2020
 * <p>
 * Representation of the answer.
 * @see Message
 */
public class Answer extends Message {
    /**
     * The question that was answered.
     *
     * @see Question
     */
    private Question question;

    /**
     * Boolean field that marks is the answer is correct.
     */
    private boolean isCorrect;

    /**
     * Method to get value of the field {@link Answer#question}.
     *
     * @return value of the field {@link Answer#question}.
     */
    public Question getQuestion() {
        return question;
    }

    /**
     * Method to set value to the field {@link Answer#question}.
     *
     * @param q - value to set to {@link Answer#question}.
     */
    public void setQuestion(Question q) {
        this.question = q;
    }

    /**
     * Method to get value of the field {@link Answer#isCorrect}.
     *
     * @return value of the field {@link Answer#isCorrect}.
     */
    public boolean isCorrect() {
        return isCorrect;
    }

    /**
     * Method to set value to the field {@link Answer#isCorrect}.
     *
     * @param correct - field that marks is the answer is correct/incorrect.
     */
    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    /**
     * Indicates whether some other {@link Answer} is "equal to" this one.
     *
     * @param o - some other {@link Answer} to check if they are the same.
     * @return {@code true}, if two answers are the same, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Answer answer = (Answer) o;
        return isCorrect == answer.isCorrect &&
                Objects.equals(question, answer.question);
    }

    /**
     * Method to find a hash code value for the {@link Answer}.
     *
     * @return hash code value for the answer.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), question, isCorrect);
    }

    /**
     * Method to generate string representation of the {@link Answer}.
     *
     * @return string representation of the answer.
     */
    @Override
    public String toString() {
        return "Answer{"
                + super.toString()
                + ", question="
                + question
                + ", isCorrect="
                + isCorrect + '}';
    }
}
