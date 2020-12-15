package by.praded.ask_and_go.entity;

import java.util.List;

/**
 * @author Kiryl Praded
 * 12.12.2020
 */
public class Tag extends Entity {
    private String text;
    private List<Question> questions;

    public Tag() {
    }

    public String getText() {
        return text;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public void setText(String tagText) {
        this.text = tagText;
    }
}
