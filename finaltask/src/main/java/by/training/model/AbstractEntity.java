package by.training.model;


import java.io.Serializable;

/**
 * Abstract entity which is the parent for the project entities,
 * implements {@link Serializable} interface.
 */
public abstract class AbstractEntity implements Serializable {

    private static final long serialVersionUID = -7967871648368085337L;
    /**
     * Entity id.
     */
    protected int id;

    /**
     * Instantiates a new Abstract entity.
     */
    AbstractEntity() {
    }

    /**
     * Instantiates a new Abstract entity.
     *
     * @param id the entity's id
     */
    AbstractEntity(int id) {
        this.id = id;
    }

    /**
     * Gets entity's id.
     *
     * @return the entity's id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets entity's id.
     *
     * @param id the entity's id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * This method equals two objects.
     *
     * @param o the object.
     * @return true if objects are equal and false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractEntity abstractEntity = (AbstractEntity) o;

        return id == abstractEntity.id;
    }

    /**
     * This method calculate object's hashcode.
     *
     * @return hashcode of object.
     */
    @Override
    public int hashCode() {
        return id;
    }
}
