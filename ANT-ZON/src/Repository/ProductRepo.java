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
import Util.PrintUtil;

public class ProductRepo {
    private static final ProductRepo instance = new ProductRepo();
    private ProductRepo() {}
    public static ProductRepo getInstance() {
        return instance;
    }

    public int getProductStock(int productId, int companyId) throws SQLException {
        String query = Queries.GET_PRODUCT_STOCK;
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, productId);
            stmt.setInt(2, companyId);
            ResultSet rs = stmt.executeQuery();
            return rs.next() ? rs.getInt("quantity") : 0;
        } catch (SQLException e) {
            throw new SQLException(Message.FAILED_TO_FETCH_PRODUCTS_QUANTITY);
        }
    }

    public List<Product> getProductsByCategory(String categoryName) throws SQLException {
        List<Product> products = new ArrayList<>();
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(Queries.GET_PRODUCTS_BY_CATEGORY)) {
            statement.setString(1, categoryName);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    products.add(mapResultSetToProduct(rs));
                }
            }
        } catch (SQLException e) {
               throw new SQLException(Message.FETCHING_FAILED);
        }
        return products;
    }

    public List<Product> getProductsBySellerId(int sellerId) throws SQLException {
        List<Product> products = new ArrayList<>();
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(Queries.GET_PRODUCTS_BY_SELLER_ID)) {

            statement.setInt(1, sellerId);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    products.add(mapResultSetToProduct(rs));
                }
            }

        } catch (SQLException e) {
            throw new SQLException(Message.FAILED_TO_FETCH_PRODUCTS);
        }
        return products;
    }

    public List<Product> getProductsBySubCategory(int subCatId) throws SQLException {
        List<Product> products = new ArrayList<>();
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(Queries.GET_PRODUCTS_BY_SUB_CAT)) {

            statement.setInt(1, subCatId);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    products.add(mapResultSetToProduct(rs));
                }
            }

        } catch (SQLException e) {
            throw new SQLException(Message.FAILED_TO_FETCH_PRODUCTS);
        }
        return products;
    }

    public List<Product> getProductsByCategoryAndSubCategory(int companyId, int subCategoryId) throws SQLException {
        List<Product> products = new ArrayList<>();
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(Queries.GET_PRODUCTS_BY_CAT_AND_SUBCAT)) {

            statement.setInt(1, companyId);
            statement.setInt(2, subCategoryId);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    products.add(mapResultSetToProduct(rs));
                }
            }

        } catch (SQLException e) {
            throw new SQLException(Message.FAILED_TO_FETCH_PRODUCTS);
        }
        return products;
    }

    public boolean addProduct(Product product) throws SQLException {
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(Queries.ADD_PRODUCT_SELLER)) {
            statement.setString(1, product.getProduct_Name());
            statement.setString(2, product.getProduct_Description());
            statement.setDouble(3, product.getProduct_Price());
            statement.setInt(4, product.getProduct_Quantity());
            statement.setInt(5, product.getSeller_ID());
            statement.setInt(6, product.getCompany_ID());
            statement.setInt(7, product.getCategory_ID());
            statement.setInt(8, product.getSub_cat_ID());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new SQLException(Message.FAILED_TO_ADD_PRODUCT);
        }
    }

    public boolean updateProductField(int productId, int sellerId, String field, String newValue) throws SQLException {
        String query = "UPDATE product SET " + field + " = ? WHERE product_Id = ? AND seller_Id = ?";
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            if (field.equals("product_Price") || field.equals("product_Quantity")) {
                statement.setDouble(1, Double.parseDouble(newValue));
            } else {
                statement.setString(1, newValue);
            }
            statement.setInt(2, productId);
            statement.setInt(3, sellerId);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            PrintUtil.printMessageWithException(Message.ERROR_UPDATING_FIELD, e);
            throw new SQLException(Message.ERROR_UPDATING_FIELD);
        }
    }

    public boolean deleteProduct(int productId, int sellerId) throws SQLException {
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(Queries.DELETE_PRODUCT_SELLER)) {

            statement.setInt(1, productId);
            statement.setInt(2, sellerId);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new SQLException(Message.DELETING_FAILED);
        }
    }

    public Product getProductById(int productId) throws SQLException {
        Product product = null;
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(Queries.GET_PRODUCT_BY_ID)) {

            statement.setInt(1, productId);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    product = mapResultSetToProduct(rs);
                }
            }

        } catch (SQLException e) {
            throw new SQLException(Message.FAILED_TO_FETCH_PRODUCTS);
        }
        return product;
    }

    public int getProductQuantity(int productId) throws SQLException {
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(Queries.GET_PRODUCT_QUANTITY)) {

            statement.setInt(1, productId);
            try (ResultSet rs = statement.executeQuery()) {
                return rs.next() ? rs.getInt("product_quantity") : -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public boolean reduceStock(int productId, int quantity) throws SQLException {
        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(Queries.REDUSE_STOCK)) {

            statement.setInt(1, quantity);
            statement.setInt(2, productId);
            statement.setInt(3, quantity);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private Product mapResultSetToProduct(ResultSet rs) throws SQLException {
        return new Product(
                rs.getInt("product_id"),
                rs.getString("product_name"),
                rs.getString("product_description"),
                rs.getDouble("product_price"),
                rs.getInt("product_quantity"),
                rs.getInt("company_id"),
                rs.getInt("seller_id"),
                rs.getInt("category_id"),
                rs.getInt("sub_cat_id"),
                rs.getInt("likes")
        );
    }
}
