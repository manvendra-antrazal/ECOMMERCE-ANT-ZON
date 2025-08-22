package Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Constants.Message;
import Constants.Queries;
import Modal.Order;
import Util.DBConnection;

public class OrderRepo {

    private static final OrderRepo instance = new OrderRepo();
    private OrderRepo() {}

    public static OrderRepo getInstance() {
        return instance;
    }

    public boolean insertOrder(int buyerId, int productId, int companyId, int quantity,
                               double totalPrice, String transactionId, String productName) throws SQLException {

        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(Queries.INSERT_ORDER)) {
            ps.setInt(1, buyerId);
            ps.setInt(2, productId);
            ps.setInt(3, companyId);
            ps.setInt(4, quantity);
            ps.setDouble(5, totalPrice);
            ps.setString(6, transactionId);
            ps.setString(7, productName);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new SQLException(Message.ORDER_INSERT_FAILED);
        }
    }

    public List<Order> getOrdersByBuyerId(int buyerId) throws SQLException {
        List<Order> orders = new ArrayList<>();

        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(Queries.GET_ORDERS_BY_BUYERID)) {
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
            throw new SQLException(Message.ORDER_FETCH_FAILED);
        }
        return orders;
    }
}
