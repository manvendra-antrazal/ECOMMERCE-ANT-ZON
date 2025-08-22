package Controller;

import Modal.Product;
import Services.CartService;
import java.util.List;

public class CartController {

    private static final CartController INSTANCE = new CartController();
    private static final CartService cartService = CartService.getInstance();
    private CartController() {}

    public static CartController getInstance() {
        return INSTANCE;
    }

    public void addProductToCart(int buyerId, int productId, int companyId, int quantity) {
        cartService.addToCart(buyerId, productId, companyId, quantity);
    }

    public boolean removeProductFromCart(int buyerId, int productId) {
        return cartService.removeProduct(buyerId, productId);
    }

    public void updateProductQuantity(int buyerId, int productId, int newQuantity) {
        cartService.updateCartQuantity(buyerId, productId, newQuantity);
    }

    public List<Product> viewCart(int buyerId) {
        return cartService.getCartItems(buyerId);
    }

    public double calculateTotal(int buyerId) {
        return cartService.getTotalPrice(buyerId);
    }

    public void clearCart(int buyerId) {
        cartService.clearCart(buyerId);
    }
}
