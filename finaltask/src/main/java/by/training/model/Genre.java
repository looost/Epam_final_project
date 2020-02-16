package by.training.model;


import java.io.Serializable;
import java.util.List;

public class Genre extends Entity implements Serializable {

    private static final long serialVersionUID = 3379973670323251459L;

    private String name;
    private List<Serial> serialList;

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

    public List<Serial> getSerialList() {
        return serialList;
    }

    public void setSerialList(List<Serial> serialList) {
        this.serialList = serialList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Genre genre = (Genre) o;

        if (name != null ? !name.equals(genre.name) : genre.name != null) return false;
        return serialList != null ? serialList.equals(genre.serialList) : genre.serialList == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (serialList != null ? serialList.hashCode() : 0);
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
