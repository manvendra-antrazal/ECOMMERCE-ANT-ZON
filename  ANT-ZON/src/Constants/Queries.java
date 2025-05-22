package Constants;

public class Queries {

    // ------------------ Seller Queries ------------------
    public static final String CHECK_SELLER_QUERY =
        "SELECT Buyer_ID, Buyer_UserName, Buyer_Psd FROM Buyer WHERE Buyer_UserName = ? AND Buyer_Psd = ?";

    public static final String SELLER_GET_ID_QUERY = "SELECT * FROM seller WHERE seller_username = ? AND seller_password = ?";    

    public static final String INSERT_SELLER_QUERY =
        "INSERT INTO seller (seller_Name, seller_username, seller_password, seller_Mobile_NO, company_ID, role) " +
        "VALUES (?, ?, ?, ?, ?, ?)";
    
    // Add product
    // public static final String ADD_PRODUCT_QUERY = "INSERT INTO product (product_Name, product_Info, product_Price, product_Quantity) VALUES (?, ?, ?, ?)";

    // Update Product
    
    // public static final String UPDATE_PRODUCT_QUERY = "UPDATE product SET product_Name = ?, product_Info = ? WHERE product_Id = ?";

    // Update product quantity
    // public static final String UPDATE_PRODUCT_QUANTITY_QUERY= "UPDATE product SET product_Quantity = ? WHERE product_Id = ?";

    // Delete Product 
    // public static final String DELETE_PRODUCT_QUERY = "DELETE FROM product WHERE product_Id = ?";
    
    
    // --------------------------------------------------------------------------------------------

    // Get all products queries -> product repo 
    public static final String GET_ALL_PRODUCTS_QUERY = "SELECT product_Id, product_Name, product_Info, product_Price, product_Quantity FROM product";

    // getProductsByCategory -> product repo 
    public static final String GET_PRODUCTS_BY_CATEGORY = "SELECT * FROM product WHERE category_Name = ?";

    // getProductsBySellerId -> product repo
    public static final String GET_PRODUCTS_BY_SELLER_ID = "SELECT * FROM product WHERE seller_Id = ?";

    // add product -> product repo 
    public static final String ADD_PRODUCT_SELLER = "INSERT INTO product (product_name, product_description, product_price, product_quantity, company_id, seller_id, category_id, sub_cat_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    // update product field -> product repo 
    // public static final String UPDATE_PRODUCT_SELLER = "UPDATE product SET " + field + " = ? WHERE product_Id = ? AND seller_Id = ?";

    // 
    public static final String DELETE_PRODUCT_SELLER = "DELETE FROM product WHERE product_Id = ? AND seller_Id = ?";


    // ------------------ Buyer Queries ------------------
    public static final String CHECK_BUYER_QUERY =
        "SELECT * FROM buyer WHERE Buyer_UserName = ? AND Buyer_Psd = ?";

    public static final String INSERT_BUYER_QUERY =
        "INSERT INTO buyer (Buyer_UserName, Buyer_Email, Buyer_Psd, Buyer_Role, city, state, county, local_Address, Buyer_Mob_No, company_ID) " +
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
    // getProductsByCategoryAndSubCategory -> product repo 
    public static final String GET_PRODUCTS_BY_CAT_AND_SUBCAT = "SELECT * FROM Product WHERE Company_ID = ? AND Sub_cat_ID = ?";



    // Add to cart 
    public static final String PRODUCT_ADD_TO_CART = "INSERT INTO cart (product_id, company_id, buyer_id, quantity) VALUES (?, ?, ?, ?)";


    // wishlist queries 
    public static final String GET_WISHLIST_BY_BUYER_ID = 
    "SELECT * FROM product WHERE product_id IN (SELECT product_id FROM wishlist WHERE buyer_id = ?)";

    public static final String REMOVE_FROM_WISHLIST = 
        "DELETE FROM wishlist WHERE buyer_id = ? AND product_id = ?";

    // cart repo 
    public static final String GET_CART_ITEM_BY_BUYERID =
    "SELECT p.*, c.quantity as cart_quantity " +
                   "FROM product p JOIN cart c ON p.product_id = c.product_id " +
                   "WHERE c.buyer_id = ?";

    // sub category 
    public static final String GET_SUB_CAT_BY_CATID =
    "SELECT * FROM sub_category WHERE category_Id = ? AND company_ID = ?";

    public static final String GET_SUBCAT_BY_ID = "SELECT * FROM sub_category WHERE sub_cat_ID = ?";
}


