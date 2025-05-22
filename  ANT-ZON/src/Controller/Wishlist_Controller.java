package Controller;

import Constants.Message;
import Modal.Company;
import Modal.Product;
import Services.Wishlist_Service;
import java.util.Scanner;

public class Wishlist_Controller {

    public static void handleAddToWishlist(Scanner inputScanner, Product selectedProduct, Company company, int buyerId) {
        Wishlist_Service wishlistService = new Wishlist_Service();

        boolean success = wishlistService.addToWishlist(buyerId, selectedProduct.getProduct_Id(), company.getCompany_Id());

        if (success) {
            System.out.println(Message.PRODUCT_ADDED_TO_WISHLIST);
        } else {
            System.out.println(Message.PRODUCT_EXISTS_IN_WIHSLIST);
        }
    }    
}
