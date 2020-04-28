package by.training.model;


import java.sql.Date;
import java.util.List;


public class Serial extends AbstractEntity {

    private static final long serialVersionUID = -8730462953097921508L;

    private String name;
    private String description;
    private String logo;
    private String fullLogo;
    private Date releaseDate;
    private int countLike;
    private Country country;
    private Studio studio;
    private List<Genre> genres;
    private List<Comment> comments;

    public Serial() {
    }

    public Serial(int id) {
        super(id);
    }

//    public Serial(int id, String name, String description, String logo, String fullLogo, List<Genre> genres) {
//        super(id);
//        this.name = name;
//        this.description = description;
//        this.logo = logo;
//        this.fullLogo = fullLogo;
//        this.genres = genres;
//    }
//
//    public Serial(String name, String description, String logo, String fullLogo) {
//        this.name = name;
//        this.description = description;
//        this.logo = logo;
//        this.fullLogo = fullLogo;
//    }
//
//    public Serial(String name, String description, String logo, String fullLogo, List<Genre> genres) {
//        this.name = name;
//        this.description = description;
//        this.logo = logo;
//        this.fullLogo = fullLogo;
//        this.genres = genres;
//    }
//
//public Serial(int id, String name, String description, String logo, String fullLogo, Date releaseDate, int countLike, Country country, Studio studio) {
//    super(id);
//    this.name = name;
//    this.description = description;
//    this.logo = logo;
//    this.fullLogo = fullLogo;
//    this.releaseDate = releaseDate;
//    this.countLike = countLike;
//    this.country = country;
//    this.studio = studio;
//}
//
//    public Serial(int id, String name, String description, String logo, String fullLogo, Date releaseDate, int countLike, Country country, Studio studio, List<Genre> genres, List<Comment> comments) {
//        super(id);
//        this.name = name;
//        this.description = description;
//        this.logo = logo;
//        this.fullLogo = fullLogo;
//        this.releaseDate = releaseDate;
//        this.countLike = countLike;
//        this.country = country;
//        this.studio = studio;
//        this.genres = genres;
//        this.comments = comments;
//    }

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

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
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

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
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

    public static class Builder {
        private Serial newSerial;

        public Builder() {
            newSerial = new Serial();
        }

        public Builder withId(int id) {
            newSerial.id = id;
            return this;
        }

        public Builder withName(String name) {
            newSerial.name = name;
            return this;
        }

        public Builder withDescription(String description) {
            newSerial.description = description;
            return this;
        }

        public Builder withLogo(String logo) {
            newSerial.logo = logo;
            return this;
        }

        public Builder withFullLogo(String fullLogo) {
            newSerial.fullLogo = fullLogo;
            return this;
        }

        public Builder withReleaseDate(Date releaseDate) {
            newSerial.releaseDate = releaseDate;
            return this;
        }

        public Builder withCountLike(int countLike) {
            newSerial.countLike = countLike;
            return this;
        }

        public Builder withCountry(Country country) {
            newSerial.country = country;
            return this;
        }

        public Builder withStudio(Studio studio) {
            newSerial.studio = studio;
            return this;
        }

        public Builder withGenres(List<Genre> genres) {
            newSerial.genres = genres;
            return this;
        }

        public Builder withComments(List<Comment> comments) {
            newSerial.comments = comments;
            return this;
        }

        public Serial build() {
            return newSerial;
        }
    }
}
