package by.praded.ask_and_go.service.impl;

import by.praded.ask_and_go.dao.AnswerDao;
import by.praded.ask_and_go.dao.AvailableDao;
import by.praded.ask_and_go.dao.QuestionDao;
import by.praded.ask_and_go.dao.Transaction;
import by.praded.ask_and_go.dao.TagDao;
import by.praded.ask_and_go.dao.UserDao;
import by.praded.ask_and_go.dao.CategoryDao;
import by.praded.ask_and_go.dao.exception.ConnectionPoolException;
import by.praded.ask_and_go.dao.exception.DaoException;
import by.praded.ask_and_go.dao.sql.TransactionFactory;
import by.praded.ask_and_go.entity.Answer;
import by.praded.ask_and_go.entity.Question;
import by.praded.ask_and_go.entity.Tag;
import by.praded.ask_and_go.service.AbstractService;
import by.praded.ask_and_go.service.QuestionService;
import by.praded.ask_and_go.service.exception.EntityNotExistsException;
import by.praded.ask_and_go.service.exception.ValidationException;
import by.praded.ask_and_go.service.util.QuestionValidator;
import by.praded.ask_and_go.service.util.TagValidator;

import java.util.*;

/**
 * @author Kiryl Praded
 * 27.12.2020
 * <p>
 * Implementation of question service.
 * @see Question
 * @see QuestionService
 * @see AbstractService
 */
public class QuestionServiceImpl implements QuestionService {

    /**
     * Method to find question by identity.
     *
     * @param id - identity of the question to find by.
     * @param page - page of answers to find by.
     * @return question found by identity.
     * @throws ConnectionPoolException  - can be thrown on interaction exception with connection.
     * @throws DaoException             - can be thrown on interaction exception with dao.
     * @throws EntityNotExistsException - can be thrown if question with such identity don't exists.
     */
    @Override
    public Question findQuestionById(Long id, int page) throws ConnectionPoolException, EntityNotExistsException, DaoException {
        Transaction transaction = null;
        try {
            transaction = TransactionFactory.getInstance().createTransaction(true);
            QuestionDao questionDao = transaction.createDao(AvailableDao.QUESTION);
            TagDao tagDao = transaction.createDao(AvailableDao.TAG);
            UserDao userDao = transaction.createDao(AvailableDao.USER);
            CategoryDao categoryDao = transaction.createDao(AvailableDao.CATEGORY);
            AnswerDao answerDao = transaction.createDao(AvailableDao.ANSWER);


            Optional<Question> optionalQuestion = Optional.ofNullable(questionDao.read(id));
            if (!optionalQuestion.isPresent()) {
                throw new EntityNotExistsException();
            }
            Question question = optionalQuestion.get();
            question.setAuthor(userDao.read(question.getAuthor().getId()));

            List<Answer> answers = answerDao.findByQuestionId(id, page);

            for (Answer answer : answers) {
                answer.setAuthor(userDao.read(answer.getAuthor().getId()));
            }

            question.setCategory(categoryDao.read(question.getCategory().getId()));
            question.setAnswers(answers);
            question.setTags(tagDao.findByQuestionId(id));
            return question;
        } finally {
            if (transaction != null) {
                transaction.close();
            }
        }

    }

    /**
     * Method to find questions by identity of category.
     *
     * @param categoryId - identity of category to find by.
     * @return list of questions in concrete category.
     * @throws ConnectionPoolException - can be thrown on interaction exception with connection.
     * @throws DaoException            - can be thrown on interaction exception with dao.
     */
    @Override
    public List<Question> findQuestionsByCategoryId(Long categoryId) throws ConnectionPoolException, DaoException {
        Transaction transaction = null;
        try {
            transaction = TransactionFactory.getInstance().createTransaction(true);

            QuestionDao dao = transaction.createDao(AvailableDao.QUESTION);
            List<Question> questions = dao.findByCategoryId(categoryId);

            UserDao userDao = transaction.createDao(AvailableDao.USER);
            for (Question question : questions) {
                question.setAuthor(userDao.read(question.getId()));
            }

            return questions;
        } finally {
            if (transaction != null) {
                transaction.close();
            }
        }

    }

    /**
     * Method to search questions by its title.
     *
     * @param query - title of the question to search by.
     * @return set of questions where title like given.
     * @throws ConnectionPoolException - can be thrown on interaction exception with connection.
     * @throws DaoException            - can be thrown on interaction exception with dao.
     */
    @Override
    public Set<Question> searchQuestionsByTitle(String query) throws ConnectionPoolException, DaoException {
        Transaction transaction = null;
        try {
            transaction = TransactionFactory.getInstance().createTransaction(true);

            QuestionDao questionDao = transaction.createDao(AvailableDao.QUESTION);

            Set<Question> questions = new HashSet<>(questionDao.findByTitle(query));

            TagDao tagDao = transaction.createDao(AvailableDao.TAG);
            for (Tag tag : tagDao.findByText(query)) {
                questions.addAll(questionDao.findByTagId(tag.getId()));
            }


            UserDao userDao = transaction.createDao(AvailableDao.USER);
            for (Question question : questions) {
                question.setAuthor(userDao.read(question.getId()));
            }

            return questions;
        } finally {
            if (transaction != null) {
                transaction.close();
            }
        }
    }

