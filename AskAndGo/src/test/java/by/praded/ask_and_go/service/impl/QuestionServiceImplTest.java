package by.praded.ask_and_go.service.impl;

import by.praded.ask_and_go.dao.exception.ConnectionPoolException;
import by.praded.ask_and_go.dao.exception.DaoException;
import by.praded.ask_and_go.data_provider.QuestionServiceImplDataProvider;
import by.praded.ask_and_go.entity.Category;
import by.praded.ask_and_go.entity.Question;
import by.praded.ask_and_go.entity.Tag;
import by.praded.ask_and_go.entity.User;
import by.praded.ask_and_go.service.QuestionService;
import by.praded.ask_and_go.service.Service;
import by.praded.ask_and_go.service.TagService;
import by.praded.ask_and_go.service.exception.EntityNotExistsException;
import by.praded.ask_and_go.service.exception.ValidationException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * @author Kiryl Praded
 * 25.01.2021
 */
@Test(dataProviderClass = QuestionServiceImplDataProvider.class)
public class QuestionServiceImplTest {
    private final QuestionService questionService = ServiceProvider.getInstance().takeService(Service.QUESTION);
    private final TagService tagService = ServiceProvider.getInstance().takeService(Service.TAG);

    @Test(description = "test successful find by identity", dataProvider = "existingQuestionsData")
    public void findQuestionByIdTest(Long qid) {
        try {
            questionService.findQuestionById(qid);
            Assert.assertTrue(true);
        } catch (DaoException | ConnectionPoolException | EntityNotExistsException e) {
            Assert.fail();
        }
    }

    @Test(description = "test find by identity expect EntityNotExistsException", dataProvider = "nonExistingIdentities")
    public void findQuestionByIdExpectEntityNotExistsExceptionTest(Long id) {
        try {
            questionService.findQuestionById(id);
            Assert.fail();
        } catch (EntityNotExistsException e) {
            Assert.assertTrue(true);
        } catch (DaoException | ConnectionPoolException e) {
            Assert.fail();
        }
    }

    @Test(description = "test find by category identity", dataProvider = "categoryIdentities")
    public void findQuestionsByCategoryIdTest(Long categoryId) {
        boolean success = true;
        try {
            questionService.findQuestionsByCategoryId(categoryId);

        } catch (DaoException | ConnectionPoolException e) {
            success = false;
        }
        Assert.assertTrue(success);
    }

    @Test(description = "testing searching", dataProvider = "queryForSearch")
    public void searchQuestionsByTitleAndTagsTest(String query) {
        try {
            questionService.searchQuestionsByTitle(query);
            Assert.assertTrue(true);
        } catch (DaoException | ConnectionPoolException e) {
            Assert.fail();
        }
    }

    @Test(description = "successful creating test", dataProvider = "validQuestionData")
    public void successfulCreateQuestionTest(Long userId, Long categoryId, String title, String text, String tags) {
        Question question = new Question();
        Category category = new Category();
        category.setId(categoryId);
        User user = new User();
        user.setId(userId);
        question.setAuthor(user);
        question.setCategory(category);
        question.setTitle(title);
        question.setText(text);
        List<Tag> tagsList = tagService.parseTags(tags);
        question.setTags(tagsList);

        try {
            questionService.createQuestion(question);
            Assert.assertTrue(true);
        } catch (DaoException | ConnectionPoolException | ValidationException e) {
            Assert.fail();
        }
    }

    @Test(description = "expecting validation exception during creating question", dataProvider = "invalidQuestionData")
    public void createQuestionExpectValidationExceptionTest(Long userId, Long categoryId, String title, String text, String tags) {
        Question question = new Question();
        Category category = new Category();
        category.setId(categoryId);
        User user = new User();
        user.setId(userId);
        question.setAuthor(user);
        question.setCategory(category);
        question.setTitle(title);
        question.setText(text);
        List<Tag> tagsList = tagService.parseTags(tags);
        question.setTags(tagsList);

        Assert.assertThrows(ValidationException.class, () -> questionService.createQuestion(question));
    }

    @Test(description = "testing updating question `isClosed` param", dataProvider = "validQuestionIdentities")
    public void updateQuestionIsClosedTest(Long id) {
        boolean success = true;
        try {
            questionService.updateQuestionIsClosed(id);

        } catch (DaoException | ConnectionPoolException e) {
            success = false;
        }
        Assert.assertTrue(success);
    }

    @Test(description = "testing deleting question", dataProvider = "questionIdToDelete")
    public void deleteQuestionTest(Long id) {
        boolean success = true;
        try {
            questionService.deleteQuestion(id);
        } catch (DaoException | ConnectionPoolException e) {
            success = false;
        }
        Assert.assertTrue(success);
    }

    @Test(description = "successful question updating test", dataProvider = "updateQuestionValidData")
    public void successfulUpdateQuestionTest(Long qid, String qTitle, String qText) {
        Question question = new Question();
        question.setId(qid);
        question.setText(qText);
        question.setTitle(qTitle);
        boolean success = true;
        try {
            questionService.updateQuestion(question);
        } catch (DaoException | ValidationException | ConnectionPoolException e) {
            success = false;
        }
        Assert.assertTrue(success);
    }

    @Test(description = "expecting validation exception during updating question", dataProvider = "updateQuestionInvalidData")
    public void updateQuestionExpectValidationExceptionTest(Long identity, String title, String text) {
        Question question = new Question();
        question.setId(identity);
        question.setText(text);
        question.setTitle(title);
        Assert.assertThrows(ValidationException.class, () -> questionService.updateQuestion(question));
    }


}
