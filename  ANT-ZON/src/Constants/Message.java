package Constants;

public class Message {

    // General
    public static final String WELCOME = "Welcome to ANT-ZON E-Commerce Application!";
    public static final String VALID_NUMBER = "Please enter a valid number.";
    public static final String INVALID_SELECTION = "Invalid selection.";
    public static final String INVALID_ROLE_SELECTION = "Invalid role selected.";
    public static final String EXIT_MESSAGE = "Thank you for visiting ANT-ZON. Goodbye!";
    public static final String INVALID_INPUT = "Invalid input. Please try again.";
    public static final String SELECT_OPTION = "Please enter your choice: ";
    public static final String FETCHING_FAILED = "An error occurred while fetching the cart items.";
    public static final String WISHLIST_FETCING_FAILED = "An error occurred while removing from wishlist.";
    public static final String CATEGORY_NOT_AVAILABLE = "No categories available";
    public static final String DELETE_PRODUCT_ID = "Enter the Product ID you want to delete: ";
    public static final String UPDATING_FAILED = "Error! Updating product.";
    public static final String NO_PRODUCT_FOUND = "No Product Found! ";
    public static final String INVALID_DELETE_PRODUCT_ID = "Product not found. Please enter a valid Product ID.";
    public static final String DELETING_FAILED = "Error! Deleting product.";
    public static final String DELETING_CONFIRMATION = "Are you sure you want to delete this product? (yes/no): ";
    public static final String NO_SUB_CATEGORY_FOUND = "No subcategories found.";
    public static final String WISHLIST_PRODUCT_REMOVED = "Product removed from wishlist." ;
    public static final String DELETED_CANCEL = "Product deletion canceled.";
    public static final String FAILED_REMOVED_PRODUCT_FROM_CART = "Failed to remove product from cart.";
    public static final String FAILED_REMOVED_PRODUCT_FROM_WISHLIST = "Failed to remove from wishlist: ";
    public static final String PRODUCT_EXISTS_IN_CART = "Product already exists in cart.";
    public static final String ERROR_FETCHING_CATEGORIES = "Error fetching categories: ";
    public static final String SELLER_CATEGORY_FRAME = """

        ╔════════════════════════════════════════╗
        ║        === SELECT CATEGORY ===         ║
        ╠════════════════════════════════════════╣""";
    public static final String SELLER_CATEGORY_LOWER_FRAME ="""
        ╠════════════════════════════════════════╣
        ║        [A] BACK  |  [B] EXIT           ║
        ╚════════════════════════════════════════╝
                    """;
 
    public static final String SELLER_SUB_CATEGORY_UPPER_FRAME = """

        ╔════════════════════════════════════════╗
        ║     ===== Select Subcategory =====     ║
        ╠════════════════════════════════════════╣""";
    public static final String SELLER_SUB_CATEGORY_LOWER_FRAME = """
        ╠════════════════════════════════════════╣
        ║        [A] BACK  |  [B] EXIT           ║
        ╚════════════════════════════════════════╝       
        """;
                            

    public static final String BACK_LOGOUT_EXIT_FRAME = """
            --------------------------------------            
             [A] BACK  |  [B] LOGOUT  |  [C] EXIT
            ======================================        
            """;

    public static final String BACK_AND_EXIT_FRAME = """     
                [A] BACK  |  [B] EXIT      """;
            
    
    
    public static final String BUYER_LOGIN_MENU = """

        ╔════════════════════════════════════════╗
        ║         ===  BUYER LOGIN ===           ║
        ╠════════════════════════════════════════╣
        ║   1. Login                             ║
        ║   2. Register                          ║
        ╠════════════════════════════════════════╣
        ║        [A] BACK  |  [B] EXIT           ║
        ╚════════════════════════════════════════╝
        """;
   
    public static final String SELLER_LOGIN_MENU = """
        ╔════════════════════════════════════════╗
        ║          === SELLER LOGIN ===          ║
        ╠════════════════════════════════════════╣
        ║   1. Login                             ║
        ║   2. Register                          ║
        ╠════════════════════════════════════════╣
        ║        [A] BACK  |  [B] EXIT           ║
        ╚════════════════════════════════════════╝
        """;

