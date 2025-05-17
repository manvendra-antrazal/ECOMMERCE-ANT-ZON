package Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Constants.Queries;
import Util.DBConnection;

public class Buyer_Repo {

    // Method to check if a buyer with the given username and password exists
    public boolean checkBuyer(String username, String password) {
        String query = Queries.CHECK_BUYER;
        boolean isBuyerFound = false;

        try {
            // db connection
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultset = preparedStatement.executeQuery();

            if (resultset.next()) {
                isBuyerFound = true;
                int buyerID = resultset.getInt("Buyer_ID");
                String buyerUserName = resultset.getString("Buyer_UserName");
                String buyerPassword = resultset.getString("Buyer_Psd");
                System.out.println("Buyer Found: " + buyerUserName);
            }

        } catch (SQLException e) {
            System.out.println("Error fetching buyer details: " + e.getMessage());
        }
        return isBuyerFound;
    }
}
