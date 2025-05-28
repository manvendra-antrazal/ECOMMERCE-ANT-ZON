package Services;

import Constants.Message;
import Controller.CompanyController;
import Controller.Wishlist_Controller;
import Modal.Product;
import Repository.Product_Repo;
import Repository.Wishlist_repo;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Wishlist_Service {

    Wishlist_repo wishlistRepo = new Wishlist_repo();

    public boolean addToWishlist(int buyerId, int productId, int companyId) {
        try {
            boolean exists = wishlistRepo.isProductInWishlist(buyerId, productId, companyId);
            if (exists) {
                System.out.println(Message.PRODUCT_EXISTS_IN_WISHLIST);
                return false;
            }
            return wishlistRepo.addToWishlist(buyerId, productId, companyId);
        } catch (Exception e) {
            System.out.println(Message.ERROR_ADDING_TO_WISHLIST);
            throw new RuntimeException(e);
        }
    }

    public static void viewWishlist(Scanner sc, int buyerId) {
        try {
            Wishlist_repo repo = new Wishlist_repo();
            List<Product> wishlist = repo.getWishlistByBuyerId(buyerId);

            if (wishlist.isEmpty()) {
                System.out.println(Message.EMPTY_WISHLIST);
                return;
            }

            System.out.println(Message.WISHLIST_UPPER_FRAME);
            int index = 1;
            for (Product p : wishlist) {
                System.out.printf("║ %-3d ║ %-20s ║ %-8.2f ║ %-8d ║ %-50s    ║\n",
                        index++, p.getProduct_Name(), p.getProduct_Price(), p.getProduct_Quantity(), p.getProduct_Description());
            }
            System.out.println(Message.WISHLIST_LOWER_FRAME);

            System.out.print(Message.CHOOSE_OPTION);
            String option = sc.nextLine().trim().toUpperCase();

            switch (option) {
                case "A":
                    return;
                case "B":
                    System.out.print(Message.ADD_TO_CART_PROMPT);
                    Wishlist_Controller.handleAddToCartFromWishlist(sc, wishlist, buyerId);
                    break;
                case "C":
                    System.out.print(Message.PRODUCT_REMOVE_FROM_WISHLIST);
                    Wishlist_Controller.handleRemoveFromWishlist(sc, wishlist, buyerId);
                    break;
                case "D":
                    CompanyController.startCompanySelection(sc);
                    break;
                default:
                    System.out.println(Message.INVALID_OPTION);
            }
        } catch (InputMismatchException e) {
            System.out.println(Message.INVALID_INPUT);
            throw e;
        } catch (Exception e) {
            System.out.println(Message.ERROR_VIEWING_WISHLIST);
            throw new RuntimeException(e);
        }
    }
}