    public static final String ADMIN_LOGIN_MENU = """
        ╔════════════════════════════════════════╗
        ║          === ADMIN LOGIN ===           ║
        ╠════════════════════════════════════════╣
        ║   1. Login                             ║
        ║   2. Register                          ║
        ╠════════════════════════════════════════╣
        ║        [A] BACK  |  [B] EXIT           ║
        ╚════════════════════════════════════════╝
        """;
                        
    // Role
    public static final String ROLE_SELECT = """
        ╔════════════════════════════════════════╗
        ║          === SELECT ROLE ===           ║
        ╠════════════════════════════════════════╣
        ║   1. Buyer                             ║
        ║   2. Seller                            ║
        ║   3. Admin                             ║
        ╠════════════════════════════════════════╣
        ║        [A] BACK  |  [B] EXIT           ║
        ╚════════════════════════════════════════╝
        """;

    public static final String ENTER_ROLE = "Please enter your role: (A) Login | (B) Register";
    public static final String ROLE_NOT_FOUND = "Role not recognized. Please enter A or B.";

    // Company
    public static final String EXISTING_COMAPNY = 
      "\n╔══════════════════════════════════════╗\n" +
        "║          EXISTING COMPANIES          ║\n" +
        "╠══════════════════════════════════════╣";
        
    public static final String EXISTING_COMPANY_SEPRATION = 
        "╠══════════════════════════════════════╣\n" +                                                        
        "║       [A] BACK  |  [B] EXIT          ║\n"+
        "╚══════════════════════════════════════╝\n"  ;   

    public static final String ADD_COMAPNY = "To Add New Company, Press (A)";
    public static final String NO_EXISTING_COMPANY_FOUND = "No companies found!";
    public static final String COMPANY_NAME = "Enter Company Name: ";
    public static final String COMPANY_USERNAME = "Enter Company Username: ";
    public static final String COMPANY_PSD = "Enter Password: ";
    public static final String COMPANY_EXISTS = "Company already exists! Enter a new company name.";
    public static final String COMAPNY_ADDED = "Company added successfully.";
    public static final String COMAPNY_FAILED = "Failed to add company! Try again.";

    // Login/Signup
    public static final String LOGIN_CREDENTIAL = """ 
        
        ╔════════════════════════════════════════╗
        ║        Enter Login Credentials         ║
        ╠════════════════════════════════════════╝
        ║ Username:   """;

    public static final String LOGIN_CREDENTIAL_LOWER = """
        ╚════════════════════════════════════════╝\n
            """;

    public static final String PASSWORD = "║ Password: ";
    public static final String PASSWORD_INVALID = "Password must be at least 8 characters with uppercase, lowercase, and a number.";
    public static final String WRONG_PASSWORD = "Wrong Password! Try again.";
    public static final String MOBILE_INVALID = "Invalid mobile number. Must be 10 digits.";
    public static final String LOGOUT = "Logging out...";
    public static final String LOGIN_SUCCESS = "Login successful.";
    public static final String LOGIN_FAILED = "Login failed. Please try again.";
    public static final String SIGNUP_SUCCESS = "Signup successful. Welcome!";
    public static final String REGISTER = "Registration successful";
    public static final String REGISTER_FAILED = "Registration failed. Try again.";
    public static final String ERROR_FETCH_SUBCAT = "Invalid option. Try again.";
    // Buyer
    public static final String BUYER_ADDED = "Buyer added successfully.";
    public static final String BUYER_REDIRECTING = "Redirecting to Buyer Login for ";
    public static final String BUYER_MENU = """
            
            ╔════════════════════════════════════════╗
            ║           === BUYER MENU ===           ║   
            ╠════════════════════════════════════════╣
            ║   1. Browse Products                   ║ 
            ║   2. View Wishlist                     ║
            ║   3. View Cart                         ║
            ║   4. Order History                     ║
            ╠════════════════════════════════════════╣
            ║  [A] BACK  |  [B] LOGOUT  |  [C] EXIT  ║
            ╚════════════════════════════════════════╝
            """;

    // Seller

    public static final String SELLER_ADDED = "Seller added successfully.";
    public static final String SELLER_MENU = """

        ╔════════════════════════════════════════╗
        ║           === SELLER MENU ===          ║
        ╠════════════════════════════════════════╣
        ║   1. View All Products                 ║
        ║   2. Add Product                       ║
        ║   3. Update Product                    ║
        ║   4. Delete Product                    ║
        ║   5. View Stats                        ║
        ╠════════════════════════════════════════╣
        ║        [A] BACK  |  [B] EXIT           ║
        ╚════════════════════════════════════════╝
        """;

