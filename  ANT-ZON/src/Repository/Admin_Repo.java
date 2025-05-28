package Repository;

import Constants.Message;
import Constants.Queries;
import Util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Modal.Product;

public class Admin_Repo {

    public static int validateAdminLogin(String username, String password, int companyId) throws SQLException {
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
            throw new SQLException(Message.ERROR_VALIDATE_ADMIN_LOGIN + e.getMessage(), e);
        }
        return -1;
    }

    public static boolean registerNewAdmin(String username, String password, int companyId) throws SQLException {
        try (Connection connection = DBConnection.getInstance().getConnection()) {
            String checkSql = Queries.VALIDATE_NEW_ADMIN_REGISTER;
            try (PreparedStatement ps = connection.prepareStatement(checkSql)) {
                ps.setString(1, username);
                ps.setInt(2, companyId);
                ResultSet rs = ps.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) return false;
            }

            String insertSql = Queries.INSERT_INTO_ADMIN;
            try (PreparedStatement ps = connection.prepareStatement(insertSql)) {
                ps.setString(1, username);
                ps.setString(2, password);
                ps.setInt(3, companyId);
                return ps.executeUpdate() > 0;
            }

        } catch (SQLException e) {
            throw new SQLException(Message.ERROR_REGISTER_ADMIN + e.getMessage(), e);
        }
    }

    public static double fetchTotalRevenue(int companyId) throws SQLException {
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
            throw new SQLException(Message.ERROR_FETCHING_REVENUE + e.getMessage(), e);
        }
        return 0.0;
    }

    public static List<Product> fetchMostLikedProducts(int companyId) throws SQLException {
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
            throw new SQLException(Message.ERROR_FETCHING_MOST_LIKED + e.getMessage(), e);
        }

        return productList;
    }

    public static List<Product> fetchBestSellerProducts(int companyId) throws SQLException {
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
                product.setProduct_Quantity(rs.getInt("total_sold"));  // Using quantity to store total sold

                productList.add(product);
            }

        } catch (SQLException e) {
            throw new SQLException(Message.ERROR_FETCHING_BEST_PRODUCTS + e.getMessage(), e);
        }

        return productList;
    }
}

    

    // public static void deleteBuyerById(Scanner scanner, int companyId) {

    //     System.out.print("Enter Buyer ID to delete: ");
    //     String input = scanner.nextLine().trim();

    //     int buyerId;
    //     try {
    //         buyerId = Integer.parseInt(input);
    //     } catch (NumberFormatException e) {
    //         System.out.println("-> Invalid Buyer ID. Please enter a valid number.");
    //         return;
    //     }

    //     String selectQuery = "SELECT Buyer_ID, Buyer_UserName, Buyer_Email, Buyer_Role, city, state, county, local_Address, Buyer_Mob_No " +
    //                         "FROM buyer WHERE Buyer_ID = ? AND company_ID = ?";

    //     try (Connection connection = DBConnection.getInstance().getConnection();
    //         PreparedStatement selectPs = connection.prepareStatement(selectQuery)) {

    //         selectPs.setInt(1, buyerId);
    //         selectPs.setInt(2, companyId);

    //         try (ResultSet rs = selectPs.executeQuery()) {
    //             if (rs.next()) {
    //                 System.out.println("Buyer found:");
    //                 System.out.println("ID          : " + rs.getInt("Buyer_ID"));
    //                 System.out.println("Username    : " + rs.getString("Buyer_UserName"));
    //                 System.out.println("Email       : " + rs.getString("Buyer_Email"));
    //                 System.out.println("Role        : " + rs.getString("Buyer_Role"));
    //                 System.out.println("City        : " + rs.getString("city"));
    //                 System.out.println("State       : " + rs.getString("state"));
    //                 System.out.println("County      : " + rs.getString("county"));
    //                 System.out.println("Address     : " + rs.getString("local_Address"));
    //                 System.out.println("Mobile No.  : " + rs.getString("Buyer_Mob_No"));

    //                 System.out.print("Are you sure you want to delete this buyer? (Y/N): ");
    //                 String confirm = scanner.nextLine().trim().toUpperCase();

    //                 if (confirm.equals("Y")) {
    //                     String deleteQuery = "DELETE FROM buyer WHERE Buyer_ID = ? AND company_ID = ?";
    //                     try (PreparedStatement deletePs = connection.prepareStatement(deleteQuery)) {
    //                         deletePs.setInt(1, buyerId);
    //                         deletePs.setInt(2, companyId);
    //                         int deleted = deletePs.executeUpdate();

    //                         if (deleted > 0) {
    //                             System.out.println("-> Buyer deleted successfully.");
    //                         } else {
    //                             System.out.println("-> Failed to delete buyer.");
    //                         }
    //                     }
    //                 } else {
    //                     System.out.println("-> Deletion cancelled.");
    //                 }
    //             } else {
    //                 System.out.println("-> No buyer found with that ID in this company.");
    //             }
    //         }

    //     } catch (SQLException e) {
    //         System.out.println("-> Error occurred: " + e.getMessage());
    //     }
    // }

