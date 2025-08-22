package Services;

import Constants.Message;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Modal.Product;
import Repository.CartRepository;
import Repository.ProductRepo;


public class CartService {

    private static final CartService INSTANCE = new CartService();

    private final CartRepository cartRepo = CartRepository.getInstance();
    private final ProductRepo productRepo = ProductRepo.getInstance();

    private CartService() {}

    public static CartService getInstance() {
        return INSTANCE;
    }

   
    public void addToCart(int buyerId, int productId, int companyId, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException(Message.INVALID_PRODUCT_QUANTITY);
        }
        try {
            boolean exists = cartRepo.isProductInCart(productId, companyId, buyerId);
            if (exists) {
                cartRepo.updateCartQuantity(buyerId, productId, quantity);
            } else {
                cartRepo.insertCartItem(productId, companyId, buyerId, quantity);
            }
        } catch (SQLException e) {
            throw new RuntimeException(Message.UPDATE_FAILED);
        }
    }
  
    public boolean removeProduct(int buyerId, int productId) {
        try {
            return cartRepo.removeFromCart(buyerId, productId);
        } catch (SQLException e) {
            throw new RuntimeException(Message.FAILED_REMOVED_PRODUCT_FROM_CART);
        }
    }

    public void updateCartQuantity(int buyerId, int productId, int newQuantity) {
        try {
            if (newQuantity < 0) {
                throw new IllegalArgumentException("Quantity cannot be negative.");
            }
            if (newQuantity == 0) {
                cartRepo.removeFromCart(buyerId, productId);
            } else {
                cartRepo.updateCartQuantity(buyerId, productId, newQuantity);
            }
        } catch (SQLException e) {
            throw new RuntimeException(Message.UPDATE_FAILED);
        }
    }

    public List<Product> getCartItems(int buyerId) {
        try {
            List<Product> items = cartRepo.getCartItemsByBuyerId(buyerId);
            return items != null ? items : new ArrayList<>();
        } catch (SQLException e) {
            throw new RuntimeException(Message.FETCHING_FAILED);
        }
    }


    public double getTotalPrice(int buyerId) {
        try {
            List<Product> items = cartRepo.getCartItemsByBuyerId(buyerId);
            double total = 0.0;
            if (items != null) {
                for (Product p : items) {
                    double unit = p.getProduct_Price();
                    int qty = p.getProduct_Quantity(); 
                    total += unit * qty;
                }
            }
            return total;
        } catch (SQLException e) {
            throw new RuntimeException(Message.ERROR_VIEW_TOTAL);
        }
    }

    public void clearCart(int buyerId) {
        try {
            List<Product> items = cartRepo.getCartItemsByBuyerId(buyerId);
            if (items == null || items.isEmpty()) return;
            for (Product p : items) {
                cartRepo.removeFromCart(buyerId, p.getProduct_Id());
            }
        } catch (SQLException e) {
            throw new RuntimeException(Message.FAILED_CLEAR_CART);
        }
    }
}