    // Menu
    public static final String MENU =  "\n" +
        "╔══════════════════════════════════════╗\n" +
        "║          ANT-ZON MAIN MENU           ║\n" +
        "╠══════════════════════════════════════╣\n" +
        "║  1. Existing Companies               ║\n" +
        "║  2. Exit                             ║\n" +
        "╚══════════════════════════════════════╝\n" ;



    // Products
    public static final String ADD_PRODUCT = "Enter product details to add:";
    public static final String ADD_PRODUCT_ERROR = "Error! Cannot add product " ;
    public static final String ERROR_UPDATING_FIELD = "Error updating product field ";
    public static final String PRODUCT_NAME = "Enter product name: ";
    public static final String PRODUCT_DESCRIPTION = "Enter Product Description: ";
    public static final String INVALID_PRODUCT_DESCRIPTION = "Invalid info. Must be 5-50 characters long.";
    public static final String INVALID_PRODUCT_NAME = "Invalid name. Must be 5-30 alphabetic characters, letters/numbers only..";
    public static final String PRODUCT_PRICE = "Enter Product Price: ";
    public static final String INVALID_PRODUCT_PRICE = "Invalid price. Must be a non-negative number.";
    public static final String PRODUCT_QUANTITY = "Enter Product Quantity: ";
    public static final String INVALID_PRODUCT_QUANTITY = "Invalid quantity. Must be a non-negative integer.";
    public static final String PRODUCT_NOT_ADDED = "Problem occur to add product";
    public static final String PRODUCT_ADDED = "Product added successfully.";
    public static final String PRODUCT_UPDATED = "Product updated successfully.";
    public static final String PRODUCT_DELETED = "Product deleted successfully.";
    public static final String EMPTY_SELLER_PRODUCT_LIST = "You don't have any products to update.";
    public static final String UPDATE_PRODUCT_ID = "Enter the Product ID you want to update: ";
    public static final String PRODUCT_OUT_OF_STOCK = "Product is currently out of stock.";
    public static final String EMPTY_SELLER_PRODUCTS_LIST = "You don't have any products.";    
    public static final String PRODUCT_LIST = """

        ╔════════════════════════════════════════╗
        ║         === Your Products ===          ║
        ╠════════════════════════════════════════╣
        """;
        

//     public static final String ELECTRONIC_PRODUCT_LIST = """
//             ======================================
//                 ==== Electronic Products ====
//             ======================================
//             1. Laptop
//             2. Mobile
//             3. Wearable
//             4. Printers
//             """;

//     public static final String FURNITURE_PRODUCT_LIST = """
//             ======================================
//                 ==== Furniture Products ====
//             ======================================
//             1. Room
//             2. Office
//             3. Event
//             """;

    // Cart and Wishlist
    public static final String PRODUCT_ADDED_TO_CART = "Product added to cart successfully.";
    public static final String PRODUCT_ALREADY_IN_CART = "Product is already in your cart.";
    public static final String CART_ERROR = "Something went wrong while adding the product to cart.";
    // public static final String QUANTITY = "Enter quantity to add: ";
    // public static final String PRODUCT_ADDED_TO_WISHLIST = "Product added to wishlist.";
    public static final String CART_EMPTY = "Your cart is empty.";
    public static final String CART_TOTAL = "Cart total (after applicable discounts): ";

    // Discounts
    public static final String DISCOUNT_APPLIED = "Special discount applied!";

    // Invoice
    // public static final String INVOICE_GENERATED = "Invoice has been generated and saved.";

    // Inventory
    public static final String INVENTORY_LIMIT_REACHED = "Cannot add more. Inventory limit reached.";

    // Statistics
    public static final String SHOW_STATS_HEADER = """

            ╔════════════════════════════════════╗
            ║   ===== ANT-ZON Statistics =====   ║
            ╚════════════════════════════════════╝ 
            """;
    public static final String STATS_REVENUE = "Total Revenue: ";
    public static final String STATS_MOST_LIKED = "Most Liked Product: ";
    public static final String STATS_BEST_SELLER = "Best Seller Product: ";

