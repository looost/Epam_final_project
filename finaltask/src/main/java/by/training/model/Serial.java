package by.training.model;


import java.io.Serializable;
import java.util.Set;


public class Serial extends Entity implements Serializable {

    private static final long serialVersionUID = -8730462953097921508L;

    private String name;
    private String description;
    private String logo;
    private String full_logo;
    private Set<Genre> genreList;

    public Serial() {
    }

    public Serial(int id, String name, String description, String logo, String full_logo, Set<Genre> genreList) {
        super(id);
        this.name = name;
        this.description = description;
        this.logo = logo;
        this.full_logo = full_logo;
        this.genreList = genreList;
    }

    public Serial(String name, String description, String logo, String full_logo) {
        this.name = name;
        this.description = description;
        this.logo = logo;
        this.full_logo = full_logo;
    }

    public Serial(String name, String description, String logo, String full_logo, Set<Genre> genreList) {
        this.name = name;
        this.description = description;
        this.logo = logo;
        this.full_logo = full_logo;
        this.genreList = genreList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getFull_logo() {
        return full_logo;
    }

    public void setFull_logo(String full_logo) {
        this.full_logo = full_logo;
    }

    public Set<Genre> getGenreList() {
        return genreList;
    }

    public void setGenreList(Set<Genre> genreList) {
        this.genreList = genreList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Serial serial = (Serial) o;

        if (name != null ? !name.equals(serial.name) : serial.name != null) return false;
        if (description != null ? !description.equals(serial.description) : serial.description != null) return false;
        if (logo != null ? !logo.equals(serial.logo) : serial.logo != null) return false;
        if (full_logo != null ? !full_logo.equals(serial.full_logo) : serial.full_logo != null) return false;
        return genreList != null ? genreList.equals(serial.genreList) : serial.genreList == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (logo != null ? logo.hashCode() : 0);
        result = 31 * result + (full_logo != null ? full_logo.hashCode() : 0);
        result = 31 * result + (genreList != null ? genreList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Show{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", logo='" + logo + '\'' +
                ", full_logo='" + full_logo + '\'' +
                ", genreList=" + genreList +
                '}';
    }
}
