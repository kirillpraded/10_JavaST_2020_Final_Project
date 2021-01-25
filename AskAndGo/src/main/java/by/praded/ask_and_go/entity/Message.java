package by.praded.ask_and_go.entity;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author Kiryl Praded
 * 15.12.2020
 * <p>
 * Representation of an abstract message.
 * @see Entity
 */
public abstract class Message extends Entity {
    /**
     * Message content.
     */
    private String text;

    /**
     * Author of the message.
     *
     * @see User
     */
    private User author;

    /**
     * Message sending date.
     *
     * @see LocalDateTime
     */
    private LocalDateTime date;

    /**
     * Method to get value of the field {@link Message#text}.
     *
     * @return content of the message {@link Message#text}.
     */
    public String getText() {
        return text;
    }

    /**
     * Method to set value to the field {@link Message#text}.
     *
     * @param text - value for the message content field.
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Method to get value of the field {@link Message#author}.
     *
     * @return author of the message.
     */
    public User getAuthor() {
        return author;
    }

    /**
     * Method to set value to the field {@link Message#author}.
     *
     * @param author - {@link Message#author} of the message.
     */
    public void setAuthor(User author) {
        this.author = author;
    }

    /**
     * Method to get value of the field {@link Message#date}.
     *
     * @return message sending date.
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * Method to set value to the field {@link Message#date}.
     *
     * @param date - {@link Message#author} of the message.
     */
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    /**
     * Indicates whether some other {@link Message} is "equal to" this one.
     *
     * @param o - some other {@link Message} to check if they are the same.
     * @return {@code true}, if two messages are the same, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Message message = (Message) o;
        return Objects.equals(text, message.text) &&
                Objects.equals(author, message.author) &&
                Objects.equals(date, message.date);
    }

    /**
     * Method to find a hash code value for the {@link Message}.
     *
     * @return hash code value for the message.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), text, author, date);
    }

    /**
     * Method to generate string representation of the {@link Message}.
     *
     * @return string representation of the answer.
     */
    @Override
    public String toString() {
        return "Message{"
                + super.toString()
                + ", text='" + text
                + '\'' + ", author="
                + author + ", date="
                + date + '}';
    }
}