    // Validation 
    public static final String INVALID_NAME = "Enter valid name";
    public static final String SELECT_FIELD_TO_UPDATE = """

        ╔════════════════════════════════════════╗
        ║      === SELECT FIELD TO UPDATE ===    ║
        ╠════════════════════════════════════════╣
        ║   1. Name                              ║
        ║   2. Description                       ║
        ║   3. Price                             ║
        ║   4. Quantity                          ║
        ╠════════════════════════════════════════╣
        ║        [A] BACK  |  [B] EXIT           ║
        ╚════════════════════════════════════════╝
        """;


    public static final String PRODUCT_NOT_FOUND = "Product not found.";
    public static final String PRODUCT_ID = "Enter the Product ID you want to update: ";
    public static final String INVALID_OPTION = "Invalid option. Please try again.";
    public static final String CHOOSE_OPTION = "Choose Option [A/B/C/D]: ";
    public static final String AVAILABLE_PRODUCTS = "Available products in this category: ";
    public static final String ADD_TO_CART_PROMPT = "Enter product number to add to cart: ";
    public static final String PRODUCT_REMOVE_FROM_WISHLIST = "Enter product number to REMOVE from wishlist: ";
    public static final String ADD_TO_WISHLIST_PROMPT = "Enter product number to add to wishlist: ";
    public static final String PRODUCT_ADDED_TO_WISHLIST = "Product added to your wishlist successfully!";
    public static final String PRODUCT_EXISTS_IN_WIHSLIST = "Product is already in your wishlist";
    public static final String INVALID_PRODUCT_SELECTION = "Invalid product selection.";
    public static final String DISCOUNTED_TOTAL = "Total after %.1f%% discount: $%.2f\n";
    public static final String TRANSACTION_ID = "Transaction ID: ";
    public static final String CHECKOUT_SUCCESS = "Checkout complete and invoice saved.";
    public static final String NO_ORDERS_PLACED = "No orders placed yet.";
    public static final String LAST_INVOICE = "Last Invoice:\n";
    public static final String NO_ORDER_HISTORY = "No order history found.";
    public static final String ORDER_HISTORY_SEPARATOR = "====================";
    public static final String BACK_ADDCART_ADDWISHLIST_LOGOUT = """
        ╔═══════════════════════════════════════════════════════════════════════╗
        ║  [A] BACK  |  [B] ADD TO CART  |  [C] ADD TO WISHLIST  |  [D] LOGOUT  ║
        ╚═══════════════════════════════════════════════════════════════════════╝
        """;
                
            
    public static final String PRODUCT_UPPER =

    "╔═══════════════════════════════════════════════════════════════════════════════════════════════════════╗";
    public static final String PRODUCTS_MIDDLE = 
    "╠═══════════════════════════════════════════════════════════════════════════════════════════════════════╣";
    public static final String PRODUCTS_LOWER = 
    "╚═══════════════════════════════════════════════════════════════════════════════════════════════════════╝\n";

    public static final String PRODUCTS_LAST = """
       ╠════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣
       ║                                                    [A] BACK  |   [B] EXIT                                                          ║
       ╚════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝
       """;

    
    
    public static final String CART_UPPER_FRAME = """
       ╔════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗
       ║                                                         Your Cart                                                                  ║
       ╠════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣""";

public static final String CART_MIDDLE_FRAME = """
       ╠════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣""";

public static final String CART_LOWER_FRAME = """
       ╠════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣
       ║                                [A] BACK  |  [B] BUY NOW  |  [C] REMOVE PRODUCT  |  [D] LOGOUT                                      ║
       ╚════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝
       """;
       
         
    public static final String EMPTY_CART = """
       ╔════════════════════════════════════════════════════╗ 
       ║                Your cart is empty.                 ║
       ╚════════════════════════════════════════════════════╝
       """;

    public static final String WISHLIST_UPPER_FRAME = """
       ╔══════════════════════════════════════════════════════════════════════════════════════════════════════════╗ 
       ║                                                Wishlist                                                  ║                                                                   
       ╠══════════════════════════════════════════════════════════════════════════════════════════════════════════╣
       ║ No  ║ Product Name         ║ Price    ║ Qty      ║ Description                                           ║
       ╠══════════════════════════════════════════════════════════════════════════════════════════════════════════╣""";

