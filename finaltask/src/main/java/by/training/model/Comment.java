package by.training.model;


import java.util.Date;

public class Comment extends AbstractEntity {
    private static final long serialVersionUID = -4451328805432622998L;

    private User user;
    private Serial serial;
    private String comment;
    private Date publication_date;

    public Comment() {
    }

    public Comment(int id) {
        super(id);
    }

    public Comment(User user, Serial serial, String comment, Date publication_date) {
        this.user = user;
        this.serial = serial;
        this.comment = comment;
        this.publication_date = publication_date;
    }

    public Comment(int id, User user, Serial serial, String comment, Date publication_date) {
        super(id);
        this.user = user;
        this.serial = serial;
        this.comment = comment;
        this.publication_date = publication_date;
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

    public Date getPublication_date() {
        return publication_date;
    }

    public void setPublication_date(Date publication_date) {
        this.publication_date = publication_date;
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
        return publication_date != null ? publication_date.equals(comment1.publication_date) : comment1.publication_date == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (serial != null ? serial.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (publication_date != null ? publication_date.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", user=" + user.getLogin() +
                ", serial=" + serial.getName() +
                ", comment='" + comment + '\'' +
                ", publication_date=" + publication_date +
                '}';
    }
}
