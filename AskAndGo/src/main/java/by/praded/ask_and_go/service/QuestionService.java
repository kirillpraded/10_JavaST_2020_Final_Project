package by.praded.ask_and_go.service;

import by.praded.ask_and_go.dao.exception.ConnectionPoolException;
import by.praded.ask_and_go.dao.exception.DaoException;
import by.praded.ask_and_go.entity.Question;
import by.praded.ask_and_go.service.exception.EntityNotExistsException;
import by.praded.ask_and_go.service.exception.ValidationException;

import java.util.List;
import java.util.Set;

/**
 * @author Kiryl Praded
 * 27.12.2020
 * <p>
 * Service with methods for actions on questions.
 * @see Question
 * @see by.praded.ask_and_go.dao.QuestionDao
 * @see AbstractService
 */
public interface QuestionService extends AbstractService {
    /**
     * Declaration of method to find question by identity.
     *
     * @param id - identity of the question to find by.
     * @param page - page to find answers.
     * @return question found by identity.
     * @throws ConnectionPoolException  - can be thrown on interaction exception with connection.
     * @throws DaoException             - can be thrown on interaction exception with dao.
     * @throws EntityNotExistsException - can be thrown if question with such identity don't exists.
     */
    Question findQuestionById(Long id, int page) throws ConnectionPoolException, EntityNotExistsException, DaoException;

    /**
     * Declaration of method to find questions by identity of category.
     *
     * @param categoryId - identity of category to find by.
     * @return list of questions in concrete category.
     * @throws ConnectionPoolException - can be thrown on interaction exception with connection.
     * @throws DaoException            - can be thrown on interaction exception with dao.
     */
    List<Question> findQuestionsByCategoryId(Long categoryId) throws ConnectionPoolException, DaoException;

    /**
     * Declaration of method to search questions by its title.
     *
     * @param query - title of the question to search by.
     * @return set of questions where title like given.
     * @throws ConnectionPoolException - can be thrown on interaction exception with connection.
     * @throws DaoException            - can be thrown on interaction exception with dao.
     */
    Set<Question> searchQuestionsByTitle(String query) throws ConnectionPoolException, DaoException;

    /**
     * Declaration of method to create question.
     *
     * @param question - question to create.
     * @return generated identity of created question.
     * @throws ConnectionPoolException - can be thrown on interaction exception with connection.
     * @throws DaoException            - can be thrown on interaction exception with dao.
     */
    Long createQuestion(Question question) throws ConnectionPoolException, DaoException, ValidationException;

    /**
     * Declaration of method to update question value `isClosed`.
     *
     * @param qid - identity of question to update.
     * @throws ConnectionPoolException - can be thrown on interaction exception with connection.
     * @throws DaoException            - can be thrown on interaction exception with dao.
     */
    void updateQuestionIsClosed(Long qid) throws ConnectionPoolException, DaoException;

    /**
     * Declaration of method to delete question by identity.
     *
     * @param questionId - identity of question to delete.
     * @throws ConnectionPoolException - can be thrown on interaction exception with connection.
     * @throws DaoException            - can be thrown on interaction exception with dao.
     */
    void deleteQuestion(Long questionId) throws ConnectionPoolException, DaoException;

    /**
     * Declaration of method to update question.
     *
     * @param question - question to update
     * @throws ConnectionPoolException - can be thrown on interaction exception with connection.
     * @throws DaoException            - can be thrown on interaction exception with dao.
     */
    void updateQuestion(Question question) throws ConnectionPoolException, DaoException, ValidationException;
}
