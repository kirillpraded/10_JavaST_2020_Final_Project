package by.praded.ask_and_go.service.impl;

import by.praded.ask_and_go.dao.exception.ConnectionPoolException;
import by.praded.ask_and_go.dao.exception.DaoException;
import by.praded.ask_and_go.data_provider.AnswerServiceImplDataProvider;
import by.praded.ask_and_go.entity.Answer;
import by.praded.ask_and_go.entity.Question;
import by.praded.ask_and_go.entity.User;
import by.praded.ask_and_go.service.AnswerService;
import by.praded.ask_and_go.service.Service;
import by.praded.ask_and_go.service.exception.EntityNotExistsException;
import by.praded.ask_and_go.service.exception.ValidationException;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Kiryl Praded
 * 26.01.2021
 */
@Test(dataProviderClass = AnswerServiceImplDataProvider.class)
public class AnswerServiceImplTest {
    private final AnswerService answerService = ServiceProvider.getInstance().takeService(Service.ANSWER);

    @Test(description = "testing successful creating an answer", dataProvider = "addAnswerValidData")
    public void successfulCreateAnswerTest(Long userId, String answerText, Long questionId) {
        Answer answer = new Answer();
        Question question = new Question();
        question.setId(questionId);
        User user = new User();
        user.setId(userId);
        answer.setQuestion(question);
        answer.setAuthor(user);
        answer.setText(answerText);
        boolean success = true;
        try {
            answerService.addAnswer(answer);
        } catch (DaoException | ConnectionPoolException | ValidationException e) {
            success = false;
        }
        Assert.assertTrue(success);
    }

    @Test(description = "expecting validation exception while creating an answer", dataProvider = "addAnswerInvalidData")
    public void createAnswerExpectValidationException(Long userId, String answerText, Long questionId) {
        Answer answer = new Answer();
        Question question = new Question();
        question.setId(questionId);
        User user = new User();
        user.setId(userId);
        answer.setQuestion(question);
        answer.setAuthor(user);
        answer.setText(answerText);
        Assert.assertThrows(ValidationException.class, () -> answerService.addAnswer(answer));

    }

    @Test(description = "testing successful updating `isCorrect`", dataProvider = "markCorrectTestData")
    public void markCorrectTest(Long answerId, Long questionId) {
        Answer answer = new Answer();
        Question question = new Question();
        question.setId(questionId);
        answer.setQuestion(question);
        answer.setId(answerId);
        boolean success = true;
        try {
            answerService.updateAnswerIsCorrect(answer);
        } catch (DaoException | ConnectionPoolException e) {
            success = false;
        }
        Assert.assertTrue(success);
    }

    @Test(description = "testing successful deleting answer", dataProvider = "toDeleteData")
    public void deleteAnswerTest(Long answerId) {
        boolean success = true;
        try {
            answerService.deleteAnswer(answerId);
        } catch (DaoException | ConnectionPoolException e) {
            success = false;
        }
        Assert.assertTrue(success);
    }

    @Test(description = "successful find answer by id test", dataProvider = "validAnswerIdentities")
    public void findAnswerByIdTest(Long answerId) {
        boolean success = true;
        try {
            answerService.findAnswerById(answerId);

        } catch (DaoException | EntityNotExistsException | ConnectionPoolException e) {
            success = false;
        }
        Assert.assertTrue(success);
    }

    @Test(description = "expecting EntityNotExists when trying to find answer", dataProvider = "invalidAnswerIdentities")
    public void findAnswerByIdExpectEntityNotExistsTest(Long answerId) {
        Assert.assertThrows(EntityNotExistsException.class, () -> answerService.findAnswerById(answerId));

    }

    @Test(description = "successful updateAnswerText test", dataProvider = "updateAnswerTextValidData")
    public void updateAnswerTextTest(Long id, String text) {
        Answer answer = new Answer();
        answer.setId(id);
        answer.setText(text);
        boolean success = true;
        try {
            answerService.updateAnswerText(answer);
        } catch (DaoException | ValidationException | ConnectionPoolException e) {
            success = false;
        }
        Assert.assertTrue(success);
    }

    @Test(description = "expecting validation exception while updating answer", dataProvider = "updateAnswerTextInvalidData")
    public void updateAnswerTextExpectValidationExceptionTest(Long id, String text) {
        Answer answer = new Answer();
        answer.setId(id);
        answer.setText(text);

        Assert.assertThrows(ValidationException.class, () -> answerService.updateAnswerText(answer));
    }

}
