package Repository;

import Constants.Queries;
import Util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Buyer_Repo {

    // Method to check if a buyer with the given username and password exists
    public int getBuyerId(String username, String password) {
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
                System.out.println("Buyer Found: " + buyerUserName);
            }

        } catch (SQLException e) {
            System.out.println("Error fetching buyer details: " + e.getMessage());
        }
        return buyerID;
    }
}
