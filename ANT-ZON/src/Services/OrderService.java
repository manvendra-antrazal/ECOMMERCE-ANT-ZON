package Services;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import Constants.Message;
import Modal.Order;
import Modal.Product;
import Repository.CartRepository;
import Repository.CategoryRepository;
import Repository.OrderRepo;
import Repository.ProductRepo;


public class OrderService {
    private static final OrderService INSTANCE = new OrderService();
    private final ProductRepo productRepo = ProductRepo.getInstance();
    private final CartRepository cartRepo = CartRepository.getInstance();
    private final OrderRepo orderRepo = OrderRepo.getInstance();
    private final CategoryRepository categoryRepo = CategoryRepository.getInstance();

    private OrderService() {}

    public static OrderService getInstance() {
        return INSTANCE;
    }

    public double placeOrderReturnPrice(Product product, int buyerId, int quantity) {
        if (product == null) {
            throw new IllegalArgumentException();
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException(Message.INVALID_PRODUCT_QUANTITY);
        }

        try {
            int stock = productRepo.getProductQuantity(product.getProduct_Id());
            if (quantity > stock) {
                throw new IllegalArgumentException(
                    String.format(Message.STOCK_INSUFFICIENT, product.getProduct_Name(), stock));
            }

            double originalPrice = product.getProduct_Price();
            double discountPercent = computeDiscountPercent(product);
            double discountedUnit = originalPrice - (originalPrice * discountPercent / 100.0);
            double totalPrice = discountedUnit * quantity;
            boolean stockReduced = productRepo.reduceStock(product.getProduct_Id(), quantity);
            if (!stockReduced) {
                throw new RuntimeException(Message.FAILED_UPDATE_STOCK);
            }
            String txnId = generateTxnId(Message.TRANSACTION_ID_PREFIX);
            boolean inserted = orderRepo.insertOrder(
                buyerId,
                product.getProduct_Id(),
                product.getCompany_ID(),
                quantity,
                totalPrice,
                txnId,
                product.getProduct_Name()
            );
            if (!inserted) {
                throw new RuntimeException(Message.ORDER_INSERT_FAILED);
            }
            try {
                cartRepo.removeProductFromCart(buyerId, product.getProduct_Id());
            } catch (SQLException ignore) {
            }

            return totalPrice;

        } catch (IllegalArgumentException e) {
            throw e;
        } catch (SQLException e) {
            throw new RuntimeException(Message.ORDER_FAILED);
        } catch (Exception e) {
            throw new RuntimeException(Message.ORDER_FAILED);
        }
    }

    private double computeDiscountPercent(Product product) throws SQLException {
        double discountPercent = 0.0;

        String productNameLower = product.getProduct_Name() != null
                ? product.getProduct_Name().toLowerCase()
                : "";

        if (productNameLower.contains("dell 7640") || productNameLower.contains("lenovo 5540")) {
            discountPercent += 2.5;
        }

        Integer furnitureCatId = categoryRepo.getCategoryIdByName("furniture");
        Integer eventSubCatId = categoryRepo.getSubCategoryIdByName("event");

        if (furnitureCatId != null && eventSubCatId != null) {
            if (product.getCategory_ID() == furnitureCatId && product.getSub_cat_ID() == eventSubCatId) {
                discountPercent += 2.5;
            }
        }

        return discountPercent;
    }

    private String generateTxnId(String prefix) {
        int rnd = new Random().nextInt(99_999_999);
        return prefix + rnd;
    }

    public List<Order> getOrdersByBuyerId(int buyerId) {
        try {
            return orderRepo.getOrdersByBuyerId(buyerId);
        } catch (SQLException e) {
            throw new RuntimeException(Message.ORDER_FETCH_FAILED);
        }
    }
}
