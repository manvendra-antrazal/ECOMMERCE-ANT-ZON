package Constants;

public class Message {

    // General
    public static final String WELCOME = "Welcome to ANT-ZON E-Commerce Application!";
    public static final String VALID_NUMBER = "Please enter a valid number.";
    public static final String SELECT_PRODUCT_INDEX = "Enter the product no: ";
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
    public static final String WISHLIST_REMOVE_ERROR = "Error removing product from wishlist: ";
    public static final String PRODUCT_EXISTS_IN_CART = "Product already exists in cart.";
    public static final String PRODUCT_EXISTS_IN_WISHLIST = "Product already exists in wishlist.";
    public static final String ERROR_FETCHING_CATEGORIES = "Error fetching categories: ";
    public static final String WISHLIST_ADD_ERROR = "Error adding product to wishlist: ";
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
    public static final String ENTER_PRODUCT_NO_UPDATE = "Enter product number to update quantity: ";
    public static final String ENTER_NEW_QUANTITY = "Enter new quantity: ";
    public static final String QUANTITY_UPDATED = "Quantity updated successfully!";
    public static final String UPDATE_FAILED = "Error occurred while updating quantity. Please try again.";
    public static final String REMOVE_PRODUCT_FROM_WISHLIST_AND_LIKE_DECREASE = "Failed to remove product from wishlist: ";
    public static final String CONNECTION_ERROR = "Database connection error: ";
  
    public static final String PRODUCT_LIST = """

       ╔════════════════════════════════════════════╗
       ║               Product's List                ║
       ╠══════════╬═════════════════════════════════╣""";
    
    public static final String PRODUCT_LIST_LOWER = 
      "╚══════════╩═════════════════════════════════╝\n";

    // Cart and Wishlist
    public static final String PRODUCT_ADDED_TO_CART = "Product added to cart successfully.";
    public static final String PRODUCT_ALREADY_IN_CART = "Product is already in your cart.";
    public static final String CART_ERROR = "Something went wrong while adding the product to cart.";
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
    public static final String SELECT_OPTION_A_B = "Please enter your choice (A/B): ";
            
    public static final String PRODUCT_UPPER =

    "╔═══════════════════════════════════════════════════════════════════════════════════════════════════════╗";
    public static final String PRODUCTS_MIDDLE = 
    "╠═══════════════════════════════════════════════════════════════════════════════════════════════════════╣";
    public static final String PRODUCTS_LOWER = 
    "╚═══════════════════════════════════════════════════════════════════════════════════════════════════════╝\n";

    public static final String PRODUCTS_LAST = """
     ╠═══════════════════════════════════════════════════════════════════════════════════════════════════════╣
     ║                                         [A] BACK  |   [B] EXIT                                        ║
     ╚═══════════════════════════════════════════════════════════════════════════════════════════════════════╝
       """;

    
    
    public static final String CART_UPPER_FRAME = """
         ╔═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗
         ║                                                         Your Cart                                                             ║
         ╠═════╦═════════════════════════╦════════════╦══════════════╦══════════╦════════════════════════════════════════════════════════╣
         ║ No  ║ Product Name            ║ Price      ║ Discounted   ║ Qty      ║ Description                                            ║
         ╠═════╬═════════════════════════╬════════════╬══════════════╬══════════╬════════════════════════════════════════════════════════╣""";

                

    public static final String CART_LOWER_FRAME = """
         ╠═════╩═════════════════════════╩════════════╩══════════════╩══════════╩════════════════════════════════════════════════════════╣
         ║                    [A] BACK  |  [B] BUY NOW  |  [C] REMOVE PRODUCT  |  [D] CHANGE QUANTITY  |  [E] LOGOUT                     ║
         ╚═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝""";
       
         
    public static final String EMPTY_CART = """
       ╔════════════════════════════════════════════════════╗ 
       ║                Your cart is empty.                 ║
       ╚════════════════════════════════════════════════════╝
       """;

    public static final String WISHLIST_UPPER_FRAME = """
    
       ╔══════════════════════════════════════════════════════════════════════════════════════════════════════════╗ 
       ║                                                Wishlist                                                  ║                                                                   
       ╠═════╦══════════════════════╦══════════╦══════════╦═══════════════════════════════════════════════════════╣
       ║ No  ║ Product Name         ║ Price    ║ Qty      ║ Description                                           ║
       ╠═════╬══════════════════════╬══════════╬══════════╬═══════════════════════════════════════════════════════╣""";

    public static final String WISHLIST_LOWER_FRAME = """
       ╠═════╩══════════════════════╩══════════╩══════════╩═══════════════════════════════════════════════════════╣
       ║                   [A] BACK  |  [B] Add To Cart  |  [C] REMOVE PRODUCT  |  [D] LOGOUT                     ║
       ╚══════════════════════════════════════════════════════════════════════════════════════════════════════════╝\n""";
      
    

     public static final String EMPTY_WISHLIST = """
       ╔════════════════════════════════════════════════════╗ 
       ║              Your wishlist is empty                ║
       ╚════════════════════════════════════════════════════╝
       """; 


