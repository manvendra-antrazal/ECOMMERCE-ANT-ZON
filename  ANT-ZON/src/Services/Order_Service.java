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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        orderRepo.insertOrder(buyerId, product.getProduct_Id(), product.getCompany_ID(), quantity, totalPrice, txnId);

        // Remove product from cart
        cartRepo.removeProductFromCart(buyerId, product.getProduct_Id());

        // Generate invoice
        generateInvoice(buyerId, product, quantity, totalPrice, txnId);
    }

    private static void generateInvoice(int buyerId, Product product, int qty, double total, String txnId) throws IOException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String timestamp = dtf.format(LocalDateTime.now());

        // Ensure invoice folder exists
        File invoiceDir = new File(Message.INVOICE_FOLDER);
        if (!invoiceDir.exists()) {
            invoiceDir.mkdir();
        }

        String fileName = Message.INVOICE_FOLDER + Message.INVOICE_FILENAME_PREFIX + buyerId + "_" + timestamp + ".txt";

        StringBuilder invoice = new StringBuilder();
        invoice.append(Message.INVOICE_HEADER).append("\n")
                .append("Buyer ID: ").append(buyerId).append("\n")
                .append("Transaction ID: ").append(txnId).append("\n")
                .append("Date: ").append(LocalDateTime.now()).append("\n\n")
                .append(String.format("%-25s %-10s %-10s %-10s\n", "Product", "Price", "Qty", "Total"))
                .append("------------------------------------------------------------\n")
                .append(String.format("%-25s $%-9.2f %-10d $%-9.2f\n",
                        product.getProduct_Name(),
                        product.getProduct_Price(),
                        qty,
                        total))
                .append("\nTOTAL: $").append(total).append("\n")
                .append(Message.NO_COD_NOTE).append("\n")
                .append(Message.INVOICE_FOOTER).append("\n");

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(invoice.toString());
        }

        System.out.println(invoice);
        System.out.printf((Message.INVOICE_GENERATED) + "%n", fileName);
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
            System.out.printf("║ %-4d ║ %-18s ║ %-12s ║ %-9d ║ ₹%-9.2f ║ %-11s ║\n",
                    order.getOrderId(),
                    order.getCompanyId(),
                    order.getQuantity(),
                    order.getTotalPrice(),
                    order.getOrderDate());
        }

        System.out.println(Message.ORDER_HISTORY_FOOTER);
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Enter your choice: ");
            String choice = sc.nextLine().trim().toUpperCase();

            switch (choice) {
                case "A":
                    BuyerController.showBuyerMenu(inputScanner, role ,company, buyerId);
                    return;
                case "B":
                    CompanyController.startCompanySelection(inputScanner); 
                    return;
                default:
                    System.out.println("Invalid input! Please enter valid input.");
            }
        }
}

}
