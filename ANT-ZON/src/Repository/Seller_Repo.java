package Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Constants.Queries;
import Util.DBConnection;

public class Seller_Repo {

    public int getBuyerId(String username, String password) throws SQLException {
        String query = Queries.GET_BUYER_ID;
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("Buyer_ID");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    public int getSellerId(String username, String password) throws SQLException {
        String query = Queries.SELLER_GET_ID_QUERY;
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, username);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("seller_ID");
                }
            }

        } catch (SQLException e) {
             e.printStackTrace();
        }
        return -1;
    }

}
