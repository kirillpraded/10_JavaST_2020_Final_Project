package by.praded.ask_and_go.dao;

import by.praded.ask_and_go.dao.exception.DaoException;
import by.praded.ask_and_go.entity.Answer;

import java.util.List;

/**
 * @author Kiryl Praded
 * 13.12.2020
 * <p>
 * Dao with methods for Answer.
 * @see Answer
 * @see Dao
 */
public interface AnswerDao extends Dao<Answer> {
    /**
     * Declaration of method to find list of answers by the question identity.
     *
     * @param questionId - identity of the question
     * @param page - number of page
     * @return list of answers found by the question id.
     * @throws DaoException - may occurs during the reading.
     * @see by.praded.ask_and_go.entity.Question
     */
    List<Answer> findByQuestionId(Long questionId, int page) throws DaoException;

    /**
     * Declaration of the method to make answer correct.
     *
     * @param answerId - identity of the answer to make it correct.
     * @throws DaoException - may occurs during the updating.
     */
    void makeCorrect(Long answerId) throws DaoException;
}
