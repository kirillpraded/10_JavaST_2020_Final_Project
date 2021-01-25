package by.praded.ask_and_go.dao;

import by.praded.ask_and_go.dao.exception.DaoException;
import by.praded.ask_and_go.entity.Question;

import java.util.List;

/**
 * @author Kiryl Praded
 * 13.12.2020
 * <p>
 * Dao with methods for question.
 * @see Question
 * @see Dao
 */
public interface QuestionDao extends Dao<Question> {

    /**
     * Declaration of the method to find questions by identity of category.
     *
     * @param categoryId - identity of category to search by.
     * @return list of questions in current category.
     * @throws DaoException - exception may occurs during the reading.
     * @see by.praded.ask_and_go.entity.Category
     */
    List<Question> findByCategoryId(Long categoryId) throws DaoException;

    /**
     * Declaration of the method to find questions by title.
     *
     * @param title - title to search by.
     * @return list of questions found by given title.
     * @throws DaoException - exception may occurs during the reading.
     */
    List<Question> findByTitle(String title) throws DaoException;

    /**
     * Declaration of the method to find questions by its tag identity.
     *
     * @param tagId - identity of the tag to search by.
     * @return list of questions that matches given tag.
     * @throws DaoException - exception may occurs during the reading.
     * @see by.praded.ask_and_go.entity.Tag
     */
    List<Question> findByTagId(Long tagId) throws DaoException;

    /**
     * Declaration of the method to close question.
     *
     * @param questionId - question identity to close.
     * @throws DaoException - exception may occurs during the updating.
     */
    void closeQuestion(Long questionId) throws DaoException;

    /**
     * Declaration of the method to mark that it contains correct answer.
     *
     * @param questionId - identity of the question to mark.
     * @throws DaoException - exception may occurs during the updating.
     * @see by.praded.ask_and_go.entity.Answer
     */
    void makeContainsCorrect(Long questionId) throws DaoException;
}
