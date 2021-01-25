package by.praded.ask_and_go.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * @author Kiryl Praded
 * 04.12.2020
 * <p>
 * Representation of the User.
 * @see Entity
 */
public class User extends Entity {

    /**
     * Username.
     */
    private String username;

    /**
     * User's password.
     */
    private String password;

    /**
     * User's first name.
     */
    private String firstName;

    /**
     * User's last name.
     */
    private String lastName;

    /**
     * Email of the user.
     */
    private String email;

    /**
     * Registration date of the user.
     */
    private LocalDateTime regDate;

    /**
     * Role of the user.
     */
    private Role role;

    /**
     * List of questions that user asked.
     */
    private List<Question> userQuestions;

    /**
     * List of answers written by the user.
     */
    private List<Answer> userAnswers;

    /**
     * Name of the profile image of the user.
     */
    private String profileImage;

    /**
     * Method to get username.
     *
     * @return value of the field {@link User#username}.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Method to set username.
     *
     * @param username - value to set for field {@link User#username}.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Method to get password.
     *
     * @return value of the field {@link User#password}.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Method to set password.
     *
     * @param password - value to set for field {@link User#password}.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Method to get first name.
     *
     * @return value of the field {@link User#firstName}.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Method to set first name.
     *
     * @param firstName - value to set for field {@link User#firstName}.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Method to get last name.
     *
     * @return value of the field {@link User#lastName}.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Method to set last name.
     *
     * @param lastName - value to set for field {@link User#lastName}.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Method to get email.
     *
     * @return value of the field {@link User#email}.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Method to set email.
     *
     * @param email - value to set for field {@link User#email}.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Method to get user role.
     *
     * @return value of the field {@link User#role}.
     */
    public Role getRole() {
        return role;
    }

    /**
     * Method to set user role.
     *
     * @param role - value to set for field {@link User#role}.
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Method to get user's profile image.
     *
     * @return value of the field {@link User#profileImage}.
     */
    public String getProfileImage() {
        return profileImage;
    }

    /**
     * Method to set user's profile image.
     *
     * @param profileImage - value to set for field {@link User#profileImage}.
     */
    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    /**
     * Method to get date of registration.
     *
     * @return value of the field {@link User#regDate}.
     */
    public LocalDateTime getRegDate() {
        return regDate;
    }

    /**
     * Method to set date of registration.
     *
     * @param regDate - value to set for field {@link User#regDate}.
     */
    public void setRegDate(LocalDateTime regDate) {
        this.regDate = regDate;
    }

    /**
     * Method to get list of questions that user asked.
     *
     * @return value of the field {@link User#userQuestions}.
     */
    public List<Question> getUserQuestions() {
        return userQuestions;
    }

    /**
     * Method to set list of user's questions.
     *
     * @param userQuestions - value to set for field {@link User#userQuestions}.
     */
    public void setUserQuestions(List<Question> userQuestions) {
        this.userQuestions = userQuestions;
    }

    /**
     * Method to get list of answers that user written.
     *
     * @return value of the field {@link User#userAnswers}.
     */
    public List<Answer> getUserAnswers() {
        return userAnswers;
    }

    /**
     * Method to set list of answers that user written.
     *
     * @param userAnswers - value to set for field {@link User#userAnswers}.
     */
    public void setUserAnswers(List<Answer> userAnswers) {
        this.userAnswers = userAnswers;
    }

    /**
     * Indicates whether some other {@link User} is "equal to" this one.
     *
     * @param o - some other {@link User} to check if they are the same.
     * @return {@code true}, if two users are the same, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(email, user.email) &&
                Objects.equals(regDate, user.regDate) &&
                role == user.role &&
                Objects.equals(userQuestions, user.userQuestions) &&
                Objects.equals(userAnswers, user.userAnswers) &&
                Objects.equals(profileImage, user.profileImage);
    }

    /**
     * Method to find a hash code value for the {@link User}.
     *
     * @return hash code value for the user.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), username, password, firstName, lastName, email, regDate, role, userQuestions, userAnswers, profileImage);
    }

    /**
     * Method to generate string representation of the {@link User}.
     *
     * @return string representation of the user.
     */
    @Override
    public String toString() {
        return "User{"
                + super.toString()
                + "username='" + username + '\''
                + ", password='" + password + '\''
                + ", firstName='" + firstName + '\''
                + ", lastName='" + lastName + '\''
                + ", email='" + email + '\''
                + ", regDate=" + regDate
                + ", role=" + role
                + ", userQuestions=" + userQuestions
                + ", userAnswers=" + userAnswers
                + ", profileImage='" + profileImage
                + '\''
                + '}';
    }
}