package by.praded.ask_and_go.service;

import by.praded.ask_and_go.dao.exception.ConnectionPoolException;
import by.praded.ask_and_go.dao.exception.DaoException;
import by.praded.ask_and_go.entity.Answer;
import by.praded.ask_and_go.service.exception.EntityNotExistsException;
import by.praded.ask_and_go.service.exception.ValidationException;

/**
 * @author Kiryl Praded
 * 31.12.2020
 * <p>
 * Service with methods for actions on answers.
 * @see Answer
 * @see by.praded.ask_and_go.dao.AnswerDao
 * @see AbstractService
 */
public interface AnswerService extends AbstractService {
    /**
     * Declaration of method to add answer.
     *
     * @param answer - answer to add.
     * @throws ConnectionPoolException - can be thrown on interaction exception with connection.
     * @throws DaoException            - can be thrown on interaction exception with dao.
     */
    void addAnswer(Answer answer) throws ConnectionPoolException, DaoException, ValidationException;

    /**
     * Declaration of method to update answer `isCorrect` value.
     *
     * @param answer - answer to update.
     * @throws ConnectionPoolException - can be thrown on interaction exception with connection.
     * @throws DaoException            - can be thrown on interaction exception with dao.
     */
    void updateAnswerIsCorrect(Answer answer) throws ConnectionPoolException, DaoException;

    /**
     * Declaration of method to delete answer.
     *
     * @param answerId - answer identity to delete it by id.
     * @throws ConnectionPoolException - can be thrown on interaction exception with connection.
     * @throws DaoException            - can be thrown on interaction exception with dao.
     */
    void deleteAnswer(Long answerId) throws ConnectionPoolException, DaoException;

    /**
     * Declaration of method to find answer by identity.
     *
     * @param answerId - identity of the answer to find by.
     * @return answer found by identity.
     * @throws ConnectionPoolException  - can be thrown on interaction exception with connection.
     * @throws DaoException             - can be thrown on interaction exception with dao.
     * @throws EntityNotExistsException - can be thrown if answer with such identity don't exists.
     */
    Answer findAnswerById(Long answerId) throws ConnectionPoolException, DaoException, EntityNotExistsException;

    /**
     * Declaration of method to update answer text.
     *
     * @param answer - answer to update.
     * @throws ConnectionPoolException - can be thrown on interaction exception with connection.
     * @throws DaoException            - can be thrown on interaction exception with dao.
     */
    void updateAnswerText(Answer answer) throws ConnectionPoolException, DaoException, ValidationException;
}
