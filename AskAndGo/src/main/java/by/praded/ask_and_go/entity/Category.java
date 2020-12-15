package by.praded.ask_and_go.entity;

import java.util.List;

/**
 * @author Kiryl Praded
 * 09.12.2020
 */
public class Category extends Entity {
    private String name;
    private Category parent;
    private List<Question> questions;
    public Category() {
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
