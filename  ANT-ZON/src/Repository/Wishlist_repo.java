package Repository;

import Util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
}
