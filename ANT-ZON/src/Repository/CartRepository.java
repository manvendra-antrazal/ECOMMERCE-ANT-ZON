package Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Constants.Message;
import Constants.Queries;
import Modal.Product;
import Util.DBConnection;

public class CartRepository {

    private static final CartRepository instance = new CartRepository();

    private CartRepository() {}

    public static CartRepository getInstance() {
        return instance;
    }

    public boolean updateCartQuantity(int buyerId, int productId, int newQty) throws SQLException {
        String query = Queries.UPDATE_CART_QUANTITY;
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, newQty);
            ps.setInt(2, buyerId);
            ps.setInt(3, productId);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new SQLException(Message.DATABASE_ERROR + ": " + e.getMessage(), e);
        }
        return false;
    }

    public boolean insertCartItem(int productId, int companyId, int buyerId, int quantity) throws SQLException {
        String query = Queries.PRODUCT_ADD_TO_CART;
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, productId);
            ps.setInt(2, companyId);
            ps.setInt(3, buyerId);
            ps.setInt(4, quantity);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new SQLException(Message.DATABASE_ERROR + ": " + e.getMessage(), e);
        }
        return false;
    }

    public List<Product> getCartItemsByBuyerId(int buyerId) throws SQLException {
        List<Product> cartItems = new ArrayList<>();
        String query = Queries.GET_CART_ITEM_BY_BUYERID;

        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, buyerId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Product p = new Product(
                            rs.getInt("product_id"),
                            rs.getString("product_name"),
                            rs.getString("product_description"),
                            rs.getDouble("product_price"),
                            rs.getInt("cart_quantity"),
                            rs.getInt("company_id"),
                            rs.getInt("seller_id"),
                            rs.getInt("category_id"),
                            rs.getInt("sub_cat_id"),
                            rs.getInt("likes")
                    );
                    cartItems.add(p);
                }
            }

        } catch (SQLException e) {
            throw new SQLException(Message.DATABASE_ERROR + ": " + e.getMessage(), e);
        }

        return cartItems;
    }

    public void removeProductFromCart(int buyerId, int productId) throws SQLException {
        String query = Queries.REMOVE_FROM_CART;
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, buyerId);
            ps.setInt(2, productId);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new SQLException(Message.FAILED_REMOVED_PRODUCT_FROM_CART + ": " + e.getMessage(), e);
        }
    }

    public boolean removeFromCart(int buyerId, int productId) throws SQLException {
        String query = Queries.REMOVE_FROM_CART;
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, buyerId);
            ps.setInt(2, productId);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            throw new SQLException(Message.FAILED_REMOVED_PRODUCT_FROM_CART + ": " + e.getMessage(), e);
        }
    }

    public boolean isProductInCart(int productId, int companyId, int buyerId) throws SQLException {
        String query = Queries.IS_PRODUCT_IN_CART;
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, productId);
            ps.setInt(2, companyId);
            ps.setInt(3, buyerId);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            throw new SQLException(Message.DATABASE_ERROR + ": " + e.getMessage(), e);
        }
    }
}
