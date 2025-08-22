package Services;

import Constants.Message;
import java.sql.SQLException;
import java.util.List;

import Modal.Category;
import Modal.Product;
import Modal.Sub_Category;
import Repository.CategoryRepository;
import Repository.ProductRepo;
import Repository.SubCategory_Repo;

public class Product_Service {

    private final ProductRepo productRepo = ProductRepo.getInstance();
    private final CategoryRepository categoryRepo = CategoryRepository.getInstance();
    private final SubCategory_Repo subCategoryRepo = new SubCategory_Repo();
    public List<Product> getSellerProducts(int sellerId) throws SQLException {
        if (sellerId <= 0) throw new IllegalArgumentException();
        return productRepo.getProductsBySellerId(sellerId);
    }

    public boolean addProduct(Product product) throws SQLException {
        validateProductForCreate(product);
        return productRepo.addProduct(product);
    }

    public boolean updateProductField(int productId, int sellerId, String field, String newValue) throws SQLException {
        if (productId <= 0) throw new IllegalArgumentException();
        if (sellerId <= 0) throw new IllegalArgumentException();
        if (field == null || field.isBlank()) throw new IllegalArgumentException();
        if (newValue == null) throw new IllegalArgumentException();
        return productRepo.updateProductField(productId, sellerId, field, newValue);
    }

    public boolean deleteProduct(int productId, int sellerId) throws SQLException {
        if (productId <= 0) throw new IllegalArgumentException();
        if (sellerId <= 0) throw new IllegalArgumentException();
        return productRepo.deleteProduct(productId, sellerId);
    }

    public List<Category> getAllCategories() throws SQLException {
        return categoryRepo.getAllCategories();
    }

    public List<Sub_Category> getSubCategoriesByCategory(int categoryId, int companyId) throws SQLException {
        if (categoryId <= 0) throw new IllegalArgumentException();
        if (companyId <= 0) throw new IllegalArgumentException();
        return subCategoryRepo.getSubCategoriesByCategoryId(categoryId, companyId);
    }

    public List<Product> getProductsByCompanyAndSubCategory(int companyId, int subCategoryId) throws SQLException {
        if (companyId <= 0) throw new IllegalArgumentException();
        if (subCategoryId <= 0) throw new IllegalArgumentException();
        return productRepo.getProductsByCategoryAndSubCategory(companyId, subCategoryId);
    }

    public List<Product> getProductsByCategoryName(String categoryName) throws SQLException {
        if (categoryName == null || categoryName.isBlank()) {
            throw new IllegalArgumentException();
        }
        return productRepo.getProductsByCategory(categoryName);
    }

    private void validateProductForCreate(Product p) {
        if (p == null) throw new IllegalArgumentException();
        if (p.getProduct_Name() == null || p.getProduct_Name().trim().isEmpty()) {
            throw new IllegalArgumentException(Message.PRODUCT_REQUIRED);
        }
        if (p.getProduct_Price() < 0) {
            throw new IllegalArgumentException(Message.PRODUCT_PRICE_REQUIRED);
        }
        if (p.getProduct_Quantity() < 0) {
            throw new IllegalArgumentException(Message.PRODUCT_QUANTITY_REQUIRED);
        }
        if (p.getCompany_ID() <= 0) {
            throw new IllegalArgumentException(Message.COMPANY_NAME_REUIQURED);
        }
        if (p.getSeller_ID() <= 0) {
            throw new IllegalArgumentException(Message.SELLER_ID_REQUIRED);
        }
    }
}
