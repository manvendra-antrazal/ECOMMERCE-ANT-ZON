package Repository;

import Constants.Message;
import Constants.Queries;
import Util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Buyer_Repo {

    // Method it checks, if buyer with the given username and password exists
    public int getBuyerId(String username, String password) throws SQLException {
        String query = Queries.CHECK_BUYER_QUERY;
        boolean isBuyerFound = false;
        int buyerID = -1;

        try {
            // db connection
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultset = preparedStatement.executeQuery();

            if (resultset.next()) {
                isBuyerFound = true;
                buyerID = resultset.getInt("Buyer_ID");
                String buyerUserName = resultset.getString("Buyer_UserName");
                String buyerPassword = resultset.getString("Buyer_Psd");
            }

        } catch (SQLException e) {
            System.out.println(Message.ERROR_FETCHING_BUYER + e.getMessage());
        }
        return buyerID;
    }
}
