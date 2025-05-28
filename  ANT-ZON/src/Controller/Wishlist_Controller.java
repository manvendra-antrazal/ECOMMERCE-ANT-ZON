package Controller;

import Constants.Message;
import Modal.Company;
import Modal.Product;
import Repository.Cart_Repo;
import Repository.Product_Repo;
import Repository.Wishlist_repo;
import Services.Cart_Service;
import Services.Wishlist_Service;

import java.util.List;
import java.util.Scanner;

public class Wishlist_Controller {

    // Add product to wishlist
    public static void handleAddToWishlist(Scanner inputScanner, Product selectedProduct, Company company, int buyerId) {
        try {
            Wishlist_Service wishlistService = new Wishlist_Service();
            boolean success = wishlistService.addToWishlist(buyerId, selectedProduct.getProduct_Id(), company.getCompany_Id());

            if (success) {
                System.out.println(Message.PRODUCT_ADDED_TO_WISHLIST);
            } else {
                System.out.println(Message.PRODUCT_EXISTS_IN_WISHLIST);
            }
        } catch (Exception e) {
            System.out.println(Message.WISHLIST_ADD_ERROR + e.getMessage());
            e.printStackTrace();
        }
    }

    // Move product from wishlist to cart
    public static void handleAddToCartFromWishlist(Scanner sc, List<Product> wishlist, int buyerId) {
        try {
            System.out.print(Message.SELECT_PRODUCT_INDEX);
            int index = Integer.parseInt(sc.nextLine().trim());

            if (index < 1 || index > wishlist.size()) {
                System.out.println(Message.INVALID_SELECTION);
                return;
            }

            Product product = wishlist.get(index - 1);
            Product actualProduct = new Product_Repo().getProductById(product.getProduct_Id());

            if (actualProduct == null) {
                System.out.println(Message.PRODUCT_OUT_OF_STOCK);
                return;
            }

            Cart_Repo cartRepo = new Cart_Repo();
            if (cartRepo.isProductInCart(product.getProduct_Id(), product.getCompany_ID(), buyerId)) {
                System.out.println(Message.PRODUCT_EXISTS_IN_CART);
                return;
            }

            Cart_Service.addToCart(product, product.getCompany_ID(), buyerId, 1);
            new Wishlist_repo().removeFromWishlist(buyerId, product.getProduct_Id());

            System.out.println(Message.PRODUCT_ADDED_TO_CART);

        } catch (NumberFormatException e) {
            System.out.println(Message.VALID_NUMBER);
        } catch (Exception e) {
            System.out.println(Message.CART_ERROR + e.getMessage());
            e.printStackTrace();
        }
    }

    // Remove product from wishlist
    public static void handleRemoveFromWishlist(Scanner sc, List<Product> wishlist, int buyerId) {
        try {
            System.out.print(Message.SELECT_PRODUCT_INDEX);
            int prodNo = Integer.parseInt(sc.nextLine().trim());

            if (prodNo < 1 || prodNo > wishlist.size()) {
                System.out.println(Message.INVALID_INPUT);
                return;
            }

            Product product = wishlist.get(prodNo - 1);
            boolean removed = new Wishlist_repo().removeFromWishlist(buyerId, product.getProduct_Id());

            if (removed) {
                System.out.println(Message.WISHLIST_PRODUCT_REMOVED);
            } else {
                System.out.println(Message.FAILED_REMOVED_PRODUCT_FROM_WISHLIST);
            }
        } catch (NumberFormatException e) {
            System.out.println(Message.VALID_NUMBER);
        } catch (Exception e) {
            System.out.println(Message.WISHLIST_REMOVE_ERROR + e.getMessage());
            e.printStackTrace();
        }
    }
}
