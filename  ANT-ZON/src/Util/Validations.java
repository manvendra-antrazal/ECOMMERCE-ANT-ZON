package Util;

public class Validations {

    // password validation
    public static boolean isValidPassword(String password) {
    return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$");
    }

    // mobile no. validation
    public static boolean isValidMobile(String mobile) {
    return mobile.matches("^\\d{10}$");
}
}
