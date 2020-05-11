package by.training.model;

/**
 * JavaBean class that represent a User, extends {@link AbstractEntity} abstract class.
 */
public class User extends AbstractEntity {

    private static final long serialVersionUID = -3150289886212330216L;

    private String login;
    /**
     * Password represent a hash using Bcrypt cipher.
     *
     * @see org.mindrot.jbcrypt.BCrypt
     */
    private String password;
    /**
     * Avatar represents the name of the image with the extension.
     */
    private String avatar;
    /**
     * Text description roleId represent {@link RoleEnum} enum.
     */
    private int role;

    /**
     * Instantiates a new User.
     */
    public User() {
    }

    /**
     * Instantiates a new User.
     *
     * @param id the user id
     */
    public User(int id) {
        super(id);
    }

    /**
     * Instantiates a new User.
     *
     * @param id       the user id
     * @param login    the user login
     * @param password the user password
     * @param role     the user role
     */
    public User(int id, String login, String password, int role) {
        super(id);
        this.login = login;
        this.password = password;
        this.role = role;
    }

    /**
     * Instantiates a new User.
     *
     * @param login    the user login
     * @param password the user password
     * @param avatar   the user avatar
     * @param role     the user role
     */
    public User(String login, String password, String avatar, int role) {
        this.login = login;
        this.password = password;
        this.avatar = avatar;
        this.role = role;
    }

    /**
     * Instantiates a new User.
     *
     * @param id       the user id
     * @param login    the user login
     * @param password the user password
     * @param avatar   the user avatar
     * @param role     the user role
     */
    public User(int id, String login, String password, String avatar, int role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.avatar = avatar;
        this.role = role;
    }

    /**
     * Gets login.
     *
     * @return the user login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets login.
     *
     * @param login the user login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Gets password.
     *
     * @return the user password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the user password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets role.
     *
     * @return the user role
     */
    public int getRole() {
        return role;
    }

    /**
     * Sets role.
     *
     * @param role the user role
     */
    public void setRole(int role) {
        this.role = role;
    }

    /**
     * Gets avatar.
     *
     * @return the user avatar
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * Sets avatar.
     *
     * @param avatar the user avatar
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
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

        User user = (User) o;

        if (role != user.role) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        return avatar != null ? avatar.equals(user.avatar) : user.avatar == null;
    }

    /**
     * This method calculate object's hashcode.
     *
     * @return hashcode of object.
     */
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (avatar != null ? avatar.hashCode() : 0);
        result = 31 * result + role;
        return result;
    }

    /**
     * Representation of an object as a string.
     *
     * @return string info about object.
     */
    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", avatar='" + avatar + '\'' +
                ", role=" + role +
                ", id=" + id +
                '}';
    }
}
