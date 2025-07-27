package Repository;
import Constants.Message;
import Constants.Queries;
import Modal.Product;
import Util.DBConnection;
import Util.PrintUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Product_Repo {

    public int getProductStock(int productId, int companyId) throws SQLException {
        String query = Queries.GET_PRODUCT_STOCK;
        try (Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, productId);
            stmt.setInt(2, companyId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("quantity");
            }
            return 0;
        }
    }



    public List<Product> getProductsByCategory(String categoryName) throws SQLException {
    List<Product> products = new ArrayList<>();
    String query = Queries.GET_PRODUCTS_BY_CATEGORY;
    try (Connection connection = DBConnection.getInstance().getConnection();
         PreparedStatement statement = connection.prepareStatement(query)) {

        statement.setString(1, categoryName);
        ResultSet rs = statement.executeQuery();

        // Iterate over result set rows
        while (rs.next()) {
            Product product = new Product(
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
            products.add(product);
        }

    } catch (SQLException e) {
        PrintUtil.printMessageWithException(Message.FETCHING_FAILED, e);
    }
    return products;
}

    

    // view all products 
    public List<Product> getProductsBySellerId(int sellerId) throws SQLException {
        List<Product> products = new ArrayList<>();
        String query = Queries.GET_PRODUCTS_BY_SELLER_ID;

        try (
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setInt(1, sellerId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Product product = new Product(
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
                products.add(product);
            }

        } catch (SQLException e) {
                       PrintUtil.printMessageWithException(Message.FETCHING_FAILED, e);
        }
        return products;
    }

    // Add Product 
    public boolean addProduct(Product product) throws SQLException {
        String query = Queries.ADD_PRODUCT_SELLER;

        try (Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, product.getProduct_Name());
            statement.setString(2, product.getProduct_Description());
            statement.setDouble(3, product.getProduct_Price());
            statement.setInt(4, product.getProduct_Quantity());
            statement.setInt(5, product.getSeller_ID());
            statement.setInt(6, product.getCompany_ID());
            statement.setInt(7, product.getCategory_ID());
            statement.setInt(8, product.getSub_cat_ID());

            return statement.executeUpdate() > 0;
        }
    }



   // update product 
   public boolean updateProductField(int productId, int sellerId, String field, String newValue) throws SQLException {
    String query = "UPDATE product SET " + field + " = ? WHERE product_Id = ? AND seller_Id = ?";
    // String query = Queries.UPDATE_PRODUCT_SELLER;
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
        return false;
    }
}


    // delete product 
    public boolean deleteProduct(int productId, int sellerId) throws SQLException {
    String query = Queries.DELETE_PRODUCT_SELLER;

    try (Connection connection = DBConnection.getInstance().getConnection();
         PreparedStatement statement = connection.prepareStatement(query)) {

        statement.setInt(1, productId);
        statement.setInt(2, sellerId);

        int rowsAffected = statement.executeUpdate();
        return rowsAffected > 0;

    } catch (SQLException e) {
       PrintUtil.printMessageWithException(Message.DELETING_FAILED, e);
        return false;
        }
    }

    
    public List<Product> getProductsBySubCategory(int subCatId) throws SQLException {
    List<Product> products = new ArrayList<>();
    String query = Queries.GET_PRODUCTS_BY_SUB_CAT;

    try (Connection connection = DBConnection.getInstance().getConnection();
         PreparedStatement statement = connection.prepareStatement(query)) {

        statement.setInt(1, subCatId);
        ResultSet rs = statement.executeQuery();

        while (rs.next()) {  // <--- iterate over all rows
            Product product = new Product(
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
            products.add(product);
        }

    } catch (SQLException e) {
        PrintUtil.printMessageWithException(Message.FAILED_TO_FETCH_PRODUCTS, e);
    }

    return products;
}



    // Buyer side 
    public List<Product> getProductsByCategoryAndSubCategory(int companyId , int subCategoryId) throws SQLException {
    List<Product> productList = new ArrayList<>();
    String query = Queries.GET_PRODUCTS_BY_CAT_AND_SUBCAT;

    try (Connection connection = DBConnection.getInstance().getConnection();
         PreparedStatement statement = connection.prepareStatement(query)) {
         
        statement.setInt(1, companyId);
        statement.setInt(2, subCategoryId);
        ResultSet rs = statement.executeQuery();

       while (rs.next()) {  
            Product product = new Product(
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
           productList.add(product);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return productList;
}



// Get the available quantity of a product by productId
public int getProductQuantity(int productId) throws SQLException{
    String query = Queries.GET_PRODUCT_QUANTITY;
    try (Connection connection = DBConnection.getInstance().getConnection();
         PreparedStatement statement = connection.prepareStatement(query)) {

        statement.setInt(1, productId);
        ResultSet rs = statement.executeQuery();

        if (rs.next()) {
            return rs.getInt("product_quantity");
        }

    } catch (SQLException e) {
        PrintUtil.printMessageWithException(Message.FAILED_TO_FETCH_PRODUCTS_QUANTITY, e);
    }
    return -1; 
}

// Reduce the stock of a product by quantity
public boolean reduceStock(int productId, int quantity) throws SQLException {
    String query = Queries.REDUSE_STOCK;
    try (Connection connection = DBConnection.getInstance().getConnection();
         PreparedStatement statement = connection.prepareStatement(query)) {

        statement.setInt(1, quantity);
        statement.setInt(2, productId);
        statement.setInt(3, quantity);  

        int rowsAffected = statement.executeUpdate();
        return rowsAffected > 0;

    } 
}

public Product getProductById(int productId) throws SQLException {
    Product product = null;
    String query = Queries.GET_PRODUCT_BY_ID;

    try (Connection connection = DBConnection.getInstance().getConnection();
         PreparedStatement statement = connection.prepareStatement(query)) {

        statement.setInt(1, productId);
        ResultSet rs = statement.executeQuery();

        if (rs.next()) {
            product = new Product();
            product.setProduct_Id(rs.getInt("product_id"));
            product.setProduct_Name(rs.getString("product_name"));
            product.setProduct_Description(rs.getString("product_description"));
            product.setProduct_Price(rs.getDouble("product_price"));
            product.setProduct_Quantity(rs.getInt("product_quantity"));
            product.setCompany_ID(rs.getInt("company_id"));
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return product;
}

    
}
