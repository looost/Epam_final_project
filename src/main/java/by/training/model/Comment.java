package by.training.model;

import java.time.LocalDateTime;

/**
 * JavaBean class that represent a Comment, extends {@link AbstractEntity} abstract class.
 */
public class Comment extends AbstractEntity {
    private static final long serialVersionUID = -4451328805432622998L;
    private User user;
    private Serial serial;
    private String comment;
    private LocalDateTime publicationDate;

    /**
     * Instantiates a new Comment.
     */
    public Comment() {
    }

    /**
     * Instantiates a new Comment.
     *
     * @param id the comment id
     */
    public Comment(int id) {
        super(id);
    }

    /**
     * Instantiates a new Comment.
     *
     * @param id      the comment id
     * @param user    the comment user
     * @param serial  the comment serial
     * @param comment the comment text
     */
    public Comment(int id, User user, Serial serial, String comment) {
        this.id = id;
        this.user = user;
        this.serial = serial;
        this.comment = comment;
    }

    /**
     * Instantiates a new Comment.
     *
     * @param id              the comment id
     * @param user            the comment user
     * @param serial          the comment serial
     * @param comment         the comment text
     * @param publicationDate the comment publication date
     */
    public Comment(int id, User user, Serial serial, String comment, LocalDateTime publicationDate) {
        super(id);
        this.user = user;
        this.serial = serial;
        this.comment = comment;
        this.publicationDate = publicationDate;
    }

    /**
     * Gets user.
     *
     * @return user who commented
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user user who commented
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets serial.
     *
     * @return the series to which the comment is left
     */
    public Serial getSerial() {
        return serial;
    }

    /**
     * Sets serial.
     *
     * @param serial the series to which the comment is left
     */
    public void setSerial(Serial serial) {
        this.serial = serial;
    }

    /**
     * Gets comment.
     *
     * @return the comment text
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets comment.
     *
     * @param comment the comment text
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Gets publication date.
     *
     * @return comment publication date
     */
    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    /**
     * Sets publication date.
     *
     * @param publicationDate the comment publication date
     */
    public void setPublicationDate(LocalDateTime publicationDate) {
        this.publicationDate = publicationDate;
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

        Comment comment1 = (Comment) o;

        if (user != null ? !user.equals(comment1.user) : comment1.user != null) return false;
        if (serial != null ? !serial.equals(comment1.serial) : comment1.serial != null) return false;
        if (comment != null ? !comment.equals(comment1.comment) : comment1.comment != null) return false;
        return publicationDate != null ? publicationDate.equals(comment1.publicationDate) : comment1.publicationDate == null;
    }

    /**
     * This method calculate object's hashcode.
     *
     * @return hashcode of object.
     */
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (serial != null ? serial.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (publicationDate != null ? publicationDate.hashCode() : 0);
        return result;
    }

    /**
     * Representation of an object as a string.
     *
     * @return string info about object.
     */
    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", user=" + user.getLogin() +
                ", serial=" + serial.getName() +
                ", comment='" + comment + '\'' +
                ", publication_date=" + publicationDate +
                '}';
    }
}
