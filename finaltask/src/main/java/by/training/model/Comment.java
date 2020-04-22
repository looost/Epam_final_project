package by.training.model;

import java.time.LocalDateTime;

public class Comment extends AbstractEntity {
    private static final long serialVersionUID = -4451328805432622998L;
    private User user;
    private Serial serial;
    private String comment;
    private LocalDateTime publicationDate;

    public Comment() {
    }

    public Comment(int id) {
        super(id);
    }

    public Comment(int id, User user, Serial serial, String comment) {
        this.id = id;
        this.user = user;
        this.serial = serial;
        this.comment = comment;
    }

    public Comment(int id, String comment) {
        super(id);
        this.comment = comment;
    }

    public Comment(User user, Serial serial, String comment) {
        this.user = user;
        this.serial = serial;
        this.comment = comment;
    }

    public Comment(int id, User user, Serial serial, String comment, LocalDateTime publicationDate) {
        super(id);
        this.user = user;
        this.serial = serial;
        this.comment = comment;
        this.publicationDate = publicationDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Serial getSerial() {
        return serial;
    }

    public void setSerial(Serial serial) {
        this.serial = serial;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDateTime publicationDate) {
        this.publicationDate = publicationDate;
    }

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

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (serial != null ? serial.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (publicationDate != null ? publicationDate.hashCode() : 0);
        return result;
    }

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
