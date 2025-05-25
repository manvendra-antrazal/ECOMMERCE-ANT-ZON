package Services;
import Constants.Message;
import Controller.Wishlist_Controller;
import Modal.Product;
import Repository.Product_Repo;
import Repository.Wishlist_repo;
import java.util.List;
import java.util.Scanner;

public class Wishlist_Service { 
 
  Wishlist_repo wishlistRepo = new Wishlist_repo();

    public boolean addToWishlist(int buyerId, int productId, int companyId) {
        // Check if already exists
        boolean exists = wishlistRepo.isProductInWishlist(buyerId, productId, companyId);

        if (exists) {
            return false;
        }
        return wishlistRepo.addToWishlist(buyerId, productId, companyId);
    }


    // viewing wishlist 
    public static void viewWishlist(Scanner sc, int buyerId) {
    Wishlist_repo repo = new Wishlist_repo();
    List<Product> wishlist = repo.getWishlistByBuyerId(buyerId);

    if (wishlist.isEmpty()) {
        System.out.println(Message.EMPTY_WISHLIST);
        return;
    }

    System.out.println(Message.WISHLIST_UPPER_FRAME);
    int index = 1;
    for (Product p : wishlist) {
        System.out.printf("║ %-3d ║ %-20s ║ %-8.2f ║ %-8d ║ %-50s ║\n",
                index++, p.getProduct_Name(), p.getProduct_Price(), p.getProduct_Quantity(), p.getProduct_Description());
    }
    System.out.println(Message.WISHLIST_LOWER_FRAME);

    System.out.print("Choose Option [A/B/C/D]: ");
    String option = sc.nextLine().trim().toUpperCase();

    switch (option) {
        case "A":
            return; // back to main menu
        case "B":
            System.out.print("Enter product number to ADD to cart: ");
            Wishlist_Controller.handleAddToCartFromWishlist(sc, wishlist, buyerId);
            break;
        case "C":
            System.out.print("Enter product number to REMOVE from wishlist: ");
            Wishlist_Controller.handleRemoveFromWishlist(sc, wishlist, buyerId);
            break;
        case "D":
            System.out.println("Logging out...");
            System.exit(0);
            break;
        default:
            System.out.println("Invalid option selected.");
        }
    }


   
}
