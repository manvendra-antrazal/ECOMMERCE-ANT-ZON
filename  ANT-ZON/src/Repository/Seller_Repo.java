package Repository;

import Constants.Message;
import Constants.Queries;
import Util.DBConnection;
import Util.PrintUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Seller_Repo {

    public int getBuyerId(String username, String password) throws SQLException {
        String query = Queries.GET_BUYER_ID;
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("Buyer_ID");
            }
        } catch (SQLException e) {
            PrintUtil.printMessageWithException(Message.ERROR_LOGIN, e);
            throw e; 
        }
        return -1;
    }

    public int getSellerId(String username, String password) throws SQLException {
        String query = Queries.SELLER_GET_ID_QUERY;
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("seller_ID");
            }
        } catch (SQLException e) {
            PrintUtil.printMessageWithException(Message.ERROR_LOGIN, e);
            throw e; 
        }
        return -1;
    }
}