    public static final String CHOICE_INPUT = "Enter your choice (A/B/C/D): ";
    public static final String CART_CHOICE_INPUT = "Enter your choice (A/B/C/D/E): ";
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


    // Admin 
    public static final String ADMIN_REGISTERED_SUCCESS = "Admin registration successful.";
    public static final String ADMIN_REGISTERED_FAILED = "Registration failed. Username may already exist.";
    public static final String ADMIN_MENU_FRAME =
    """
    ╔══════════════════════════════════════════════════════════════════╗
    ║                         === ADMIN MENU ===                       ║
    ╠══════════════════════════════════════════════════════════════════╣
    ║  1. View Current Company Name                                    ║
    ║  2. View Total Revenue                                           ║
    ║  3. View Most Liked Products                                     ║
    ║  4. View Best Seller Products                                    ║
    ╠══════════════════════════════════════════════════════════════════╣
    ║         [A] Back to Company Selection  |  [B] Exit               ║
    ╚══════════════════════════════════════════════════════════════════╝
    """;

    public static final String TOTAL_REVENUE_FRAME_TOP = """

            ╔════════════════════════════════════════════╗
            ║       TOTAL REVENUE FOR THIS COMPANY       ║
            ╠════════════════════════════════════════════╣""";

    public static final String TOTAL_REVENUE_FRAME_BOTTOM = """
            ╠════════════════════════════════════════════╣
            ║           [A] BACK  |  [B] EXIT            ║
            ╚════════════════════════════════════════════╝
            """;

    public static final String MOST_LIKED_FRAME_TOP = """
         ╔═══════════════════════════════════╗
         ║          Liked Products           ║
         ╠═══════╦═══════════════════════════╣""";
        
    public static final String MOST_LIKED_FRAME_DIVIDER = 
        "╠═══════╬═══════════════════════════╣";
    
        public static final String MOST_LIKED_FRAME_BOTTOM =  """
         ╠═══════╩═══════════════════════════╣
         ║       [A] BACK  |  [B] EXIT       ║
         ╚═══════════════════════════════════╝
                """;

    public static final String NO_LIKED_PRODUCT = """

        ╔════════════════════════════════════════════════╗
        ║         ===  No liked products found ===       ║
        ╚════════════════════════════════════════════════╝
        """;

    public static final String BEST_SELLERS_FRAME_TOP ="""
        ╔═════════════════════════════════════════════════════════════╗
        ║                    Best-Selling Products                    ║
        ╠═════╦══════════════════════════════════╦════════════════════╣""";

        public static final String BEST_SELLERS_FRAME_MIDDLE = """
        ╠═════╬══════════════════════════════════╬════════════════════╣""";
    
        public static final String BEST_SELLERS_FRAME_BOTTOM = """
        ╠═════╩══════════════════════════════════╩════════════════════╣
        ║                  [A] BACK  |  [B] EXIT                      ║
        ╚═════════════════════════════════════════════════════════════╝
        """;
    public static final String NO_BEST_SELLERS = """

        ╔════════════════════════════════════════════════╗
        ║      === No best-seller data available ===     ║
        ╚════════════════════════════════════════════════╝
        """;

    public static final String CURRENT_COMPANY_UPPER = 
    "╔════════════════════════════════════════════════╗";

    public static final String CURRENT_COMPANY_BOTTOM = 
    "╚════════════════════════════════════════════════╝";

    public static final String ERROR_FETCHING_REVENUE = "Error fetching revenue:";
    public static final String ERROR_FETCHING_MOST_LIKED = "Error fetching most liked products: ";
    public static final String ERROR_FETCHING_BEST_PRODUCTS = "Error fetching best sellers: ";
    public static final String ENTER_MOBILE_NUM= "Enter Mobile Number: ";
    public static final String ENTER_NEW_SELLER_NAME = "Enter New Seller Name: ";
    public static final String ENTER_NEW_SELLER_USERNAME = "Enter New Seller Username: ";
    public static final String ENTER_NEW_ADMIN_USERNAME = "Enter new username: ";
    public static final String ENTER_NEW_ADMIN_PASSWORD = "Enter new password: ";
    public static final String USERNAME_INVALID = "Username can only contain letters, digits, underscores and @.";
    public static final String EMAIL_INVALID = "Invalid email format. Please enter a valid email.";
    public static final String INVALID_CITY_ERROR = "Invalid input. City must contain only alphabets and spaces.";
    public static final String INVALID_STATE_ERROR = "Invalid input. State must contain only alphabets and spaces.";
    public static final String INVALID_COUNTRY_ERROR = "Invalid input. County must contain only alphabets and spaces.";
    public static final String INVALID_ADDRESS_ERROR = "Invalid address. Use only letters, digits, spaces, commas, dots, slashes, and dashes.";
    public static final String USERNAME = "Enter Username: ";   
    public static final String ENETR_PASSWORD = "Enter Password: ";
    public static final String ENETR_EMAIL = "Enter Email: ";
    public static final String ENTER_CITY = "Enter City: ";
    public static final String ENTER_STATE = "Enter State: ";
    public static final String ENTER_COUNTRY = "Enter Country: ";
    public static final String ENTER_ADDRESS = "Enter Local Address: ";
     public static final String ORDER_INSERT_FAILED = "Failed to insert order into database.";
    public static final String ORDER_FETCH_FAILED = "Failed to fetch orders for buyer.";

