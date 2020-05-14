package by.training.model;

/**
 * JavaBean class that represent a Genre, extends {@link AbstractEntity} abstract class.
 */
public class Genre extends AbstractEntity {

    private static final long serialVersionUID = 3379973670323251459L;

    private String name;

    /**
     * Instantiates a new Genre.
     */
    public Genre() {
    }

    /**
     * Instantiates a new Genre.
     *
     * @param id the genre id
     */
    public Genre(int id) {
        super(id);
    }

    /**
     * Instantiates a new Genre.
     *
     * @param id   the genre id
     * @param name the genre name
     */
    public Genre(int id, String name) {
        super(id);
        this.name = name;
    }

    /**
     * Gets name.
     *
     * @return the genre name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the genre name
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

        Genre genre = (Genre) o;

        return name != null ? name.equals(genre.name) : genre.name == null;
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
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
