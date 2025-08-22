package Services;

import Constants.Message;
import java.util.List;

import Modal.Product;
import Repository.Wishlist_repo;


public class WishlistService {

    private final Wishlist_repo wishlistRepo = new Wishlist_repo();

    public boolean addToWishlist(int buyerId, int productId, int companyId) {
        validateIds(buyerId, productId, companyId);
        try {
            if (wishlistRepo.isProductInWishlist(buyerId, productId, companyId)) {
                return false; 
            }
            return wishlistRepo.addToWishlist(buyerId, productId, companyId);
        } catch (Exception e) {
            throw new RuntimeException(Message.WISHLIST_ADD_ERROR);
        }
    }

    public boolean removeFromWishlist(int buyerId, int productId) {
        if (buyerId <= 0 || productId <= 0) {
            throw new IllegalArgumentException();
        }
        try {
            return wishlistRepo.removeFromWishlist(buyerId, productId);
        } catch (Exception e) {
            throw new RuntimeException(Message.FAILED_REMOVED_PRODUCT_FROM_WISHLIST);
        }
    }

    public List<Product> getWishlist(int buyerId) {
        if (buyerId <= 0) {
            throw new IllegalArgumentException();
        }
        try {
            return Wishlist_repo.getWishlistByBuyerId(buyerId);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
    
    private void validateIds(int buyerId, int productId, int companyId) {
        if (buyerId <= 0) throw new IllegalArgumentException();
        if (productId <= 0) throw new IllegalArgumentException();
        if (companyId <= 0) throw new IllegalArgumentException();
    }
}
