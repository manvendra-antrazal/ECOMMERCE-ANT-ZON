package View;

import Constants.Message;
import Modal.Order;
import Modal.Product;
import java.util.List;
import java.util.Scanner;

public class BuyerView {

    // --- Main Buyer Flow ---
    public static void showWelcomeMenu() {
        System.out.println(Message.BUYER_LOGIN_MENU);
    }

    public static void showLoginPrompt() {
        System.out.println(Message.LOGIN_CREDENTIAL);
    }

    public static void showRegisterPrompt() {
        System.out.println("\n--- Buyer Registration ---");
    }

    public static void showInvalidCredentials() {
        System.out.println("Invalid credentials.");
    }

    public static void showLoginSuccess() {
        System.out.println(Message.LOGIN_SUCCESS);
    }

    public static void showRegistrationSuccess() {
        System.out.println(Message.REGISTER);
    }

    public static void showRegistrationFailure() {
        System.out.println(Message.USERNAME_EXISTS);
    }

    public static void showExitMessage() {
        System.out.println(Message.EXIT_MESSAGE);
    }

    public static void showBuyerMenu() {
        System.out.print(Message.BUYER_MENU);
    }

    public static String getUsername(Scanner scanner) {
        System.out.print(Message.USERNAME);
        return scanner.nextLine();
    }

    public static String getPassword(Scanner scanner) {
        System.out.print(Message.ENTER_PASSWORD);
        return scanner.nextLine();
    }

    public static int getSubCategoryChoice(Scanner scanner) {
        System.out.print(Message.SELLER_SUB_CATEGORY_UPPER_FRAME);
        System.out.print(Message.SELLER_SUB_CATEGORY_LOWER_FRAME);
        return Integer.parseInt(scanner.nextLine());
    }

    public static String getSearchKeyword(Scanner scanner) {
        System.out.print("Enter product keyword to search: ");
        return scanner.nextLine();
    }

    public static int getProductIdForWishlist(Scanner scanner) {
        System.out.print(Message.SELECT_PRODUCT_INDEX);
        return Integer.parseInt(scanner.nextLine());
    }

    public static int getProductIdForCart(Scanner scanner) {
        System.out.print(Message.SELECT_PRODUCT_INDEX);
        return Integer.parseInt(scanner.nextLine());
    }

    public static int getProductQty(Scanner scanner) {
        System.out.print(Message.PRODUCT_QUANTITY);
        return Integer.parseInt(scanner.nextLine());
    }

    // --- Outputs ---
    public static void showProducts(List<Product> products) {
        if (products == null || products.isEmpty()) {
            System.out.println(Message.NO_PRODUCT_FOUND);
            return;
        }
        System.out.println(Message.PRODUCT_LIST);
        for (Product p : products) {
            System.out.println(p);
        }
    }

    public static void showWishlist(List<Product> wishlist) {
        if (wishlist == null || wishlist.isEmpty()) {
            System.out.println(Message.EMPTY_WISHLIST);
            return;
        }
        System.out.println(Message.WISHLIST_UPPER_FRAME);
        for (Product p : wishlist) {
            System.out.println(p);
        }
        System.out.println(Message.WISHLIST_LOWER_FRAME);
    }

    public static void showCart(List<Product> cartItems) {
        if (cartItems == null || cartItems.isEmpty()) {
            System.out.println(Message.EMPTY_CART);
            return;
        }
        System.out.println(Message.CART_EMPTY);
        for (Product p : cartItems) {
            System.out.println(p);
        }
    }

    public static void showOrders(List<Order> orders) {
        if (orders == null || orders.isEmpty()) {
            return;
        }
        System.out.println(Message.NO_ORDERS_MSG);
        for (Order o : orders) {
            System.out.println(o);
        }
    }

    public static void showMessage(String message) {
        System.out.println(message);
    }
}
