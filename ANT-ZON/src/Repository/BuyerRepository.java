package Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Constants.Message;
import Constants.Queries;
import Util.DBConnection;

public class BuyerRepository {

    private static final BuyerRepository INSTANCE = new BuyerRepository();
    private BuyerRepository() {}
    public static BuyerRepository getInstance() {
        return INSTANCE;
    }

    public int getBuyerId(String username, String password) throws SQLException {
        String query = Queries.CHECK_BUYER_QUERY;
        int buyerID = -1;

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, username);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    buyerID = rs.getInt("Buyer_ID");
                }
            }
        } catch (SQLException e) {
            throw new SQLException(Message.ERROR_FETCHING_BUYER);
        }

        return buyerID;
    }
    public boolean registerBuyer(String username, String password) throws SQLException {
        String checkQuery = Queries.CHECK_BUYER_EXISTS;
        String insertQuery = Queries.INSERT_NEW_BUYER;

        try (Connection connection = DBConnection.getInstance().getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(checkQuery)) {
                ps.setString(1, username);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next() && rs.getInt(1) > 0) {
                        return false; 
                    }
                }
            }

            try (PreparedStatement ps = connection.prepareStatement(insertQuery)) {
                ps.setString(1, username);
                ps.setString(2, password);
                return ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            throw new SQLException();
        }
    }
}
