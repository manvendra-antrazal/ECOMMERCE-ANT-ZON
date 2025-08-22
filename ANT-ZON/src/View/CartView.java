package View;

import Constants.Message;
import Controller.CartController;
import Modal.Product;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class CartView {

    private static final Scanner scanner = new Scanner(System.in);
    private static final CartController cartController = CartController.getInstance();
    public static void openCartMenu(int buyerId, int companyId) {
        boolean exit = false;
        while (!exit) {
            String choice = showCartMenuAndGetChoice();

            try {
                switch (choice) {
                    case "1": {
                        int productId = getIntInput(Message.ENTER_PRODUCT_ID);
                        int qty = getIntInput(Message.ENTER_QUANTITY);
                        if (qty <= 0) {
                            showMessage(Message.INVALID_PRODUCT_PRICE);
                            break;
                        }
                        cartController.addProductToCart(buyerId, productId, companyId, qty);
                        showMessage(Message.PRODUCT_ADDED);
                        break;
                    }

                    case "2": { 
                        int productId = getIntInput(Message.ENTER_PRODUCT_ID);
                        boolean removed = cartController.removeProductFromCart(buyerId, productId);
                        showMessage(removed ? Message.PRODUCT_REMOVED_CART : Message.PRODUCT_NOT_FOUND);
                        break;
                    }

                    case "3": { 
                        int productId = getIntInput(Message.ENTER_PRODUCT_ID);
                        int qty = getIntInput(Message.QUANTITY_UPDATED);
                        if (qty < 0) {
                            showMessage(Message.INVALID_PRODUCT_PRICE);
                            break;
                        }
                        cartController.updateProductQuantity(buyerId, productId, qty);
                        showMessage(Message.QUANTITY_UPDATED);
                        break;
                    }

                    case "4": { 
                        List<Product> items = cartController.viewCart(buyerId);
                        double total = cartController.calculateTotal(buyerId);
                        displayCart(items, total);
                        break;
                    }

                    case "5": { 
                        cartController.clearCart(buyerId);
                        showMessage(Message.CART_EMPTY);
                        break;
                    }

                    case "6": { 
                        double total = cartController.calculateTotal(buyerId);
                        showMessage(String.format("Cart Total: $%.2f. Proceed to place order from Buyer menu.", total));
                        break;
                    }

                    case "0": 
                        exit = true;
                        break;

                    default:
                        showMessage(Message.INVALID_OPTION);
                        break;
                }
            } catch (Exception e) {
                showMessage("Operation failed: " + e.getMessage());
            }
        }
    }

    public static String showCartMenuAndGetChoice() {
        System.out.println(Message.CART_UPPER_FRAME);
        System.out.print("Enter your choice: ");
        System.out.println(Message.CART_LOWER_FRAME);
        return scanner.nextLine().trim();
    }

    public static void displayCart(List<Product> cartItems, double totalPrice) {
        if (cartItems == null || cartItems.isEmpty()) {
            System.out.println(Message.CART_EMPTY);
            return;
        }

        System.out.println(Message.CART_UPPER_FRAME);
        System.out.printf("%-10s %-28s %-12s %-10s %-12s%n",
        "ProductID", "Name", "Unit Price", "Qty", "Line Total");
        System.out.println("------------------------------------------------------------------");
        
        double computedTotal = 0.0;
        for (Product p : cartItems) {
            int pid = p.getProduct_Id();
            String name = safe(p.getProduct_Name());
            double price = p.getProduct_Price();
            int qty = p.getProduct_Quantity(); 
            double line = price * qty;
            computedTotal += line;
            
            System.out.printf("%-10d %-28s $%-11.2f %-10d $%-11.2f%n",
            pid, name, price, qty, line);
        }
        
        System.out.println(Message.CART_LOWER_FRAME);
        System.out.printf("Reported Total: $%.2f%n", totalPrice);
        if (Math.abs(totalPrice - computedTotal) > 0.01) {
            System.out.printf("Computed Total: $%.2f%n", computedTotal);
        }
    }

    public static void showMessage(String message) {
        System.out.println(message);
    }

    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String line = scanner.nextLine().trim();
                return Integer.parseInt(line);
            } catch (NumberFormatException | InputMismatchException e) {
                System.out.println(Message.INVALID_INPUT);
            }
        }
    }

    private static String safe(String s) {
        return s == null ? "N/A" : s;
    }
}
