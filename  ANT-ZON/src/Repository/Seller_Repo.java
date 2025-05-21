package Repository;

import Constants.Queries;
import Util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Seller_Repo {

    public int getBuyerId(String username, String password) {
    String query = "SELECT Buyer_ID FROM Buyer WHERE Buyer_UserName = ? AND Buyer_Psd = ?";
    try (Connection conn = DBConnection.getInstance().getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {

        stmt.setString(1, username);
        stmt.setString(2, password);

        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("Buyer_ID");
        }
    } catch (SQLException e) {
        System.out.println("Login error: " + e.getMessage());
    }
    return -1;
}

    public int getSellerId(String username, String password) {
    String query = Queries.SELLER_GET_ID_QUERY;
    try (Connection connection = DBConnection.getInstance().getConnection();
         PreparedStatement ps = connection.prepareStatement(query)) {

        ps.setString(1, username);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getInt("seller_ID"); // assumes seller_ID exists in the seller table
        }
    } catch (SQLException e) {
        System.out.println("Login Error: " + e.getMessage());
    }
    return -1;
    }
}
