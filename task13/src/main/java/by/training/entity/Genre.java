package by.training.entity;

import java.util.Set;

public class Genre extends AbstractEntity {

    private static final long serialVersionUID = 3379973670323251459L;

    private String name;
    private Set<Serial> serials;

    public Genre() {
    }

    public Genre(int id) {
        super(id);
    }

    public Genre(int id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Serial> getSerials() {
        return serials;
    }

    public void setSerials(Set<Serial> serials) {
        this.serials = serials;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Genre genre = (Genre) o;

        if (name != null ? !name.equals(genre.name) : genre.name != null) return false;
        return serials != null ? serials.equals(genre.serials) : genre.serials == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (serials != null ? serials.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
