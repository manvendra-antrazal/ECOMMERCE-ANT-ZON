package Util;

public class PrintUtil {
    public static void printMessage(String msg) {
        System.out.println(msg);
    }

    public static void printMessages(String... messages) {
        for (String msg : messages) {
            System.out.println(msg);
        }
    }
    public static void printMessageWithException(String message, Exception e) {
        System.out.println(message);
        if (e != null) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

