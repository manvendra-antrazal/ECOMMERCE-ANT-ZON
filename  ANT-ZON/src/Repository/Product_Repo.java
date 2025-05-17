package Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Constants.Message;
import Constants.Queries;
import Modal.Product;
import Util.DBConnection;

public class Product_Repo {
    

    public List<Product> get_All_Products(){

        List<Product> products = new ArrayList<>();
        String query = Queries.GET_ALL_PRODUCTS;

        try{
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet resultSet = stmt.executeQuery();

            while(resultSet.next()){
                Product product = new Product(
                    resultSet.getInt("product_Id"),
                    resultSet.getString("product_Name"),
                    resultSet.getString("product_Info"),
                    resultSet.getDouble("product_Price"),       
                    resultSet.getInt("product_Quantity")        
                );
                products.add(product);
            }

        }catch(SQLException e){
            System.out.println(Message.FETCHING_FAILED + e.getMessage());
        }

        return products;
    }   
}
