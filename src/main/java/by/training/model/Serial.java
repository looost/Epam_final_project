package by.training.model;

import java.sql.Date;
import java.util.List;

/**
 * JavaBean class that represent a Serial, extends {@link AbstractEntity} abstract class.
 */
public class Serial extends AbstractEntity {

    private static final long serialVersionUID = -8730462953097921508L;

    private String name;
    private String description;
    /**
     * Logo represents the name of the image with the extension.
     */
    private String logo;
    /**
     * Full logo represents the name of the image with the extension.
     */
    private String fullLogo;
    private Date releaseDate;
    private int countLike;
    private Country country;
    private Studio studio;
    private List<Genre> genres;
    private List<Comment> comments;

    /**
     * Instantiates a new Serial.
     */
    public Serial() {
    }

    /**
     * Instantiates a new Serial.
     *
     * @param id the serial id
     */
    public Serial(int id) {
        super(id);
    }

    /**
     * Gets name.
     *
     * @return the serial name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the serial name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets description.
     *
     * @return the serial description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the serial description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets logo.
     *
     * @return the serial logo
     */
    public String getLogo() {
        return logo;
    }

    /**
     * Sets logo.
     *
     * @param logo the serial logo
     */
    public void setLogo(String logo) {
        this.logo = logo;
    }

    /**
     * Gets full logo.
     *
     * @return the serial full logo
     */
    public String getFullLogo() {
        return fullLogo;
    }

    /**
     * Sets full logo.
     *
     * @param fullLogo the serial full logo
     */
    public void setFullLogo(String fullLogo) {
        this.fullLogo = fullLogo;
    }

    /**
     * Gets genres.
     *
     * @return the serial genres
     */
    public List<Genre> getGenres() {
        return genres;
    }

    /**
     * Sets genres.
     *
     * @param genres the serial genres
     */
    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    /**
     * Gets country.
     *
     * @return the serial country
     */
    public Country getCountry() {
        return country;
    }

    /**
     * Sets country.
     *
     * @param country the serial country
     */
    public void setCountry(Country country) {
        this.country = country;
    }

    /**
     * Gets studio.
     *
     * @return the serial studio
     */
    public Studio getStudio() {
        return studio;
    }

    /**
     * Sets studio.
     *
     * @param studio the serial studio
     */
    public void setStudio(Studio studio) {
        this.studio = studio;
    }

    /**
     * Gets comments.
     *
     * @return the serial comments
     */
    public List<Comment> getComments() {
        return comments;
    }

    /**
     * Sets comments.
     *
     * @param comments the serial comments
     */
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    /**
     * Gets release date.
     *
     * @return the serial release date
     */
    public Date getReleaseDate() {
        return releaseDate;
    }

    /**
     * Sets release date.
     *
     * @param releaseDate the serial release date
     */
    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * Gets count like.
     *
     * @return the serial count like
     */
    public int getCountLike() {
        return countLike;
    }

    /**
     * Sets count like.
     *
     * @param countLike the serial count like
     */
    public void setCountLike(int countLike) {
        this.countLike = countLike;
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

    /**
     * This method calculate object's hashcode.
     *
     * @return hashcode of object.
     */
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

    /**
     * Representation of an object as a string.
     *
     * @return string info about object.
     */
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

    /**
     * Represents an implementation of the Builder design pattern.
     */
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
