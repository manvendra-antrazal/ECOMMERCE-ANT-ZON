package Services;
import Constants.Message;
import Modal.Product;
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
        System.out.printf("║ %-3d ║ %-20s ║ %-8.2f ║ %-8d ║ %-50s    ║\n",
                          index++,
                          p.getProduct_Name(),
                          p.getProduct_Price(),
                          p.getProduct_Quantity(),
                          p.getProduct_Description());
    }

    System.out.println(Message.WISHLIST_LOWER_FRAME);
    System.out.println("\nEnter the number of a product to remove from wishlist (or press ENTER to go back):");

    String input = sc.nextLine().trim();

    if (input.isEmpty()) {
        return; // back to previous menu
    }

    try {
        int choice = Integer.parseInt(input);
        if (choice < 1 || choice > wishlist.size()) {
            System.out.println("Invalid choice.");
        } else {
            Product toRemove = wishlist.get(choice - 1);
            repo.removeFromWishlist(buyerId, toRemove.getProduct_Id());
            System.out.println("Product removed from your wishlist.");
        }
    } catch (NumberFormatException e) {
        System.out.println("Invalid input.");
    }
    }


   
}
