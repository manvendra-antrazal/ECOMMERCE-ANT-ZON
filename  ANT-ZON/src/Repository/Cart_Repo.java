package Repository;

import Constants.Queries;
import Util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Cart_Repo {

    public void insertCartItem(int productId, int companyId, int buyerId, int quantity) {

        try (Connection connection = DBConnection.getInstance().getConnection()) {
            String query = Queries.PRODUCT_ADD_TO_CART;
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, productId);
            statement.setInt(2, companyId);
            statement.setInt(3, buyerId);
            statement.setInt(4, quantity);
            statement.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
