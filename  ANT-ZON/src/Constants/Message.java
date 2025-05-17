package Constants;

public class Message {

    // General
    public static final String WELCOME = "Welcome to ANT-ZON E-Commerce Application!";
    public static final String EXIT_MESSAGE = "Thank you for visiting ANT-ZON. Goodbye!";
    public static final String INVALID_INPUT = "Invalid input. Please try again.";
    public static final String SELECT_OPTION = "Select option: ";
    public static final String FETCHING_FAILED = "Facing problem in fetching!";
    public static final String BACK_LOGOUT_EXIT_FRAME = """
            --------------------------------------            
            [A] BACK  |  [B] LOGOUT  |  [C] EXIT
            ======================================        
            """;

    public static final String BACK_AND_EXIT_FRAME = """
            --------------------------------------            
                    [A] BACK  |  [B] EXIT  
            ======================================        
            """;
    
    
    public static final String BUYER_LOGIN_MENU = """
            ======================================            
                    === BUYER LOGIN === 
            ======================================
            1. LOGIN 
            2. Register
            """;
    
    public static final String SELLER_LOGIN_MENU = """
            ======================================            
                    === SELLER LOGIN === 
            ======================================
            1. LOGIN 
            2. Register
            """;

    public static final String ADMIN_LOGIN_MENU = """
            ======================================            
                    === ADMIN LOGIN === 
            ======================================
            1. LOGIN 
            2. Register
            """;

    // Role
    public static final String ROLE_SELECT = """
            ======================================            
                    === SELECT ROLE === 
            ====================================== 
            1. BUYER
            2. SELLER
            3. ADMIN
            """;
    public static final String ENTER_ROLE = "Please enter your role: (A) Login | (B) Register";
    public static final String ROLE_NOT_FOUND = "Role not recognized. Please enter A or B.";

    // Company
    public static final String EXISTING_COMAPNY = """
            ======================================
                    ==== Companies ====
            ======================================    """;
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
            ======================================
                Enter Login Credentials
            ======================================    
            Username: """;
    public static final String PASSWORD = "Password: ";
    public static final String PASSWORD_INVALID = "Password must be at least 8 characters with uppercase, lowercase, and a number.";
    public static final String MOBILE_INVALID = "Invalid mobile number. Must be 10 digits.";
    public static final String LOGIN_SUCCESS = "Login successful.";
    public static final String LOGIN_FAILED = "Login failed. Please try again.";
    public static final String SIGNUP_SUCCESS = "Signup successful. Welcome!";
    public static final String REGISTER = "Registration successful";
    public static final String REGISTER_FAILED = "Registration failed. Try again.";

    // Buyer
    public static final String BUYER_ADDED = "Buyer added successfully.";
    public static final String BUYER_REDIRECTING = "Redirecting to Buyer Login for ";
    public static final String BUYER_MENU = """
            ==========================================
                        === BUYER MENU ===
            ==========================================
            1. Browse Products
            2. Add to Wishlist
            3. Add to Cart
            4. Checkout
            5. Invoice
            6. Order Status
            """;

    // Seller

    public static final String SELLER_ADDED = "Seller added successfully.";
    public static final String SELLER_MENU = """
            ==========================================
                    === SELLER MENU ===
            ==========================================
            1. View All Products
            2. Add Products
            3. Update Product Info
            4. Update Quantity
            5. Delete Products
            6. View Stats
            """;

    // Menu
    public static final String MENU = """
            ==========================================
                        ==== MAIN MENU ====
            ==========================================
            1. Existing Companies
            2. Exit
            """;

    // Category
    public static final String CATEGORY = """
            ==========================================
                    ==== CHOOSE CATEGORY ====
            ==========================================
            1. Electronic
            2. Furniture
            """;

    // Products
    public static final String ADD_PRODUCT = "Enter product details to add:";
    public static final String PRODUCT_ADDED = "Product added successfully.";
    public static final String PRODUCT_UPDATED = "Product updated successfully.";
    public static final String PRODUCT_DELETED = "Product deleted successfully.";
    public static final String PRODUCT_OUT_OF_STOCK = "Product is currently out of stock.";
    public static final String PRODUCT_LIST_HEADER = "==== Available Products ====";

    public static final String ELECTRONIC_PRODUCT_LIST = """
            ======================================
                ==== Electronic Products ====
            ======================================
            1. Laptop
            2. Mobile
            3. Wearable
            4. Printers
            """;

    public static final String FURNITURE_PRODUCT_LIST = """
            ======================================
                ==== Furniture Products ====
            ======================================
            1. Room
            2. Office
            3. Event
            """;

    // Cart and Wishlist
    public static final String PRODUCT_ADDED_TO_CART = "Product added to cart.";
    public static final String PRODUCT_ADDED_TO_WISHLIST = "Product added to wishlist.";
    public static final String CART_EMPTY = "Your cart is empty.";
    public static final String CART_TOTAL = "Cart total (after applicable discounts): ";

    // Discounts
    public static final String DISCOUNT_APPLIED = "Special discount applied!";

    // Invoice
    public static final String INVOICE_GENERATED = "Invoice has been generated and saved.";

    // Inventory
    public static final String INVENTORY_LIMIT_REACHED = "Cannot add more. Inventory limit reached.";

    // Statistics
    public static final String SHOW_STATS_HEADER = """
            ======================================
                ===== ANT-ZON Statistics =====
            ====================================== """;
    public static final String STATS_REVENUE = "Total Revenue: ";
    public static final String STATS_MOST_LIKED = "Most Liked Product: ";
    public static final String STATS_BEST_SELLER = "Best Seller Product: ";
}
