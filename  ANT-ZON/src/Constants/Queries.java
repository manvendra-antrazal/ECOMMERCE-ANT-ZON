package Constants;

public class Queries {

    // Seller Queries
    public static final String CHECK_SELLER =
        "SELECT * FROM seller WHERE seller_username = ? AND seller_password = ?";

    public static final String INSERT_SELLER =
        "INSERT INTO seller (seller_Name, seller_username, seller_password, seller_Mobile_NO, company_ID, role) " +
        "VALUES (?, ?, ?, ?, ?, ?)";

    // Buyer Queries
    public static final String CHECK_BUYER =
        "SELECT * FROM buyer WHERE Buyer_UserName = ? AND Buyer_Psd = ?";

    public static final String INSERT_BUYER =
        "INSERT INTO buyer (Buyer_UserName, Buyer_Email, Buyer_Psd, Buyer_Role, city, state, county, local_Address, Buyer_Mob_No, company_ID) " +
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
    // Get all products queries
    public static final String GET_ALL_PRODUCTS = "SELECT product_Id, product_Name, product_Info, product_Price, product_Quantity FROM product";
}
