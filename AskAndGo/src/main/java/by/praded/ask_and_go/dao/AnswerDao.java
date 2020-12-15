package by.praded.ask_and_go.dao;

import by.praded.ask_and_go.entity.Answer;
import by.praded.ask_and_go.entity.Question;

import java.util.List;

/**
 * @author Kiryl Praded
 * 13.12.2020
 */
public interface AnswerDao extends Dao<Answer> {
    List<Answer> findByQuestionId(Long questionId);
}
