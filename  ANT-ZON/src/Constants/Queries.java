package Constants;

public class Queries {

    // Company 
    public static final String GET_ALL_EXISTING_COMPANY  = "Select * from company";

    // ------------------ Seller Queries ------------------
    public static final String CHECK_SELLER_QUERY =
        "SELECT Buyer_ID, Buyer_UserName, Buyer_Psd FROM Buyer WHERE Buyer_UserName = ? AND Buyer_Psd = ?";

    public static final String SELLER_GET_ID_QUERY = "SELECT * FROM seller WHERE seller_username = ? AND seller_password = ?";    

    public static final String INSERT_SELLER_QUERY =
        "INSERT INTO seller (seller_Name, seller_username, seller_password, seller_Mobile_NO, company_ID, role) " +
        "VALUES (?, ?, ?, ?, ?, ?)";

    public static final String GET_BUYER_ID = "SELECT Buyer_ID FROM Buyer WHERE Buyer_UserName = ? AND Buyer_Psd = ?";
    
    
    
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

    public static final String ADD_TO_WISHLIST = "INSERT INTO wishlist (company_id, buyer_id, product_id, added_on) VALUES (?, ?, ?, NOW())";

    public static final String IS_PRODUCT_IN_WISHLIST = "SELECT * FROM wishlist WHERE buyer_id = ? AND product_id = ? AND company_id = ?";


    // cart repo 
    public static final String GET_CART_ITEM_BY_BUYERID =
    "SELECT p.*, c.quantity as cart_quantity " +
                   "FROM product p JOIN cart c ON p.product_id = c.product_id " +
                   "WHERE c.buyer_id = ?";

    public static final String IS_PRODUCT_IN_CART = "SELECT 1 FROM cart WHERE product_id = ? AND company_id = ? AND buyer_id = ?";    
    public static final String UPDATE_CART_QUANTITY = "UPDATE cart SET quantity = ? WHERE buyer_id = ? AND product_id = ?";
    public static final String REMOVE_FROM_CART = "DELETE FROM cart WHERE buyer_id = ? AND product_id = ?";
    public static final String GET_CATEGORY_NAME_BY_ID = "SELECT category_name FROM category WHERE category_id = ?";


    // category 
    public static final String GET_ALL_CATEGORIES = "SELECT * FROM category";
    public static final String GET_ALL_CATEGORIES_BY_COMPANY = "SELECT * FROM category WHERE company_ID = ?";
    public static final String GET_CATEGORY_ID_BY_NAME = "SELECT category_id FROM category WHERE LOWER(category_name) = ?";
    public static final String GET_SUB_CATEGORY_ID_BY_NAME = "SELECT sub_cat_id FROM sub_category WHERE LOWER(sub_cat_name) = ?";

    // sub category 
    public static final String GET_SUB_CAT_BY_CATID =
    "SELECT * FROM sub_category WHERE category_Id = ? AND company_ID = ?";

    public static final String GET_SUBCAT_BY_ID = "SELECT * FROM sub_category WHERE sub_cat_ID = ?";

    public static final String GET_ORDER_BY_BUYERID =
    """
            SELECT o.order_id, o.transaction_id, o.quantity, o.total_price, o.order_date,
                   p.product_name
            FROM `order` o
            JOIN product p ON o.product_id = p.product_id
            WHERE o.buyer_id = ?
            ORDER BY o.order_date DESC
            """;



    // order 
       public static final String GET_ORDERS_BY_BUYERID = 
       "SELECT order_id, product_name, quantity, total_price, order_date " +
                   "FROM `order` WHERE buyer_id = ? ORDER BY order_date DESC";

        public static final String INSERT_ORDER = "INSERT INTO `order` (buyer_id, product_id, company_id, quantity, total_price, transaction_id, product_name) VALUES (?, ?, ?, ?, ?, ?, ?)";

    // like increment 
        public static final String LIKE_INCREMENT = "UPDATE product SET likes = likes + 1 WHERE product_id = ?";

    // Admin 
        public static final String VALIDATE_ADMIN_LOGIN = 
            "SELECT admin_id FROM admin WHERE username = ? AND password = ? AND company_id = ?";

        public static final String VALIDATE_NEW_ADMIN_REGISTER =     
        "SELECT COUNT(*) FROM admin WHERE username = ? AND company_id = ?";    

        public static final String INSERT_INTO_ADMIN = 
        "INSERT INTO admin (username, password, company_id) VALUES (?, ?, ?)";

        public static final String FETCH_TOTAL_REVENUE =
        "SELECT SUM(total_price) AS revenue FROM `order` WHERE company_id = ?";

        public static final String FETCH_MOST_LIKED_PRODUCTS =
        "SELECT product_id, product_name, likes FROM product WHERE company_id = ? ORDER BY likes DESC LIMIT 1";

        public static final String FETCH_BEST_SELLER_PRODUCTS = """
            SELECT p.product_id, p.product_name, SUM(o.quantity) AS total_sold
            FROM `order` o
            JOIN product p ON o.product_id = p.product_id
            WHERE o.company_id = ?
            GROUP BY o.product_id, p.product_name
            ORDER BY total_sold DESC
            LIMIT 5
        """;

    
        public static final String GET_PRODUCTS_BY_SUB_CAT = "SELECT * FROM product WHERE sub_category_id = ?";
        public static final String GET_PRODUCT_QUANTITY = "SELECT product_quantity FROM product WHERE product_Id = ?";
        public static final String REDUSE_STOCK = "UPDATE product SET product_quantity = product_quantity - ? WHERE product_Id = ? AND product_quantity >= ?";
        public static final String GET_PRODUCT_BY_ID = "SELECT * FROM product WHERE product_id = ?";
        public static final String GET_PRODUCT_STOCK = "SELECT quantity FROM product WHERE product_id = ? AND company_id = ?";
        public static final String REMOVE_FROM_WISHLIST_DESCREASE_LIKE = "UPDATE product SET likes = likes - 1 WHERE product_id = ? AND likes > 0";
}