    public static final String CHECKOUT_OPTIONS_MENU ="""
    ╔════════════════════════════════════════╗
    ║           Choose checkout type:        ║
    ╠════════════════════════════════════════╣
    ║   1. Order a single item               ║
    ║   2. Order all items in cart           ║
    ╚════════════════════════════════════════╝
            """;
    
    public static final String ENTER_CHOICE = "Enter your choice: ";
    public static final String ENTER_PRODUCT_ID = "Enter Product ID to order: ";
    public static final String INVALID_PRODUCT_ID = "Invalid Product ID. Item not found in cart.";
    public static final String INVALID_NUMERIC_INPUT = "Invalid input. Please enter a numeric Product ID.";
    public static final String BULK_CONFIRM_MESSAGE = "You are about to place order for **ALL ITEMS** in your cart.";
    public static final String CONFIRM_YN = "Are you sure? (Y/N): ";
    public static final String ORDER_CANCELLED = "Order cancelled. Returning to cart...";
    public static final String INVALID_OPTION_RETURNING = "Invalid option. Returning to cart menu.";
    public static final String FAILED_ORDER = "Failed to place order due to I/O error.";
    public static final String SELLER_FLOW_ERROR = "An unexpected error occurred in Seller flow. Please try again.";
    public static final String ERROR_VALIDATE_ADMIN_LOGIN = "Error validating admin login: ";
    public static final String ERROR_REGISTER_ADMIN = "Error registering new admin: ";
    // Errors 
    
    public static final String EMPTY_INPUT = "Input cannot be empty. Please enter a valid option.";
    public static final String GENERAL_ERROR = "An error occurred:";
    public static final String ERROR_ADMIN_LOGIN = "Admin login failed!";
    public static final String UNEXPECTED_ERROR = "An unexpected error occurred!";
    

    public static final String ERROR_VIEW_COMPANY = "Unable to fetch company details.";
    public static final String ERROR_VIEW_REVENUE = "Failed to fetch total revenue.";
    public static final String ERROR_VIEW_MOST_LIKED = "Error while fetching most liked products.";
    public static final String ERROR_VIEW_BEST_SELLER = "Error while fetching best seller products.";
    public static final String DATABASE_ERROR = "Database error while updating cart quantity.";
    public static final String REGISTRATION_FAILED = "Registration failed. Please try again."; 
    public static final String FAILED_TO_FETCH_PRODUCTS = "Failed to fetch products: ";
    public static final String FAILED_TO_FETCH_PRODUCTS_QUANTITY = "Error fetching product quantity: ";
    public static final String ERROR_REDUSE_STOCK = "Error reducing stock: ";
    public static final String WISHLIST_CHECK_ERROR = "Error checking wishlist: ";
    public static final String ADD_TO_WISHLIST_ERROR = "Error adding to wishlist: ";
    public static final String GET_WISHLIST_ERROR = "Error retrieving wishlist: ";
    public static final String COMPANY_VIEW_ERROR = "Unable to display company name: ";
    public static final String REVENUE_VIEW_ERROR = "Failed to retrieve revenue: ";
    public static final String MOST_LIKED_VIEW_ERROR = "Failed to load most liked products: ";
    public static final String BEST_SELLERS_VIEW_ERROR = "Failed to load best-seller products: ";
    public static final String DELETE_USER_ERROR = "Failed to delete user data: ";
    public static final String LOGIN_ERROR = "Error occurred during login. Please try again.";
    public static final String INTERNAL_ERROR = "An unexpected error occurred. Please contact support.";
    public static final String ERROR_ADD_TO_CART = "Error occurred while adding product to cart.";
    public static final String ERROR_FETCHING_CART = "Error fetching cart items.";
    public static final String REMOVE_FAILED = "Failed to remove product from cart.";
    public static final String NO_VALID_PRODUCTS_PURCHASED = "No valid products were selected for purchase.";
    public static final String DB_ERROR_FETCHING_COMPANIES = "Error: Unable to fetch companies from the database.";
    public static final String UNKNOWN_ERROR = "An unknown error occurred.";
    public static final String ERROR_RETRIEVING_HISTORY = "Error retrieving order history.";
    public static final String REGISTER_ERROR = "Error during registration";
    public static final String ERROR_ADDING_TO_WISHLIST = "An error occurred while adding the product to wishlist.";
    public static final String ERROR_VIEWING_WISHLIST = "An error occurred while viewing the wishlist.";
    public static final String LOGIN_FAILED_DB_ERROR = "Login failed due to database error ";
    public static final String SELLER_REGISTER_FAILED = "Seller registration failed";
    public static final String ADMIN_LOGIN_SUCCESS = "Admin login successful.";
    public static final String ADMIN_LOGIN_FAILED = "Invalid admin credentials or unauthorized access.";
}


