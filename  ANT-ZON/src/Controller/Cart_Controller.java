package Controller;

import Constants.Message;
import java.util.Scanner;

import Modal.Company;
import Modal.Product;
import Services.Cart_Service;

public class Cart_Controller {
    
    public static void handleAddToCart(Scanner sc, Product product, Company company, int buyerId) {
        while (true) {
            System.out.print(Message.QUANTITY);
            String input = sc.nextLine().trim();

            try {
                int quantity = Integer.parseInt(input);

                if (quantity <= 0) {
                    System.out.println(Message.QUANTITY_GREATER_ZERO);
                    continue;
                }

                if (quantity > product.getProduct_Quantity()) {
                    System.out.println(Message.QUANTITY_EXCEED);
                    continue;
                }

                Cart_Service.addToCart(product, company.getCompany_Id(), buyerId, quantity);
                System.out.println(Message.PRODUCT_ADDED_TO_CART);
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
