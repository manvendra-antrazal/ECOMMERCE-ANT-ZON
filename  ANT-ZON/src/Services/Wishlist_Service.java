package Services;



import Repository.Wishlist_repo;

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
}
