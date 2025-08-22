package Controller;

import Modal.Category;
import Modal.Product;
import Modal.Sub_Category;
import Services.Product_Service;
import java.sql.SQLException;
import java.util.List;

public class ProductController {

    private static final Product_Service productService = new Product_Service();

    private ProductController() {}

    public static List<Product> getSellerProducts(int sellerId) throws SQLException {
        return productService.getSellerProducts(sellerId);
    }

    public static boolean addProduct(Product product) throws SQLException {
        return productService.addProduct(product);
    }

    public static boolean updateProductField(int productId, int sellerId, String field, String newValue) throws SQLException {
        return productService.updateProductField(productId, sellerId, field, newValue);
    }

    public static boolean deleteProduct(int productId, int sellerId) throws SQLException {
        return productService.deleteProduct(productId, sellerId);
    }

    public static List<Category> getAllCategories() throws SQLException {
        return productService.getAllCategories();
    }

    public static List<Sub_Category> getSubCategoriesByCategory(int categoryId, int companyId) throws SQLException {
        return productService.getSubCategoriesByCategory(categoryId, companyId);
    }
}
