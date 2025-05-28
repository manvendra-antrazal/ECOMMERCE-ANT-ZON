package Services;

import Constants.Message;
import Controller.BuyerController;
import Controller.CompanyController;
import Modal.Company;
import Modal.Order;
import Modal.Product;
import Repository.Cart_Repo;
import Repository.Category_Repo;
import Repository.Order_Repo;
import Repository.Product_Repo;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Order_Service {

    // public static void placeOrder(Product product, int buyerId) throws IOException {
    //     Product_Repo productRepo = new Product_Repo();
    //     Cart_Repo cartRepo = new Cart_Repo();
    //     Order_Repo orderRepo = new Order_Repo();

    //     int stock = productRepo.getProductQuantity(product.getProduct_Id());
    //     int quantity = 1;  // Default quantity from cart

    //     if (quantity > stock) {
    //         throw new IllegalArgumentException(String.format(Message.STOCK_INSUFFICIENT, product.getProduct_Name(), stock));
    //     }

    //     // Apply discount
    //     double originalPrice = product.getProduct_Price();
    //     double discountedPrice = originalPrice;

    //     Category_Repo categoryRepo = new Category_Repo();
    //     int furnitureCatId = categoryRepo.getCategoryIdByName("furniture");
    //     int eventSubCatId = categoryRepo.getSubCategoryIdByName("event");

    //     double discountPercent = 0.0;
    //     String productNameLower = product.getProduct_Name().toLowerCase();

    //     if (productNameLower.contains("dell 7640") || productNameLower.contains("lenovo 5540")) {
    //         discountPercent += 2.5;
    //     }

    //     if (product.getCategory_ID() == furnitureCatId && product.getSub_cat_ID() == eventSubCatId) {
    //         discountPercent += 2.5;
    //     }

    //     discountedPrice = originalPrice - (originalPrice * discountPercent / 100.0);
    //     double totalPrice = discountedPrice * quantity;

    //     // Update stock
    //     productRepo.reduceStock(product.getProduct_Id(), quantity);

    //     // Generate transaction ID
    //     String txnId = Message.TRANSACTION_ID_PREFIX + new Random().nextInt(99999999);

    //     // Save order
    //     orderRepo.insertOrder(buyerId, product.getProduct_Id(), product.getCompany_ID(), quantity, totalPrice, txnId, product.getProduct_Name());

    //     // Remove from cart
    //     cartRepo.removeProductFromCart(buyerId, product.getProduct_Id());

    //     // Generate invoice
    //     Invoice_Service.generateInvoice(buyerId, product, quantity, totalPrice, txnId);
    // }


    public static double placeOrderReturnPrice(Product product, int buyerId, int quantity) throws IOException {
        Product_Repo productRepo = new Product_Repo();
        Cart_Repo cartRepo = new Cart_Repo();
        Order_Repo orderRepo = new Order_Repo();

        int stock = productRepo.getProductQuantity(product.getProduct_Id());
        if (quantity > stock) {
            throw new IllegalArgumentException(String.format(Message.STOCK_INSUFFICIENT, product.getProduct_Name(), stock));
        }

        // Discount logic
        double originalPrice = product.getProduct_Price();
        double discountPercent = 0.0;

        Category_Repo categoryRepo = new Category_Repo();
        int furnitureCatId = categoryRepo.getCategoryIdByName("furniture");
        int eventSubCatId = categoryRepo.getSubCategoryIdByName("event");

        String nameLower = product.getProduct_Name().toLowerCase();
        if (nameLower.contains("dell 7640") || nameLower.contains("lenovo 5540")) {
            discountPercent += 2.5;
        }
        if (product.getCategory_ID() == furnitureCatId && product.getSub_cat_ID() == eventSubCatId) {
            discountPercent += 2.5;
        }

        double discountedPrice = originalPrice - (originalPrice * discountPercent / 100.0);
        double totalPrice = discountedPrice * quantity;

        // DB operations
        productRepo.reduceStock(product.getProduct_Id(), quantity);

        String txnId = Message.TRANSACTION_ID_PREFIX + new Random().nextInt(99999999);
        orderRepo.insertOrder(buyerId, product.getProduct_Id(), product.getCompany_ID(), quantity, totalPrice, txnId, product.getProduct_Name());

        cartRepo.removeProductFromCart(buyerId, product.getProduct_Id());

        return totalPrice;
    }



    // here it show's Order history 
    public static void viewOrderHistory(Scanner inputScanner, String role, Company company, int buyerId) {

        Order_Repo orderRepo = new Order_Repo(); // Create instance
        List<Order> orders = orderRepo.getOrdersByBuyerId(buyerId);

        if (orders.isEmpty()) {
            System.out.println(Message.NO_ORDERS_MSG);
            return;
        }

        System.out.println(Message.ORDER_HISTORY_HEADER);

       for (Order order : orders) {
            System.out.printf("║ %-6d ║ %-30s ║ %-10d ║  %-11.2f ║ %-20s ║\n",
            order.getOrderId(),
            order.getProductName(),
            order.getQuantity(),
            order.getTotalPrice(),
            order.getOrderDate().toLocalDateTime());
        }


        System.out.println(Message.ORDER_HISTORY_FOOTER);
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print(Message.SELECT_OPTION);
            String choice = sc.nextLine().trim().toUpperCase();

            switch (choice) {
                case "A":
                    BuyerController.showBuyerMenu(inputScanner, role ,company, buyerId);
                    return;
                case "B":
                    CompanyController.startCompanySelection(inputScanner); 
                    return;
                default:
                    System.out.println(Message.INVALID_INPUT);
            }
        }
    }
}
