package by.praded.ask_and_go.entity;

/**
 * @author Kiryl Praded
 * 10.12.2020
 */
public class Answer extends Message {
    private Question question;
    private boolean isCorrect;


    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question q) {
        this.question = q;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }
}
