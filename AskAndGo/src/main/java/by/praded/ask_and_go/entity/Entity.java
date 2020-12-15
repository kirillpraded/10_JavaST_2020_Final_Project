package by.praded.ask_and_go.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Kiryl Praded
 * 09.12.2020
 */
public abstract class Entity implements Serializable {
    private Long id;

    public Entity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return Objects.equals(id, entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Entity{}";
    }
}
