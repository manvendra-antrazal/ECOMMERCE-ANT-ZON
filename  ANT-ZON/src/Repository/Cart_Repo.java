package Repository;

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

    public void insertCartItem(int productId, int companyId, int buyerId, int quantity) {

        try (Connection connection = DBConnection.getInstance().getConnection()) {
            String query = Queries.PRODUCT_ADD_TO_CART;
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, productId);
            statement.setInt(2, companyId);
            statement.setInt(3, buyerId);
            statement.setInt(4, quantity);
            statement.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

     public List<Product> getCartItemsByBuyerId(int buyerId) {
        List<Product> cartItems = new ArrayList<>();

        String query = Queries.GET_CART_ITEM_BY_BUYERID;

        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, buyerId);
            ResultSet rs = ps.executeQuery();

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
                rs.getInt("sub_cat_id")
                );

                cartItems.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cartItems;
    }
    
}
