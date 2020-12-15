package by.praded.ask_and_go.entity;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Kiryl Praded
 * 04.12.2020
 */
public class User extends Entity {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    //дата регистрации пользователя, возможно убдет изменен тип.
    private LocalDateTime regDate;
    private Role role;
    private List<Question> userQuestions;
    private List<Answer> userAnswers;
    private String profileImage;

    public User() {

    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public LocalDateTime getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDateTime regDate) {
        this.regDate = regDate;
    }

    public List<Question> getUserQuestions() {
        return userQuestions;
    }

    public void setUserQuestions(List<Question> userQuestions) {
        this.userQuestions = userQuestions;
    }

    public List<Answer> getUserAnswers() {
        return userAnswers;
    }

    public void setUserAnswers(List<Answer> userAnswers) {
        this.userAnswers = userAnswers;
    }

}