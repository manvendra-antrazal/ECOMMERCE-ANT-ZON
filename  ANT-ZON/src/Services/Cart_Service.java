package Services;

import Constants.Message;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import Modal.Product;
import Repository.Cart_Repo;


public class Cart_Service {

        public static void addToCart(Product product, int companyId, int buyerId, int quantity) throws SQLException {
        Cart_Repo repo = new Cart_Repo();
        repo.insertCartItem(product.getProduct_Id(), companyId, buyerId, quantity); 
    }


    public static void viewCart(Scanner sc, int buyerId) {

     List<Product> cartItems = new Cart_Repo().getCartItemsByBuyerId(buyerId);

    if (cartItems.isEmpty()) {
        System.out.println(Message.EMPTY_CART);
        return;
    }

    System.out.println(Message.CART_UPPER_FRAME);
    System.out.printf("║ %-3s | %-20s | %-8s | %-8s | %-50s    ║\n", 
                      "No", "Product Name", "Price", "Qty", "Description");
    System.out.println(Message.CART_MIDDLE_FRAME);

    int index = 1;
    for (Product p : cartItems) {
        System.out.printf("║ %-3d | %-20s | %-8.2f | %-8d | %-50s    ║\n",
                          index++,
                          p.getProduct_Name(),
                          p.getProduct_Price(),
                          p.getProduct_Quantity(),  
                          p.getProduct_Description());
    }
    System.out.println(Message.CART_LOWER_FRAME);
   
    }
}
