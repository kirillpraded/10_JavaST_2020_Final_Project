package by.praded.ask_and_go.entity;

import java.time.LocalDateTime;

/**
 * @author Kiryl Praded
 * 15.12.2020
 */
public abstract class Message extends Entity{
    private String text;
    private User author;
    private LocalDateTime date;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
