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

public class AdminRepository {

    private static final AdminRepository instance = new AdminRepository();
    private AdminRepository() {}

    public static AdminRepository getInstance() {
        return instance;
    }

    public int validateAdminLogin(String username, String password, int companyId) {
        String query = Queries.VALIDATE_ADMIN_LOGIN;
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, username);
            ps.setString(2, password);
            ps.setInt(3, companyId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("admin_id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(Message.ERROR_VALIDATE_ADMIN_LOGIN, e);
        }
        return -1;
    }

    public boolean usernameExists(String username, int companyId) {
        String query = Queries.VALIDATE_NEW_ADMIN_REGISTER;
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, username);
            ps.setInt(2, companyId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(Message.ERROR_REGISTER_ADMIN, e);
        }
        return false;
    }

    public boolean registerNewAdmin(String username, String password, int companyId) {
        String query = Queries.INSERT_INTO_ADMIN;
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, username);
            ps.setString(2, password);
            ps.setInt(3, companyId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(Message.ERROR_REGISTER_ADMIN, e);
        }
    }

    public double fetchTotalRevenue(int companyId) {
        String query = Queries.FETCH_TOTAL_REVENUE;
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, companyId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                double revenue = rs.getDouble("revenue");
                return rs.wasNull() ? 0.0 : revenue;
            }
        } catch (SQLException e) {
            throw new RuntimeException(Message.ERROR_FETCHING_REVENUE, e);
        }
        return 0.0;
    }

    public List<Product> fetchMostLikedProducts(int companyId) {
        List<Product> productList = new ArrayList<>();
        String query = Queries.FETCH_MOST_LIKED_PRODUCTS;
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, companyId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                product.setProduct_Id(rs.getInt("product_id"));
                product.setProduct_Name(rs.getString("product_name"));
                product.setLikes(rs.getInt("likes"));
                productList.add(product);
            }

        } catch (SQLException e) {
            throw new RuntimeException(Message.ERROR_FETCHING_MOST_LIKED, e);
        }
        return productList;
    }

    public List<Product> fetchBestSellerProducts(int companyId) {
        List<Product> productList = new ArrayList<>();
        String query = Queries.FETCH_BEST_SELLER_PRODUCTS;

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, companyId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                product.setProduct_Id(rs.getInt("product_id"));
                product.setProduct_Name(rs.getString("product_name"));
                product.setProduct_Quantity(rs.getInt("total_sold")); 
                productList.add(product);
            }

        } catch (SQLException e) {
            throw new RuntimeException(Message.ERROR_FETCHING_BEST_PRODUCTS, e);
        }
        return productList;
    }
}
