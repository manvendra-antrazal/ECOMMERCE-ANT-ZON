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

public class Wishlist_repo {

    public boolean isProductInWishlist(int buyerId, int productId, int companyId) throws Exception {
        try (Connection connection = DBConnection.getInstance().getConnection()) {
            String query = Queries.IS_PRODUCT_IN_WISHLIST;
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, buyerId);
            ps.setInt(2, productId);
            ps.setInt(3, companyId);
            ResultSet rs = ps.executeQuery();

            return rs.next();
        } catch (Exception e) {
            throw new Exception(Message.WISHLIST_CHECK_ERROR + e.getMessage(), e);
        }
    }

    public boolean addToWishlist(int buyerId, int productId, int companyId) throws Exception {
        try (Connection connection = DBConnection.getInstance().getConnection()) {
            String query = Queries.ADD_TO_WISHLIST;
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, companyId);
            ps.setInt(2, buyerId);
            ps.setInt(3, productId);

            if (ps.executeUpdate() > 0) {
                // Increment likes after insert 
                String likeUpdateQuery = Queries.LIKE_INCREMENT;
                PreparedStatement updateLikes = connection.prepareStatement(likeUpdateQuery);
                updateLikes.setInt(1, productId);
                updateLikes.executeUpdate();

                return true;
            }
        } catch (Exception e) {
            throw new Exception(Message.ADD_TO_WISHLIST_ERROR + e.getMessage(), e);
        }
        return false;
    }

    public static List<Product> getWishlistByBuyerId(int buyerId) throws Exception {
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
            throw new Exception(Message.GET_WISHLIST_ERROR + e.getMessage(), e);
        }

        return wishlist;
    }

    public boolean removeFromWishlist(int buyerId, int productId) throws Exception {
        String deleteWishlistQuery = Queries.REMOVE_FROM_WISHLIST;
        String decrementLikesQuery = Queries.REMOVE_FROM_WISHLIST_DESCREASE_LIKE;

        try (Connection conn = DBConnection.getInstance().getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement deleteStmt = conn.prepareStatement(deleteWishlistQuery);
                 PreparedStatement updateLikesStmt = conn.prepareStatement(decrementLikesQuery)) {

                deleteStmt.setInt(1, buyerId);
                deleteStmt.setInt(2, productId);
                int rowsDeleted = deleteStmt.executeUpdate();

                if (rowsDeleted == 0) {
                    conn.rollback();
                    return false;
                }

                updateLikesStmt.setInt(1, productId);
                updateLikesStmt.executeUpdate();

                conn.commit();
                return true;

            } catch (SQLException e) {
                conn.rollback();
                throw new Exception(Message.REMOVE_PRODUCT_FROM_WISHLIST_AND_LIKE_DECREASE + e.getMessage(), e);
            } finally {
                conn.setAutoCommit(true);
            }

        } catch (SQLException e) {
            throw new Exception(Message.CONNECTION_ERROR + e.getMessage(), e);
        }
    }
}
