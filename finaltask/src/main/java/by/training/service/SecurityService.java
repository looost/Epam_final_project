package by.training.service;

/**
 * Security interface.
 */
public interface SecurityService {
    /**
     * Password Verification.
     *
     * @param password verified password
     * @param hashed   valid password
     * @return true if operation was made successfully and false otherwise
     */
    boolean checkpw(String password, String hashed);

    /**
     * Hash a password.
     *
     * @param password the password to hash
     * @param salt     the salt to hash
     * @return the hashed password
     */
    String hashpw(String password, String salt);
}
