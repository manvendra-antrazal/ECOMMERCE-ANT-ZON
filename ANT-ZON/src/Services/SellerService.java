package Services;

import Constants.Message;
import java.util.List;

import Modal.Product;
import Modal.Seller;
import Repository.ProductRepo;
import Repository.Seller_Repo;


public class SellerService {

    private static final SellerService INSTANCE = new SellerService();
    private static final Seller_Repo sellerRepository = new Seller_Repo(); 
    private static final ProductRepo productRepo = ProductRepo.getInstance();

    private SellerService() {}

    public static SellerService getInstance() {
        return INSTANCE;
    }

    public Integer loginSeller(String username, String password, int companyId) {
        validateString(username, "Username");
        validateString(password, "Password");
        if (companyId <= 0) throw new IllegalArgumentException();

        try {
            int sellerId = sellerRepository.getSellerId(username, password);
            return sellerId;
        } catch (Exception e) {
            throw new RuntimeException(Message.LOGIN_FAILED);
        }
    }

    public boolean registerSeller(String username, String password, int companyId) {
        validateString(username, "Username");
        validateString(password, "Password");
        if (companyId <= 0) throw new IllegalArgumentException();

        try {
            throw new UnsupportedOperationException();
        } catch (UnsupportedOperationException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(Message.FAILED_TO_REGISTER);
        }
    }

    public boolean addProduct(Product product, int sellerId) {
        validateSellerId(sellerId);
        validateProductForCreate(product, sellerId);

        try {
            product.setSeller_ID(sellerId);
            return productRepo.addProduct(product);
        } catch (Exception e) {
            throw new RuntimeException(Message.FAILED_TO_ADD_PRODUCT);
        }
    }

    public boolean updateProduct(Product product, int sellerId) {
        validateSellerId(sellerId);
        if (product == null || product.getProduct_Id() <= 0) {
            throw new IllegalArgumentException();
        }

        try {
            boolean updated = false;

            if (product.getProduct_Name() != null && !product.getProduct_Name().trim().isEmpty()) {
                updated = productRepo.updateProductField(
                product.getProduct_Id(), sellerId, "product_Name", product.getProduct_Name());
            }
            if (product.getProduct_Description() != null) {
                updated = productRepo.updateProductField(
                product.getProduct_Id(), sellerId, "product_Description", product.getProduct_Description());
            }
            if (product.getProduct_Price() >= 0) {
                updated = productRepo.updateProductField(
                product.getProduct_Id(), sellerId, "product_Price", Double.toString(product.getProduct_Price()));
            }
            if (product.getProduct_Quantity() >= 0) {
                updated = productRepo.updateProductField(
                product.getProduct_Id(), sellerId, "product_Quantity", Integer.toString(product.getProduct_Quantity()));
            }
            if (product.getCategory_ID() > 0) {
                updated = productRepo.updateProductField(
                product.getProduct_Id(), sellerId, "category_ID", Integer.toString(product.getCategory_ID()));
            }
            if (product.getSub_cat_ID() > 0) {
                updated = productRepo.updateProductField(
                product.getProduct_Id(), sellerId, "sub_cat_ID", Integer.toString(product.getSub_cat_ID()));
            }

            return updated;
        } catch (Exception e) {
            throw new RuntimeException(Message.UPDATING_FAILED);
        }
    }

    public boolean deleteProduct(int productId, int sellerId) {
        validateSellerId(sellerId);
        if (productId <= 0) throw new IllegalArgumentException("Invalid productId");

        try {
            return productRepo.deleteProduct(productId, sellerId);
        } catch (Exception e) {
            throw new RuntimeException(Message.DELETING_FAILED);
        }
    }

    public List<Product> getProductsBySeller(int sellerId) {
        validateSellerId(sellerId);
        try {
            return productRepo.getProductsBySellerId(sellerId);
        } catch (Exception e) {
            throw new RuntimeException(Message.ERROR_VIEW_SELLER);
        }
    }

    public double getTotalSales(int sellerId) {
        validateSellerId(sellerId);
        throw new UnsupportedOperationException();
    }

    public Seller getSellerProfile(int sellerId) {
        validateSellerId(sellerId);
        throw new UnsupportedOperationException(Message.ERROR_GET_TOTAL);
    }

    private void validateString(String s, String field) {
        if (s == null || s.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }
    }

    private void validateSellerId(int sellerId) {
        if (sellerId <= 0) {
            throw new IllegalArgumentException();
        }
    }

    private void validateProductForCreate(Product p, int sellerId) {
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
        if (sellerId <= 0) {
            throw new IllegalArgumentException(Message.SELLER_ID_REQUIRED);
        }
    }
}
