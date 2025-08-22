package Controller;

import Modal.Product;
import Services.WishlistService;
import java.util.List;

public class WishlistController {

    private static final WishlistController INSTANCE = new WishlistController();
    private static final WishlistService wishlistService = new WishlistService();

    private WishlistController() {}

    public static WishlistController getInstance() {
        return INSTANCE;
    }

    public boolean addProductToWishlist(int buyerId, int productId, int companyId) {
        return wishlistService.addToWishlist(buyerId, productId, companyId);
    }

    public boolean removeProductFromWishlist(int buyerId, int productId) {
        return wishlistService.removeFromWishlist(buyerId, productId);
    }

    public List<Product> viewWishlist(int buyerId) {
        return wishlistService.getWishlist(buyerId);
    }
}
