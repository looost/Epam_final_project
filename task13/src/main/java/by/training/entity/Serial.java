package by.training.entity;

import java.sql.Date;
import java.util.Set;

public class Serial extends AbstractEntity {

    private static final long serialVersionUID = 3696457511902462868L;

    private String name;
    private String description;
    private String logo;
    private String fullLogo;
    private Date releaseDate;
    private int countLike;
    private Country country;
    private Studio studio;
    private Set<Genre> genres;
    private Set<Comment> comments;

    public Serial() {
    }

    public Serial(int id) {
        super(id);
    }

    public Serial(int id, String name, String description, String logo, String fullLogo, Set<Genre> genres) {
        super(id);
        this.name = name;
        this.description = description;
        this.logo = logo;
        this.fullLogo = fullLogo;
        this.genres = genres;
    }

    public Serial(String name, String description, String logo, String fullLogo) {
        this.name = name;
        this.description = description;
        this.logo = logo;
        this.fullLogo = fullLogo;
    }

    public Serial(String name, String description, String logo, String fullLogo, Set<Genre> genres) {
        this.name = name;
        this.description = description;
        this.logo = logo;
        this.fullLogo = fullLogo;
        this.genres = genres;
    }

    public Serial(int id, String name, String description, String logo, String fullLogo, Date releaseDate, int countLike, Country country, Studio studio, Set<Genre> genres, Set<Comment> comments) {
        super(id);
        this.name = name;
        this.description = description;
        this.logo = logo;
        this.fullLogo = fullLogo;
        this.releaseDate = releaseDate;
        this.countLike = countLike;
        this.country = country;
        this.studio = studio;
        this.genres = genres;
        this.comments = comments;
    }

    public Serial(int id, String name, String description, String logo, String fullLogo, Date releaseDate, int countLike, Country country, Studio studio, Set<Genre> genres) {
        super(id);
        this.name = name;
        this.description = description;
        this.logo = logo;
        this.fullLogo = fullLogo;
        this.releaseDate = releaseDate;
        this.countLike = countLike;
        this.country = country;
        this.studio = studio;
        this.genres = genres;
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

    public String getFullLogo() {
        return fullLogo;
    }

    public void setFullLogo(String fullLogo) {
        this.fullLogo = fullLogo;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Studio getStudio() {
        return studio;
    }

    public void setStudio(Studio studio) {
        this.studio = studio;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getCountLike() {
        return countLike;
    }

    public void setCountLike(int countLike) {
        this.countLike = countLike;
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
        if (fullLogo != null ? !fullLogo.equals(serial.fullLogo) : serial.fullLogo != null) return false;
        if (country != null ? !country.equals(serial.country) : serial.country != null) return false;
        if (studio != null ? !studio.equals(serial.studio) : serial.studio != null) return false;
        if (genres != null ? !genres.equals(serial.genres) : serial.genres != null) return false;
        return comments != null ? comments.equals(serial.comments) : serial.comments == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (logo != null ? logo.hashCode() : 0);
        result = 31 * result + (fullLogo != null ? fullLogo.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (studio != null ? studio.hashCode() : 0);
        result = 31 * result + (genres != null ? genres.hashCode() : 0);
        result = 31 * result + (comments != null ? comments.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Serial{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", logo='" + logo + '\'' +
                ", full_logo='" + fullLogo + '\'' +
                ", releaseDate=" + releaseDate +
                ", countLike=" + countLike +
                ", country=" + country +
                ", studio=" + studio +
                ", genres=" + genres +
                ", comments=" + comments +
                '}';
    }

}
