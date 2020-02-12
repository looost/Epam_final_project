package by.training.entity;

import java.util.List;
import java.util.Set;

public class Show extends Entity {

    private String name;
    private String description;
    private String logo;
    private String full_logo;
    private double rating;
    private Set<Genre> genreList;

    public Show() {
    }

    public Show(int id, String name, double rating, Set<Genre> genreList) {
        super(id);
        this.name = name;
        this.rating = rating;
        this.genreList = genreList;
    }

    public Show(String name, double rating) {
        this.name = name;
        this.rating = rating;
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

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
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

        Show show = (Show) o;

        if (Double.compare(show.rating, rating) != 0) return false;
        if (name != null ? !name.equals(show.name) : show.name != null) return false;
        if (description != null ? !description.equals(show.description) : show.description != null) return false;
        if (logo != null ? !logo.equals(show.logo) : show.logo != null) return false;
        if (full_logo != null ? !full_logo.equals(show.full_logo) : show.full_logo != null) return false;
        return genreList != null ? genreList.equals(show.genreList) : show.genreList == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (logo != null ? logo.hashCode() : 0);
        result = 31 * result + (full_logo != null ? full_logo.hashCode() : 0);
        temp = Double.doubleToLongBits(rating);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
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
                ", rating=" + rating +
                ", genreList=" + genreList +
                '}';
    }
}
