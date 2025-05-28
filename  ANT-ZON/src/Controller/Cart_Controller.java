package Controller;

import Constants.Message;
import Modal.Company;
import Modal.Product;
import Services.Cart_Service;
import java.util.Scanner;

public class Cart_Controller {
    
    public static void handleAddToCart(Scanner sc, Product product, Company company, int buyerId) {
        while (true) {
            int defaultQuantity = 1;
            try {
                
                if (defaultQuantity > product.getProduct_Quantity()) {
                    System.out.println(Message.QUANTITY_EXCEED);
                    continue;
                }

                Cart_Service.addToCart(product, company.getCompany_Id(), buyerId, defaultQuantity);
                // System.out.println(Message.PRODUCT_ADDED_TO_CART);
                break;
            } catch (NumberFormatException e) {
                System.out.println(Message.VALID_NUMBER);
            } catch (Exception e) {
                System.out.println(Message.CART_ERROR);
                e.printStackTrace();  
                break;  
            }
        }
    }
}
  