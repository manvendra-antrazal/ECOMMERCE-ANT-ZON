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

public class Wishlist_repo {

        public boolean isProductInWishlist(int buyerId, int productId, int companyId) {
        try (Connection connection = DBConnection.getInstance().getConnection()) {
            String query = "SELECT * FROM wishlist WHERE buyer_id = ? AND product_id = ? AND company_id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, buyerId);
            ps.setInt(2, productId);
            ps.setInt(3, companyId);
            ResultSet rs = ps.executeQuery();

            return rs.next(); // already exists
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addToWishlist(int buyerId, int productId, int companyId) {

        try (Connection connection = DBConnection.getInstance().getConnection()) {
            String query = "INSERT INTO wishlist (company_id, buyer_id, product_id, added_on) VALUES (?, ?, ?, NOW())";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, companyId);
            ps.setInt(2, buyerId);
            ps.setInt(3, productId);

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Get wishlist items for a specific buyer
    public static List<Product> getWishlistByBuyerId(int buyerId) {
        List<Product> wishlist = new ArrayList<>();

        String query = Queries.GET_WISHLIST_BY_BUYER_ID;

         try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, buyerId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product p = new Product();
                p.setProduct_Id(rs.getInt("product_id"));
                p.setProduct_Name(rs.getString("product_name"));
                p.setProduct_Description(rs.getString("product_description"));
                p.setProduct_Price(rs.getDouble("product_price"));
                p.setProduct_Quantity(rs.getInt("product_quantity"));
                p.setCompany_ID(rs.getInt("company_id"));
                p.setSeller_ID(rs.getInt("seller_id"));
                p.setCategory_ID(rs.getInt("category_id"));
                p.setSub_cat_ID(rs.getInt("sub_cat_id"));
                wishlist.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return wishlist;
    }

    // Remove item from wishlist for a buyer
    // Wishlist_Repo.java
public boolean removeFromWishlist(int buyerId, int productId) {
    String query = "DELETE FROM wishlist WHERE buyer_id = ? AND product_id = ?";
    try (Connection conn = DBConnection.getInstance().getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {

        stmt.setInt(1, buyerId);
        stmt.setInt(2, productId);

        int rowsAffected = stmt.executeUpdate();
        return rowsAffected > 0;

    } catch (SQLException e) {
        System.out.println("Failed to remove from wishlist: " + e.getMessage());
        return false;
    }
    }

}
