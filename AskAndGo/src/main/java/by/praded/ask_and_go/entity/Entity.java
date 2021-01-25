package by.praded.ask_and_go.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Kiryl Praded
 * 09.12.2020
 * <p>
 * Representation of an abstract entity.
 */
public abstract class Entity implements Serializable {
    /**
     * Identity of the object.
     */
    private Long id;

    /**
     * Method to get value of the field {@link Entity#id}.
     *
     * @return identity of the entity {@link Entity#id}.
     */
    public Long getId() {
        return id;
    }

    /**
     * Method to set value to the field {@link Entity#id}.
     *
     * @param id - value for the identity field.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Indicates whether some other {@link Entity} is "equal to" this one.
     *
     * @param o - some other object to check if they are the same.
     * @return {@code true}, if two entities are the same, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return Objects.equals(id, entity.id);
    }

    /**
     * Method to find a hash code value for the {@link Entity}.
     *
     * @return hash code value for the entity.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Method to generate string representation of the {@link Entity}.
     *
     * @return string representation of the object.
     */
    @Override
    public String toString() {
        return "Entity{" +
                "id=" + id +
                '}';
    }
}
