package by.training.service.impl;

import by.training.service.SecurityService;
import org.mindrot.jbcrypt.BCrypt;

/**
 * Security service.
 */
public class SecurityServiceImpl implements SecurityService {

    /**
     * Verify password validity. The library is used as a hash check is {@link BCrypt}.
     *
     * @param password verified password
     * @param hashed   password hash
     * @return if the passwords are valid and false otherwise
     */
    @Override
    public boolean checkpw(final String password, final String hashed) {
        return BCrypt.checkpw(password, hashed);
    }

    /**
     * Hash a password using the OpenBSD bcrypt scheme
     *
     * @param password the password to hash
     * @param salt     the salt to hash with (perhaps generated
     *                 using BCrypt.gensalt)
     * @return the hashed password
     */
    @Override
    public String hashpw(final String password, final String salt) {
        return BCrypt.hashpw(password, salt);
    }
}
