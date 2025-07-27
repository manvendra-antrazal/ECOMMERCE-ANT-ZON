package Repository;

import Constants.Message;
import Constants.Queries;
import Modal.Category;
import Util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Category_Repo {

    public static String getCategoryNameById(int categoryId) throws SQLException {
        String categoryName = null;
        String query = Queries.GET_CATEGORY_NAME_BY_ID;

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, categoryId);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    categoryName = rs.getString("category_name");
                }
            }

        } catch (SQLException e) {
            throw new SQLException(Message.FETCHING_FAILED + e.getMessage(), e);
        }
        return categoryName;
    }

    public List<Category> getAllCategories() throws SQLException {
        List<Category> categories = new ArrayList<>();
        String query = Queries.GET_ALL_CATEGORIES;

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
            throw new SQLException(Message.FETCHING_FAILED + e.getMessage(), e);
        }

        return categories;
    }

    public List<Category> getAllCategoriesByCompany(int companyId) throws SQLException {
        List<Category> categories = new ArrayList<>();
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(Queries.GET_ALL_CATEGORIES_BY_COMPANY)) {

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
            throw new SQLException(Message.ERROR_FETCHING_CATEGORIES + e.getMessage(), e);
        }
        return categories;
    }

    public Integer getCategoryIdByName(String name) throws SQLException {
        String query = Queries.GET_CATEGORY_ID_BY_NAME;
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, name.toLowerCase());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("category_id");
            }
        } catch (SQLException e) {
            throw new SQLException(Message.FETCHING_FAILED + e.getMessage(), e);
        }
        return null; 
    }

    public Integer getSubCategoryIdByName(String name) throws SQLException {
        String query = Queries.GET_SUB_CATEGORY_ID_BY_NAME;
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, name.toLowerCase());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("sub_cat_id");
            }
        } catch (SQLException e) {
            throw new SQLException(Message.FETCHING_FAILED + e.getMessage(), e);
        }
        return null;
    }
}
