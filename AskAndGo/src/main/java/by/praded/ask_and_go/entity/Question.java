package by.praded.ask_and_go.entity;

import java.time.LocalDateTime;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @author Kiryl Praded
 * 10.12.2020
 */
public class Question extends Message {
    private String title;
    private Category category;
    private String imageName;
    private boolean containsCorrectAnswer;
    private boolean isClosed;
    private List<Answer> answers;
    private List<Tag> tags;

    public Question() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }



    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public boolean isContainsCorrectAnswer() {
        return containsCorrectAnswer;
    }

    public void setContainsCorrectAnswer(boolean containsCorrectAnswer) {
        this.containsCorrectAnswer = containsCorrectAnswer;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }
}
