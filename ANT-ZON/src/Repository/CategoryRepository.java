package Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Constants.Message;
import Constants.Queries;
import Modal.Category;
import Util.DBConnection;

public class CategoryRepository {

    private static final CategoryRepository instance = new CategoryRepository();

    private CategoryRepository() {}

    public static CategoryRepository getInstance() {
        return instance;
    }

    public String getCategoryNameById(int categoryId) throws SQLException {
        String categoryName = null;
        String query = Queries.GET_CATEGORY_NAME_BY_ID;
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, categoryId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    categoryName = rs.getString("category_name");
                }
            }
        } catch (SQLException e) {
            throw new SQLException(Message.FETCHING_FAILED + ": " + e.getMessage(), e);
        }

        return categoryName;
    }

    public List<Category> getAllCategories() throws SQLException {
        List<Category> categories = new ArrayList<>();
        String query = Queries.GET_ALL_CATEGORIES;

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Category category = new Category(
                        rs.getInt("category_Id"),
                        rs.getString("category_Name"),
                        rs.getInt("company_ID")
                );
                categories.add(category);
            }
        } catch (SQLException e) {
            throw new SQLException(Message.FETCHING_FAILED + ": " + e.getMessage(), e);
        }

        return categories;
    }

    public List<Category> getAllCategoriesByCompany(int companyId) throws SQLException {
        List<Category> categories = new ArrayList<>();
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(Queries.GET_ALL_CATEGORIES_BY_COMPANY)) {
            ps.setInt(1, companyId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Category c = new Category(
                            rs.getInt("category_ID"),
                            rs.getString("category_Name"),
                            rs.getInt("company_ID")
                    );
                    categories.add(c);
                }
            }
        } catch (SQLException e) {
            throw new SQLException(Message.ERROR_FETCHING_CATEGORIES + ": " + e.getMessage(), e);
        }
        return categories;
    }
    public Integer getCategoryIdByName(String name) throws SQLException {
        String query = Queries.GET_CATEGORY_ID_BY_NAME;
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, name.toLowerCase());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("category_id");
                }
            }
        } catch (SQLException e) {
            throw new SQLException(Message.FETCHING_FAILED + ": " + e.getMessage(), e);
        }
        return null;
    }
    public Integer getSubCategoryIdByName(String name) throws SQLException {
        String query = Queries.GET_SUB_CATEGORY_ID_BY_NAME;
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, name.toLowerCase());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("sub_cat_id");
                }
            }
        } catch (SQLException e) {
            throw new SQLException(Message.FETCHING_FAILED + ": " + e.getMessage(), e);
        }
        return null;
    }
}
