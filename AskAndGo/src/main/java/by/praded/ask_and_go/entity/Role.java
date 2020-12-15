package by.praded.ask_and_go.entity;

/**
 * @author Kiryl Praded
 * 04.12.2020
 * Possible user roles.
 */
public enum Role {
    ADMIN("Administrator"), MODERATOR("Moderator"), WRITER("Writer"), READER("Reader");

    /**
     * Name of the role
     */
    private final String name;

    /**
     * Enumeration constructor.
     *
     * @param role - name of the role.
     */
    Role(String role) {
        this.name = role;
    }

    /**
     * Method to get index of role.
     *
     * @return index of role among all roles.
     */
    public Integer getIdentity() {
        return ordinal();
    }

    /**
     * Getter for the field {@link Role#name}.
     *
     * @return string value of the role
     */
    public String getName() {
        return name;
    }
}
