package Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Constants.Message;
import Modal.Category;
import Modal.Company;
// import Services.Category_Service;
import Util.DBConnection;

public class Category_Repo {

    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        String query = "SELECT * FROM category";

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                Category category = new Category(
                    rs.getInt("category_Id"),
                    rs.getString("category_Name"),
                    rs.getInt("company_ID"));
                categories.add(category);
            }

        } catch (SQLException e) {
            System.out.println(Message.FETCHING_FAILED + e.getMessage());
        }

        return categories;
    }

    public List<Category> getAllCategoriesByCompany(int companyId) {
    List<Category> categories = new ArrayList<>();
    try (Connection connection = DBConnection.getInstance().getConnection();
         PreparedStatement statement = connection.prepareStatement("SELECT * FROM category WHERE company_ID = ?")) {

        statement.setInt(1, companyId);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Category c = new Category(
                resultSet.getInt("category_ID"),
                resultSet.getString("category_Name"),
                resultSet.getInt("company_ID")
            );  
            categories.add(c);
        }

    } catch (SQLException e) {
        System.out.println("Error fetching categories: " + e.getMessage());
    }
    return categories;
    }

}
