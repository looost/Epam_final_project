package by.training.model;

/**
 * JavaBean class that represent a Country, extends {@link AbstractEntity} abstract class.
 */
public class Country extends AbstractEntity {

    private static final long serialVersionUID = -5599644480354135522L;

    private String name;

    /**
     * Instantiates a new Country.
     */
    public Country() {
    }


    /**
     * Instantiates a new Country.
     *
     * @param id   the country id
     * @param name the country name
     */
    public Country(int id, String name) {
        super(id);
        this.name = name;
    }

    /**
     * Instantiates a new Country.
     *
     * @param id the country id
     */
    public Country(int id) {
        super(id);
    }

    /**
     * Gets name.
     *
     * @return the country name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the country name
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

        Country country = (Country) o;

        return name != null ? name.equals(country.name) : country.name == null;
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
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