    /**
     * Method to create question.
     *
     * @param question - question to create.
     * @return generated identity of created question.
     * @throws ConnectionPoolException - can be thrown on interaction exception with connection.
     * @throws DaoException            - can be thrown on interaction exception with dao.
     */
    @Override
    public Long createQuestion(Question question) throws ConnectionPoolException, DaoException, ValidationException {
        Transaction transaction = null;
        try {

            boolean textIsValid = QuestionValidator.validateText(question.getText());
            boolean titleIsValid = QuestionValidator.validateTitle(question.getTitle());
            boolean tagsAreValid = TagValidator.validateNames(question.getTags());
            Map<String, String> validationErrorMap = new HashMap<>();

            if (!textIsValid) {
                validationErrorMap.put("text_error", "question.text.validation.error");
            }
            if (!titleIsValid) {
                validationErrorMap.put("title_error", "question.title.validation.error");
            }
            if (!tagsAreValid) {
                validationErrorMap.put("tags_error", "tags.validation.error");
            }

            if (!(textIsValid && titleIsValid && tagsAreValid)) {
                throw new ValidationException(validationErrorMap);
            }
            transaction = TransactionFactory.getInstance().createTransaction(false);
            TagDao tagDao = transaction.createDao(AvailableDao.TAG);
            QuestionDao questionDao = transaction.createDao(AvailableDao.QUESTION);
            try {
                for (Tag tag :
                        question.getTags()) {
                    tag.setId(tagDao.create(tag));
                }

                return questionDao.create(question);
            } catch (DaoException e) {
                transaction.rollback();
                throw new DaoException(e);
            }
        } finally {
            if (transaction != null) {
                transaction.close();
            }
        }
    }

    /**
     * Method to update question value `isClosed`.
     *
     * @param id - identity of question to update.
     * @throws ConnectionPoolException - can be thrown on interaction exception with connection.
     * @throws DaoException            - can be thrown on interaction exception with dao.
     */
    @Override
    public void updateQuestionIsClosed(Long id) throws ConnectionPoolException, DaoException {
        Transaction transaction = null;
        try {
            transaction = TransactionFactory.getInstance().createTransaction(true);
            QuestionDao questionDao = transaction.createDao(AvailableDao.QUESTION);
            questionDao.closeQuestion(id);
        } finally {
            if (transaction != null) {
                transaction.close();
            }
        }
    }

    /**
     * Method to delete question by identity.
     *
     * @param qid - identity of question to delete.
     * @throws ConnectionPoolException - can be thrown on interaction exception with connection.
     * @throws DaoException            - can be thrown on interaction exception with dao.
     */
    @Override
    public void deleteQuestion(Long qid) throws ConnectionPoolException, DaoException {
        Transaction transaction = null;
        try {
            transaction = TransactionFactory.getInstance().createTransaction(true);
            QuestionDao questionDao = transaction.createDao(AvailableDao.QUESTION);

            questionDao.delete(qid);
        } finally {
            if (transaction != null) {
                transaction.close();
            }
        }
    }

    /**
     * Method to update question.
     *
     * @param question - question to update
     * @throws ConnectionPoolException - can be thrown on interaction exception with connection.
     * @throws DaoException            - can be thrown on interaction exception with dao.
     */
    @Override
    public void updateQuestion(Question question) throws ConnectionPoolException, DaoException, ValidationException {
        Transaction transaction = null;
        try {
            boolean textIsValid = QuestionValidator.validateText(question.getText());
            boolean titleIsValid = QuestionValidator.validateTitle(question.getTitle());
            Map<String, String> validationErrorMap = new HashMap<>();

            if (!textIsValid) {
                validationErrorMap.put("text_error", "question.text.validation.error");
            }
            if (!titleIsValid) {
                validationErrorMap.put("title_error", "question.title.validation.error");
            }
            if (!(textIsValid && titleIsValid)) {
                throw new ValidationException(validationErrorMap);
            }

            transaction = TransactionFactory.getInstance().createTransaction(true);
            QuestionDao questionDao = transaction.createDao(AvailableDao.QUESTION);

            questionDao.update(question);
        } finally {
            if (transaction != null) {
                transaction.close();
            }
        }
    }
}
