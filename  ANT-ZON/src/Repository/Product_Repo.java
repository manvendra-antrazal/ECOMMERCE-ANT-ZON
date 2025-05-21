package Repository;

import Constants.Message;
import Constants.Queries;
import Modal.Product;
import Util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Product_Repo {

    public List<Product> getProductsByCategory(String categoryName) {
    List<Product> products = new ArrayList<>();
    String query = Queries.GET_PRODUCTS_BY_CATEGORY;
    try (Connection connection = DBConnection.getInstance().getConnection();
         PreparedStatement statement = connection.prepareStatement(query)) {

        statement.setString(1, categoryName);
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
            rs.getInt("sub_category_id")
            );
            products.add(product);
        }

    } catch (SQLException e) {
        System.out.println(Message.FETCHING_FAILED + e.getMessage());
    }

    return products;
}
    

    // view all products 
    public List<Product> getProductsBySellerId(int sellerId) {
    List<Product> products = new ArrayList<>();
    String query = Queries.GET_PRODUCTS_BY_SELLER_ID;

    try {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
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
            rs.getInt("sub_cat_id")
            );
            products.add(product);
        }

    } catch (SQLException e) {
        System.out.println(Message.FETCHING_FAILED);
        e.printStackTrace();
    }
    return products;
    }

    // Add Product 
    public boolean addProduct(Product product) {
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

    } catch (SQLException e) {
        System.out.println(Message.ADD_PRODUCT_ERROR);
        e.printStackTrace();
    }
    return false;
}



   // update product 
   public boolean updateProductField(int productId, int sellerId, String field, String newValue) {
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
        System.out.println(Message.ERROR_UPDATING_FIELD );
        e.printStackTrace();
        return false;
    }
}


    // delete product 
    public boolean deleteProduct(int productId, int sellerId) {
    String query = Queries.DELETE_PRODUCT_SELLER;

    try (Connection connection = DBConnection.getInstance().getConnection();
         PreparedStatement statement = connection.prepareStatement(query)) {

        statement.setInt(1, productId);
        statement.setInt(2, sellerId);

        int rowsAffected = statement.executeUpdate();
        return rowsAffected > 0;

    } catch (SQLException e) {
        System.out.println(Message.DELETING_FAILED);
        e.printStackTrace();
        return false;
        }
    }

    // stats 

    // Example: get total revenue of all seller's products
public double getTotalRevenueBySeller(int sellerId) {
    double revenue = 0;
    String query = "SELECT SUM(product_Price * quantity_sold) AS total_revenue FROM sales WHERE seller_ID = ?";

    try (Connection connection = DBConnection.getInstance().getConnection();
         PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setInt(1, sellerId);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            revenue = rs.getDouble("total_revenue");
        }
    } catch (SQLException e) {
        System.out.println("Failed to fetch revenue: " + e.getMessage());
    }
    return revenue;
}

// Example: get most liked product for seller (assuming likes column in product table)
public Product getMostLikedProductBySeller(int sellerId) {
    String query = "SELECT * FROM product WHERE seller_ID = ? ORDER BY likes DESC LIMIT 1";
    try (Connection connection = DBConnection.getInstance().getConnection();
         PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setInt(1, sellerId);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            return new Product(
                rs.getInt("product_id"),
            rs.getString("product_name"),
            rs.getString("product_description"),
            rs.getDouble("product_price"),
            rs.getInt("product_quantity"),
            rs.getInt("company_id"),
            rs.getInt("seller_id"),
            rs.getInt("category_id"),       
            rs.getInt("sub_category_id")
            );
        }
    } catch (SQLException e) {
        System.out.println("Failed to fetch most liked product: " + e.getMessage());
    }
    return null;
}

// Example: get best selling product (based on quantity sold)
public Product getBestSellingProductBySeller(int sellerId) {
    String query = "SELECT p.*, s.quantity_sold FROM product p JOIN sales s ON p.product_Id = s.product_Id WHERE p.seller_ID = ? ORDER BY s.quantity_sold DESC LIMIT 1";

    try (Connection connection = DBConnection.getInstance().getConnection();
         PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setInt(1, sellerId);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            return new Product(
            rs.getInt("product_id"),
            rs.getString("product_name"),
            rs.getString("product_description"),
            rs.getDouble("product_price"),
            rs.getInt("product_quantity"),
            rs.getInt("company_id"),
            rs.getInt("seller_id"),
            rs.getInt("category_id"),       
            rs.getInt("sub_category_id")
            );
        }
    } catch (SQLException e) {
        System.out.println("Failed to fetch best selling product: " + e.getMessage());
    }
    return null;
}

public List<Product> getProductsBySubCategory(int subCatId) {
    List<Product> products = new ArrayList<>();
    String query = "SELECT * FROM product WHERE sub_cat_ID = ?";

    try (Connection connection = DBConnection.getInstance().getConnection();
         PreparedStatement statement = connection.prepareStatement(query)) {

        statement.setInt(1, subCatId);
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
            rs.getInt("sub_category_id")
        );
            products.add(product);
        }

    } catch (SQLException e) {
        System.out.println("Failed to fetch products: " + e.getMessage());
    }

    return products;
}

    // Buyer side 
    public List<Product> getProductsByCategoryAndSubCategory(int companyId , int subCategoryId) {
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
            rs.getInt("sub_cat_id")
        );

            productList.add(product);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return productList;
}

    
}