    public static final String WISHLIST_LOWER_FRAME = """
       ╠══════════════════════════════════════════════════════════════════════════════════════════════════════════╣
       ║                   [A] BACK  |  [B] Add To Cart  |  [C] REMOVE PRODUCT  |  [D] LOGOUT                     ║
       ╚══════════════════════════════════════════════════════════════════════════════════════════════════════════╝
       """;
      
    

     public static final String EMPTY_WISHLIST = """
       ╔════════════════════════════════════════════════════╗ 
       ║              Your wishlist is empty                ║
       ╚════════════════════════════════════════════════════╝
       """; 


    public static final String CHOICE_INPUT = "Enter your choice (A/B/C/D): ";
    public static final String ENTER_PRODUCT_NO = "Enter the product number to buy: ";
    public static final String ENTER_PRODUCT_NO_REMOVE = "Enter the product number to remove: ";
    public static final String QUANTITY = "Enter quantity: ";
    public static final String QUANTITY_GREATER_ZERO = "Quantity must be greater than 0.";
    public static final String QUANTITY_EXCEED = "Entered quantity exceeds available stock.";
    public static final String ORDER_SUCCESSFUL = "Order placed successfully!";
    public static final String ORDER_FAILED = "Failed to place the order.";
    public static final String INVALID_CHOICE = "Invalid choice. Please try again.";
    public static final String PRODUCT_REMOVED_CART = "Product removed from cart.";
    public static final String LOGOUT_SUCCESS = "Logged out successfully.";
 
    public static final String INVOICE_FOLDER = "invoices/";
    public static final String INVOICE_HEADER = """
        ╔════════════════════════════════════════════════════════════════════╗
        ║                           ANT-ZON INVOICE                          ║
        ╚════════════════════════════════════════════════════════════════════╝
            """;
    public static final String INVOICE_FILENAME_PREFIX = "invoice_buyer";
    public static final String TRANSACTION_ID_PREFIX = "TXN";
    public static final String NO_COD_NOTE = "Note: Cash on Delivery (COD) is not available.";
    public static final String STOCK_INSUFFICIENT = "Not enough stock for %s. Available: %d\n";
    public static final String INVOICE_GENERATED = """
        ╔════════════════════════════════════════════════════════════════════╗
        ║                THANK YOU FOR SHOPPING WITH ANT-ZON!                ║
        ╚════════════════════════════════════════════════════════════════════╝
            """;
    public static final String INVOICE_FOOTER = "Thank you for shopping with ANT-ZON!";


    public static final String ORDER_HISTORY_HEADER ="""
        ╔════════╦════════════════════════════════╦════════════╦══════════════╦══════════════════════╗
        ║ O.ID   ║ Product Name                   ║ Quantity   ║ Price/item   ║ Order Date           ║
        ╠════════╬════════════════════════════════╬════════════╬══════════════╬══════════════════════╣""";
       

    public static final String ORDER_HISTORY_FOOTER = """
        ╠════════╩════════════════════════════════╩════════════╩══════════════╩══════════════════════╣
        ║                                  [A] MENU  |  [B] LOGOUT                                   ║
        ╚════════════════════════════════════════════════════════════════════════════════════════════╝
        """;
        

    public static final String NO_ORDERS_MSG =
        "╔══════════════════════════════════════════════╗\n" +
        "║         No orders found in your history.     ║\n" +
        "╚══════════════════════════════════════════════╝";

    public static final String NO_ORDERS_FOUND = "...";
    public static final String ENTER_PASSWORD = "║ Enter Password: ";
    public static final String ERROR_LOGIN = "Login Error: ";
    public static final String ERROR_FETCHING_BUYER = "Error fetching buyer details: ";
    public static final String ERROR_FETCHING__CATEGORY_AND_SUBCAT = "Error: Could not fetch furniture/event category/subcategory IDs.";


    public static final String ADMIN_MENU_FRAME =
    """
    ╔══════════════════════════════════════════════════════════════════╗
    ║                         === ADMIN MENU ===                       ║
    ╠══════════════════════════════════════════════════════════════════╣
    ║  1. View Current Company Name                                    ║
    ║  2. View Total Revenue                                           ║
    ║  3. View Most Liked Products                                     ║
    ║  4. View Best Seller Products                                    ║
    ║  5. Delete Buyer & Seller                                        ║
    ╠══════════════════════════════════════════════════════════════════╣
    ║         [A] Back to Company Selection  |  [B] Exit               ║
    ╚══════════════════════════════════════════════════════════════════╝
    """;


}


