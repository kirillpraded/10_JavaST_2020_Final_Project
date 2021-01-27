package by.praded.ask_and_go.service.impl;

import by.praded.ask_and_go.dao.AnswerDao;
import by.praded.ask_and_go.dao.AvailableDao;
import by.praded.ask_and_go.dao.QuestionDao;
import by.praded.ask_and_go.dao.Transaction;
import by.praded.ask_and_go.dao.exception.ConnectionPoolException;
import by.praded.ask_and_go.dao.exception.DaoException;
import by.praded.ask_and_go.dao.sql.TransactionFactory;
import by.praded.ask_and_go.entity.Answer;
import by.praded.ask_and_go.service.AnswerService;
import by.praded.ask_and_go.service.exception.EntityNotExistsException;
import by.praded.ask_and_go.service.exception.ValidationException;
import by.praded.ask_and_go.service.util.AnswerValidator;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author Kiryl Praded
 * 31.12.2020
 * <p>
 * Implementation of answer service.
 * @see Answer
 * @see AnswerService
 */
public class AnswerServiceImpl implements AnswerService {

    /**
     * Method to add answer.
     *
     * @param answer - answer to add.
     * @throws ConnectionPoolException - can be thrown on interaction exception with connection.
     * @throws DaoException            - can be thrown on interaction exception with dao.
     */
    @Override
    public void addAnswer(Answer answer) throws ConnectionPoolException, DaoException, ValidationException {
        Transaction transaction = null;
        try {
            if (!AnswerValidator.validateAnswerText(answer.getText())) {
                Map<String, String> validationErrorMap = new HashMap<>();
                validationErrorMap.put("error_message", "answer.validation.error");
                throw new ValidationException(validationErrorMap);
            }

            transaction = TransactionFactory.getInstance().createTransaction(true);
            AnswerDao answerDao = transaction.createDao(AvailableDao.ANSWER);
            answerDao.create(answer);
        } finally {
            if (transaction != null) {
                transaction.close();
            }
        }
    }

    /**
     * Method to update answer `isCorrect` value.
     *
     * @param answer - answer to update.
     * @throws ConnectionPoolException - can be thrown on interaction exception with connection.
     * @throws DaoException            - can be thrown on interaction exception with dao.
     */
    @Override
    public void updateAnswerIsCorrect(Answer answer) throws ConnectionPoolException, DaoException {
        Transaction transaction = null;
        try {
            transaction = TransactionFactory.getInstance().createTransaction(true);
            AnswerDao answerDao = transaction.createDao(AvailableDao.ANSWER);
            QuestionDao questionDao = transaction.createDao(AvailableDao.QUESTION);

            questionDao.makeContainsCorrect(answer.getQuestion().getId());
            answerDao.makeCorrect(answer.getId());
        } finally {
            if (transaction != null) {
                transaction.close();
            }
        }
    }

    /**
     * Method to delete answer.
     *
     * @param answerId - answer identity to delete it by id.
     * @throws ConnectionPoolException - can be thrown on interaction exception with connection.
     * @throws DaoException            - can be thrown on interaction exception with dao.
     */
    @Override
    public void deleteAnswer(Long answerId) throws ConnectionPoolException, DaoException {
        Transaction transaction = null;
        try {
            transaction = TransactionFactory.getInstance().createTransaction(true);
            AnswerDao answerDao = transaction.createDao(AvailableDao.ANSWER);

            answerDao.delete(answerId);
        } finally {
            if (transaction != null) {
                transaction.close();
            }
        }
    }

    /**
     * Method to find answer by identity.
     *
     * @param answerId - identity of the answer to find by.
     * @return answer found by identity.
     * @throws ConnectionPoolException  - can be thrown on interaction exception with connection.
     * @throws DaoException             - can be thrown on interaction exception with dao.
     * @throws EntityNotExistsException - can be thrown if answer with such identity don't exists.
     */
    @Override
    public Answer findAnswerById(Long answerId) throws ConnectionPoolException, DaoException, EntityNotExistsException {
        Transaction transaction = null;
        try {
            transaction = TransactionFactory.getInstance().createTransaction(true);
            AnswerDao answerDao = transaction.createDao(AvailableDao.ANSWER);
            Optional<Answer> answer = Optional.ofNullable(answerDao.read(answerId));
            if (answer.isPresent()) {
                return answer.get();
            }
            throw new EntityNotExistsException();
        } finally {
            if (transaction != null) {
                transaction.close();
            }
        }
    }

    /**
     * Method to update answer text.
     *
     * @param answer - answer to update.
     * @throws ConnectionPoolException - can be thrown on interaction exception with connection.
     * @throws DaoException            - can be thrown on interaction exception with dao.
     */
    @Override
    public void updateAnswerText(Answer answer) throws ConnectionPoolException, DaoException, ValidationException {
        Transaction transaction = null;
        try {

            if (!AnswerValidator.validateAnswerText(answer.getText())) {
                Map<String, String> validationErrorMap = new HashMap<>();
                validationErrorMap.put("message", "answer.validation.error");
                throw new ValidationException(validationErrorMap);
            }

            transaction = TransactionFactory.getInstance().createTransaction(true);
            AnswerDao answerDao = transaction.createDao(AvailableDao.ANSWER);

            answerDao.update(answer);
        } finally {
            if (transaction != null) {
                transaction.close();
            }
        }
    }
}
