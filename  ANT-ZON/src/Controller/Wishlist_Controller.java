package Controller;

import Modal.Company;
import Modal.Product;
import Services.Wishlist_Service;
import java.util.Scanner;

public class Wishlist_Controller {

    public static void handleAddToWishlist(Scanner inputScanner, Product selectedProduct, Company company, int buyerId) {
        Wishlist_Service wishlistService = new Wishlist_Service();

        boolean success = wishlistService.addToWishlist(buyerId, selectedProduct.getProduct_Id(), company.getCompany_Id());

        if (success) {
            System.out.println("Product added to your wishlist successfully!");
        } else {
            System.out.println("Product is already in your wishlist or failed to add.");
        }
    }    
}
