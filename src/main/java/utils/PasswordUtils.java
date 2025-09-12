package utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtils {

    // Genera un hash seguro con salt
    public static String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    // Verifica que la contraseña ingresada coincida con el hash almacenado
    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        if (hashedPassword == null || hashedPassword.isEmpty()) {
            return false;
        }
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
