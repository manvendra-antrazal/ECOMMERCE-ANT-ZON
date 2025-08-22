package Repository;

import Constants.Message;
import Constants.Queries;
import Modal.Sub_Category;
import Util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubCategory_Repo {
    public List<Sub_Category> getSubCategoriesByCategoryId(int categoryId, int companyId) {
        List<Sub_Category> subCategories = new ArrayList<>();
        String query = Queries.GET_SUB_CAT_BY_CATID;
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, categoryId);
            statement.setInt(2, companyId);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Sub_Category subCat = new Sub_Category(
                        rs.getInt("sub_cat_ID"),
                        rs.getString("sub_cat_Name"),
                        rs.getInt("category_Id"),
                        rs.getInt("company_ID")
                    );
                    subCategories.add(subCat);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(Message.FETCHING_FAILED);
        }

        return subCategories;
    }

    public Sub_Category getSubCategoryById(int subCatId) {
        String query = Queries.GET_SUBCAT_BY_ID;

        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, subCatId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Sub_Category(
                        rs.getInt("sub_cat_ID"),
                        rs.getString("sub_cat_Name"),
                        rs.getInt("category_Id"),
                        rs.getInt("company_ID")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(Message.FETCHING_FAILED);
        }

        return null;
    }
}
