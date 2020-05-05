package by.training.model;

/**
 * Simple JavaBean class that represent a Studio, extends {@link AbstractEntity} abstract class.
 */
public class Studio extends AbstractEntity {
    private static final long serialVersionUID = 438370900406527985L;

    private String name;

    /**
     * Instantiates a new Studio.
     */
    public Studio() {
    }

    /**
     * Instantiates a new Studio.
     *
     * @param id the studio id
     */
    public Studio(int id) {
        super(id);
    }

    /**
     * Instantiates a new Studio.
     *
     * @param id   the studio id
     * @param name the studio name
     */
    public Studio(int id, String name) {
        super(id);
        this.name = name;
}

    /**
     * Gets name.
     *
     * @return the studio name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the studio name
     */
    public void setName(String name) {
        this.name = name;
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
        if (!super.equals(o)) return false;

        Studio studio = (Studio) o;

        return name != null ? name.equals(studio.name) : studio.name == null;
    }

    /**
     * This method calculate object's hashcode.
     *
     * @return hashcode of object.
     */
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    /**
     * Representation of an object as a string.
     *
     * @return string info about object.
     */
    @Override
    public String toString() {
        return "Studio{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
