package Services;

import java.sql.*;
import java.util.Scanner;
import Util.DBConnection;

public class AdminService {

    public static void viewTotalRevenue(int companyId) {
        String sql = " SELECT SUM(total_price) AS revenue FROM orders WHERE company_id = ?";

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, companyId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                double revenue = rs.getDouble("revenue");
                System.out.printf("-> Total Revenue: ₹%.2f\n", revenue);
            } else {
                System.out.println("-> No revenue found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void viewMostLikedProducts(int companyId) {
        String sql = """
            SELECT product_name, likes
            FROM product
            WHERE company_id = ?
            ORDER BY likes DESC
            LIMIT 5;
        """;

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, companyId);
            ResultSet rs = ps.executeQuery();

            System.out.println("-> Most Liked Products:");
            int i = 1;
            while (rs.next()) {
                System.out.printf("   %d. %s (Likes: %d)\n",
                    i++, rs.getString("product_name"), rs.getInt("likes"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void viewBestSellerProducts(int companyId) {
        String sql = """
            SELECT product_name, SUM(quantity) AS sold
            FROM orders
            WHERE company_id = ?
            GROUP BY product_name
            ORDER BY sold DESC
            LIMIT 5;
        """;

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, companyId);
            ResultSet rs = ps.executeQuery();

            System.out.println("-> Best Seller Products:");
            int i = 1;
            while (rs.next()) {
                System.out.printf("   %d. %s (Sold: %d units)\n",
                    i++, rs.getString("product_name"), rs.getInt("sold"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteBuyerAndSeller(Scanner scanner, int companyId) {
        System.out.print("-> Enter Buyer or Seller Email to delete: ");
        String email = scanner.nextLine().trim();

        String deleteBuyer = "DELETE FROM buyer WHERE email = ? AND company_id = ?";
        String deleteSeller = "DELETE FROM seller WHERE email = ? AND company_id = ?";

        try (Connection connection = DBConnection.getInstance().getConnection()) {
            PreparedStatement ps = connection.prepareStatement(deleteBuyer);
            ps.setString(1, email);
            ps.setInt(2, companyId);
            int buyerDeleted = ps.executeUpdate();

            ps = connection.prepareStatement(deleteSeller);
            ps.setString(1, email);
            ps.setInt(2, companyId);
            int sellerDeleted = ps.executeUpdate();

            if (buyerDeleted > 0) {
                System.out.println("-> Buyer deleted successfully.");
            } else if (sellerDeleted > 0) {
                System.out.println("-> Seller deleted successfully.");
            } else {
                System.out.println("-> No matching buyer or seller found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
