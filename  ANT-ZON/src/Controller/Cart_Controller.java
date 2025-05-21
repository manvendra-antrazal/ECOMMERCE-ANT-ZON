package Controller;

import Constants.Message;
import java.util.Scanner;

import Modal.Company;
import Modal.Product;
import Services.Cart_Service;

public class Cart_Controller {
    
    public static void handleAddToCart(Scanner sc, Product product, Company company, int buyerId) {
        System.out.print("Enter quantity to add: ");
        int quantity = Integer.parseInt(sc.nextLine());
        Cart_Service.addToCart(product, company.getCompany_Id(), buyerId, quantity);
        System.out.println(Message.PRODUCT_ADDED_TO_CART);
    }
    
}
