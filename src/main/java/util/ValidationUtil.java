package util;

public class ValidationUtil {
    public static boolean isValidPassword(String password){
        return password != null &&
                password.length() >= 8 &&
                password.matches(".*[A-Z].*") &&       // at least one uppercase
                password.matches(".*[a-z].*") &&       // at least one lowercase
                password.matches(".*\\d.*") &&         // at least one digit
                password.matches(".*[!@#$%^&*()].*");  // at least one symbol
    }
}
