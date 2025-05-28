package Repository;

import Constants.Message;
import Util.DBConnection;
import java.sql.*;
import java.util.*;

import Constants.Queries;
import Modal.Order;

public class Order_Repo {

    public boolean insertOrder(int buyerId, int productId, int companyId, int quantity, double totalPrice, String transactionId, String productName) throws SQLException {
        String query = Queries.INSERT_ORDER;

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
            // Wrap and rethrow with custom error message
            throw new SQLException(Message.ORDER_INSERT_FAILED, e);
        }
    }


    public List<Order> getOrdersByBuyerId(int buyerId) throws SQLException {
        List<Order> orders = new ArrayList<>();
        String query = Queries.GET_ORDERS_BY_BUYERID;

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
            // Wrap and rethrow with custom error message
            throw new SQLException(Message.ORDER_FETCH_FAILED, e);
        }

        return orders;
    }
}
