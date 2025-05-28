package Repository;

import Constants.Message;
import Constants.Queries;
import Modal.Product;
import Util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Cart_Repo {

    public void updateCartQuantity(int buyerId, int productId, int newQty) {
        try (Connection connection = DBConnection.getInstance().getConnection()) {
            String query = Queries.UPDATE_CART_QUANTITY;
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, newQty);
                ps.setInt(2, buyerId);
                ps.setInt(3, productId);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(Message.DATABASE_ERROR + ": " + e.getMessage(), e);
        }
    }

    public void insertCartItem(int productId, int companyId, int buyerId, int quantity) {
        try (Connection connection = DBConnection.getInstance().getConnection()) {
            String query = Queries.PRODUCT_ADD_TO_CART;
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, productId);
                statement.setInt(2, companyId);
                statement.setInt(3, buyerId);
                statement.setInt(4, quantity);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(Message.DATABASE_ERROR + ": " + e.getMessage(), e);
        }
    }

    public List<Product> getCartItemsByBuyerId(int buyerId) {
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
            throw new RuntimeException(Message.DATABASE_ERROR + ": " + e.getMessage(), e);
        }
        return cartItems;
    }

    public void removeProductFromCart(int buyerId, int productId) {
        String query = Queries.REMOVE_FROM_CART;
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, buyerId);
            stmt.setInt(2, productId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(Message.FAILED_REMOVED_PRODUCT_FROM_CART + ": " + e.getMessage(), e);
        }
    }

    public boolean removeFromCart(int buyerId, int productId) {
        String query = Queries.REMOVE_FROM_CART;
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, buyerId);
            statement.setInt(2, productId);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            throw new RuntimeException(Message.FAILED_REMOVED_PRODUCT_FROM_CART + ": " + e.getMessage(), e);
        }
    }

    public boolean isProductInCart(int productId, int companyId, int buyerId) {
        String query = Queries.IS_PRODUCT_IN_CART;
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, productId);
            stmt.setInt(2, companyId);
            stmt.setInt(3, buyerId);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); // true if already in cart
            }

        } catch (SQLException e) {
            throw new RuntimeException(Message.DATABASE_ERROR + ": " + e.getMessage(), e);
        }
    }
}
