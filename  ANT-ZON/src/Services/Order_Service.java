package Services;

import Constants.Message;
import Controller.BuyerController;
import Controller.CompanyController;
import Modal.Company;
import Modal.Order;
import Modal.Product;
import Repository.Cart_Repo;
import Repository.Order_Repo;
import Repository.Product_Repo;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Order_Service {

    public static void placeOrder(Product product, int quantity, int buyerId) throws IOException {
        Product_Repo productRepo = new Product_Repo();
        Cart_Repo cartRepo = new Cart_Repo();
        Order_Repo orderRepo = new Order_Repo();
        // print companyID

        int stock = productRepo.getProductQuantity(product.getProduct_Id());
        if (quantity > stock) {
            throw new IllegalArgumentException(String.format(Message.STOCK_INSUFFICIENT, product.getProduct_Name(), stock));
        }

        // Update stock
        productRepo.reduceStock(product.getProduct_Id(), quantity);

        // Generate transaction ID
        String txnId = Message.TRANSACTION_ID_PREFIX + new Random().nextInt(99999999);

        // Total price
        double totalPrice = product.getProduct_Price() * quantity;

        // Save order
        orderRepo.insertOrder(buyerId, product.getProduct_Id(), product.getCompany_ID(), quantity, totalPrice, txnId, product.getProduct_Name());

        // Remove product from cart
        cartRepo.removeProductFromCart(buyerId, product.getProduct_Id());

        // Generate invoice
        Invoice_Service.generateInvoice(buyerId, product, quantity, totalPrice, txnId);
    }


    // this method show's Order history 
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
