package by.training.model;

public class Studio extends AbstractEntity {
    private static final long serialVersionUID = 438370900406527985L;

    private String name;

    public Studio() {
    }

    public Studio(String name) {
        this.name = name;
    }

    public Studio(int id) {
        super(id);
    }

    public Studio(int id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Studio studio = (Studio) o;

        return name != null ? name.equals(studio.name) : studio.name == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Studio{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
