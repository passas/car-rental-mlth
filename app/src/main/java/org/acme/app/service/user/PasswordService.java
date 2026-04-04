package org.acme.app.service.user;

import org.jboss.logging.Logger;
import org.mindrot.jbcrypt.BCrypt;

public class PasswordService
{
    private static Logger log = Logger.getLogger(PasswordService.class.getSimpleName());

    /**
     * BCrypt password with 10's factor.
     * @param plainPassword
     * @return Hashed salt password
     */
    public static String hashPassword(String plainPassword)
    {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    /**
     * Checks plain text password with the current hashed salted 10 bcrypt factor db stored password.
     * @param hashedPassword Hashed salt 10 factor BCrypt password
     * @param plainPassword Plain text password
     * @return True if it is a match, false otherwise.
     */
    public static boolean verifyPassword(String hashedPassword, String plainPassword)
    {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
