package Util;

public class Validations {

    // Validate password: min 8 chars, at least 1 uppercase, 1 lowercase, 1 digit, 1 Special char
    public static boolean isValidPassword(String password) {
        return password != null && password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");
    }

    // Validate 10-digit mobile number
    public static boolean isValidMobile(String mobile) {
        return mobile != null && mobile.matches("^\\d{10}$");
    }

    // Validate name: alphanumeric and space, 5–50 chars
    public static boolean isValidName(String name) {
        return name != null 
            && name.trim().length() >= 5 
            && name.trim().length() <= 50 
            && name.matches("^[A-Za-z0-9 ]+$");
    }

    // Validate product description: allow comma, space, alphanumeric
    public static boolean isValidDescription(String desc) {
    if (desc == null || desc.trim().length() < 5 || desc.trim().length() > 50) {
        return false;
    }
    return desc.matches("^[A-Za-z0-9,\\.\\-/ ]+$");
    }


    // Validate price as positive decimal
    public static boolean isValidPrice(String priceStr) {
        try {
            double price = Double.parseDouble(priceStr.trim());
            return price >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Validate quantity as non-negative integer
    public static boolean isValidQuantity(String qtyStr) {
        try {
            int qty = Integer.parseInt(qtyStr.trim());
            return qty >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
