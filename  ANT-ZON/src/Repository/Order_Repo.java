// Repository/Order_Repo.java
package Repository;

import Util.DBConnection;
import java.sql.*;
import java.util.*;

import Constants.Queries;

public class Order_Repo {

    public boolean insertOrder(int buyerId, int productId, int companyId, int quantity, double totalPrice, String transactionId) {
        String query = "INSERT INTO `order` (buyer_id, product_id, company_id, quantity, total_price, transaction_id) VALUES (?, ?, ?, ?, ?, ?)";
    
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, buyerId);
            ps.setInt(2, productId);
            ps.setInt(3, companyId);
            ps.setInt(4, quantity);
            ps.setDouble(5, totalPrice);
            ps.setString(6, transactionId);

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<String> getOrdersByBuyerId(int buyerId) {
        List<String> orders = new ArrayList<>();
        String query = Queries.GET_ORDER_BY_BUYERID;

        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, buyerId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String order = String.format(
                    "Order ID: %d | Product: %s | Qty: %d | Total: $%.2f | Date: %s | Txn ID: %s",
                    rs.getInt("order_id"),
                    rs.getString("product_name"),
                    rs.getInt("quantity"),
                    rs.getDouble("total_price"),
                    rs.getTimestamp("order_date"),
                    rs.getString("transaction_id")
                );
                orders.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }
}
