package View;

import Constants.Message;
import Modal.Product;
import java.util.List;
import java.util.Scanner;

public class WishlistView {

    public static String showWishlistMenuAndGetChoice(Scanner scanner) {
        
        System.out.println(Message.WISHLIST_UPPER_FRAME);
        System.out.println(Message.WISHLIST_LOWER_FRAME);
        System.out.print("Enter your choice: ");
        return scanner.nextLine();
    }

    public static int getProductIdToAdd(Scanner scanner) {
        System.out.print(Message.ENTER_PRODUCT_ID);
        return scanner.nextInt();
    }

    public static int getProductIdToRemove(Scanner scanner) {
        System.out.print(Message.ENTER_PRODUCT_ID);
        return scanner.nextInt();
    }

    public static void showWishlistItems(List<Product> wishlist) {
        if (wishlist == null || wishlist.isEmpty()) {
            System.out.println(Message.EMPTY_WISHLIST);
        } else {
            System.out.println(Message.WISHLIST_UPPER_FRAME);
            for (Product product : wishlist) {
                System.out.println(product);
            }
            System.out.println(Message.WISHLIST_LOWER_FRAME);
        }
    }

    // Confirmation Messages
    public static void showAddSuccess(String productName) {
        System.out.println(productName + " added to wishlist successfully!");
    }

    public static void showRemoveSuccess(String productName) {
        System.out.println(productName + " removed from wishlist successfully!");
    }

    public static void showClearSuccess() {
        System.out.println("Wishlist cleared successfully!");
    }

    public static void showError(String message) {
        System.out.println("Error: " + message);
    }
}
