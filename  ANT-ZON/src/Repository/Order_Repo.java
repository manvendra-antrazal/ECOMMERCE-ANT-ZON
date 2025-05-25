// Repository/Order_Repo.java
package Repository;

import Util.DBConnection;
import java.sql.*;
import java.util.*;

import Constants.Queries;
import Modal.Order;
import Modal.Product;
import Repository.Product_Repo;

public class Order_Repo {

        public boolean insertOrder(int buyerId, int productId, int companyId, int quantity, double totalPrice, String transactionId, String productName) {
    String query = "INSERT INTO `order` (buyer_id, product_id, company_id, quantity, total_price, transaction_id, product_name) VALUES (?, ?, ?, ?, ?, ?, ?)";

    try (Connection conn = DBConnection.getInstance().getConnection();
         PreparedStatement ps = conn.prepareStatement(query)) {

        ps.setInt(1, buyerId);
        ps.setInt(2, productId);
        ps.setInt(3, companyId);
        ps.setInt(4, quantity);
        ps.setDouble(5, totalPrice);
        ps.setString(6, transactionId);
        ps.setString(7, productName);

        ps.executeUpdate();
        return true;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}




    public List<Order> getOrdersByBuyerId(int buyerId) {

    List<Order> orders = new ArrayList<>();
    String query = "SELECT order_id, product_name, quantity, total_price, order_date " +
                   "FROM `order` WHERE buyer_id = ? ORDER BY order_date DESC";

    try (Connection conn = DBConnection.getInstance().getConnection();
         PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, buyerId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Order order = new Order();
                    order.setOrderId(rs.getInt("order_id"));
                    order.setProductName(rs.getString("product_name"));
                    order.setQuantity(rs.getInt("quantity"));
                    order.setTotalPrice(rs.getDouble("total_price"));
                    order.setOrderDate(rs.getTimestamp("order_date"));


                    orders.add(order);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }


}
