package Services;

import Constants.Message;
import Controller.BuyerController;
import Controller.Cart_Controller;
import Controller.CompanyController;
import Controller.Wishlist_Controller;
import Modal.Category;
import Modal.Company;
import Modal.Product;
import Modal.Sub_Category;
import Repository.Buyer_Repo;
import Repository.Category_Repo;
import Repository.Product_Repo;
import Repository.Register_Buyer_Repo;
import Repository.SubCategory_Repo;
import Util.Validations;
import java.util.List;
import java.util.Scanner;

public class Buyer_Service {
    public static void handleBuyerLogin(Scanner inputscanner, String role, Company company) {
        while (true) {
            System.out.println(Message.BUYER_LOGIN_MENU);
            // System.out.println(Message.BACK_AND_EXIT_FRAME);
            System.out.print(Message.SELECT_OPTION);

            String input = inputscanner.nextLine().trim();

            switch (input.toUpperCase()) {
                case "A":
                    CompanyController.handleLoginRoles(company, inputscanner); 
                    return;
                case "B":
                    System.out.println(Message.EXIT_MESSAGE);
                    System.exit(0);
                    break;
                default:
                    try {
                        int option = Integer.parseInt(input);
                        switch (option) {
                            case 1:
                                int buyerId = LOGIN(inputscanner);
                                if (buyerId > 0) {
                                    System.out.println(Message.LOGIN_SUCCESS);
                                    BuyerController.showBuyerMenu(inputscanner, role, company, buyerId);
                                }
                                break;
                            case 2:
                                if (REGISTER_BUYER(inputscanner, role, company)) {
                                    // System.out.println(Message.BUYER_ADDED);
                                    LOGIN(inputscanner);
                                } else {
                                    System.out.println(Message.REGISTER_FAILED);
                                }
                                break;
                            default:
                                System.out.println(Message.INVALID_INPUT);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(Message.INVALID_INPUT);
                    }
            }
        }
    }

    

    public static int LOGIN(Scanner inputscanner) {
    System.out.print(Message.LOGIN_CREDENTIAL);
    String username = inputscanner.nextLine();

    String password;
    while (true) {
        System.out.print(Message.PASSWORD);
        password = inputscanner.nextLine();

        if (Validations.isValidPassword(password)) {
            break;
        } else {
            System.out.println(Message.WRONG_PASSWORD);
        }
    }
    System.out.print(Message.LOGIN_CREDENTIAL_LOWER);
        Buyer_Repo buyerRepo = new Buyer_Repo();
        int buyerId = buyerRepo.getBuyerId(username, password);

    if (buyerId > 0) {
        return buyerId;
    } else {
        System.out.println(Message.LOGIN_FAILED);
        return -1;
    }
    }

    public static boolean REGISTER_BUYER(Scanner inputscanner, String role, Company company) {
        Register_Buyer_Repo registerRepo = new Register_Buyer_Repo();
        return registerRepo.REGISTER(inputscanner, role, company);
    }


    //---------------------------------------------------------------------------------------------------------
    // View all products functionality in Buyer
    public static void browseProducts(Scanner inputScanner, String role, Company company, int buyer) {
    Category_Repo categoryRepo = new Category_Repo();
    // List<Category> categories = categoryRepo.getAllCategories();
    List<Category> categories = categoryRepo.getAllCategories();

    if (categories.isEmpty()) {
        System.out.println(Message.CATEGORY_NOT_AVAILABLE);
        return;
    }

    System.out.println(Message.SELLER_CATEGORY_FRAME); // "Choose a category to browse:"
    for (int i = 0; i < categories.size(); i++) {
        System.out.printf("║   %-2d. %-31s  ║\n", (i + 1), categories.get(i).getCategory_Name());
    }
    System.out.println(Message.SELLER_CATEGORY_LOWER_FRAME); // A. Back | C. Exit

    int selectedCategoryIndex = -1;
    while (true) {
        System.out.print(Message.SELECT_OPTION);
        String input = inputScanner.nextLine().trim().toUpperCase();

        if (input.equals("A")) {
                BuyerController.showBuyerMenu(inputScanner, role, company, buyer);
        }  
        if (input.equals("B")) {
            System.out.println(Message.EXIT_MESSAGE);
            System.exit(0);
        }

        try {
            selectedCategoryIndex = Integer.parseInt(input) - 1;
            if (selectedCategoryIndex >= 0 && selectedCategoryIndex < categories.size()) break;
            System.out.println(Message.INVALID_OPTION);
        } catch (NumberFormatException e) {
            System.out.println(Message.INVALID_INPUT);
        }
    }

    int categoryId = categories.get(selectedCategoryIndex).getCategory_Id();

    // Fetch subcategories
    SubCategory_Repo subCategoryRepo = new SubCategory_Repo();
    List<Sub_Category> subCategories = subCategoryRepo.getSubCategoriesByCategoryId(categoryId, company.getCompany_Id());

    if (subCategories.isEmpty()) {
        System.out.println(Message.NO_SUB_CATEGORY_FOUND);
        return;
    }

    System.out.println(Message.SELLER_SUB_CATEGORY_UPPER_FRAME); 
    for (int i = 0; i < subCategories.size(); i++) {
         System.out.printf("║   %-2d. %-31s  ║\n", i + 1, subCategories.get(i).getSub_cat_Name());
    }
    System.out.println(Message.SELLER_SUB_CATEGORY_LOWER_FRAME);

    int selectedSubIndex = -1;
    while (true) {
        System.out.print(Message.SELECT_OPTION);
        String input = inputScanner.nextLine().trim().toUpperCase();

        if (input.equals("A")){
            BuyerController.showBuyerMenu(inputScanner, role, company, buyer);
        }
        if (input.equals("B")) {
            System.out.println(Message.EXIT_MESSAGE);
            System.exit(0);
        }

        try {
            selectedSubIndex = Integer.parseInt(input) - 1;
            if (selectedSubIndex >= 0 && selectedSubIndex < subCategories.size()) break;
            System.out.println(Message.INVALID_OPTION);
        } catch (NumberFormatException e) {
            System.out.println();
        }
    }

    int subCategoryId = subCategories.get(selectedSubIndex).getSub_cat_ID();

    // Fetch products using category_id and sub_category_id
    Product_Repo productRepo = new Product_Repo();
    List<Product> products = productRepo.getProductsByCategoryAndSubCategory(company.getCompany_Id(), subCategoryId);

    if (products.isEmpty()) {
        System.out.println(Message.NO_PRODUCT_FOUND);  // "No products found for this category."
        return;
    }

    System.out.println(Message.AVAILABLE_PRODUCTS);    // "Available products in this category:"
    System.out.println(Message.PRODUCT_UPPER);
    System.out.printf("║ %-3s ║ %-20s ║ %-8s ║ %-8s ║ %-50s ║\n", 
                  "No", "Product Name", "Price", "Qty", "Description");
    System.out.println(Message.PRODUCTS_MIDDLE);

    for (int i = 0; i < products.size(); i++) {
        Product p = products.get(i);

        String truncatedDescription = p.getProduct_Description();
        if (truncatedDescription.length() > 50) {
            truncatedDescription = truncatedDescription.substring(0, 47) + "...";
        }

        System.out.printf("║ %-3d ║ %-20s ║ %-8.2f ║ %-8d ║ %-50s ║\n", 
        i + 1,
        p.getProduct_Name(),
        p.getProduct_Price(),
        p.getProduct_Quantity(),
        truncatedDescription);
    }

    System.out.println(Message.PRODUCTS_LOWER);
    System.out.print(Message.SELECT_OPTION);

   int selectedIndex = -1;

// Select a valid product no
    while (true) {
        String input = inputScanner.nextLine().trim();
        try {
            int choice = Integer.parseInt(input);
            if (choice > 0 && choice <= products.size()) {
                selectedIndex = choice - 1;
                break;
            } else {
                System.out.println(Message.INVALID_OPTION);
            }
        } catch (NumberFormatException e) {
            System.out.println(Message.INVALID_OPTION);
        }
    }
    Product selectedProduct = products.get(selectedIndex);

    //  Show options and take action
    System.out.println(Message.BACK_ADDCART_ADDWISHLIST_LOGOUT);
    
    while (true) {
        System.out.print(Message.SELECT_OPTION);
        String input = inputScanner.nextLine().trim();

        if (input.equalsIgnoreCase("A")) {
            BuyerController.showBuyerMenu(inputScanner, input, company, buyer);
            break;
        } else if (input.equalsIgnoreCase("B")) {
            Cart_Controller.handleAddToCart(inputScanner, selectedProduct, company, buyer);
            break;
        } else if (input.equalsIgnoreCase("C")) {
            Wishlist_Controller.handleAddToWishlist(inputScanner, selectedProduct, company, buyer);
            break;
        } else if (input.equalsIgnoreCase("D")) {
            CompanyController.startCompanySelection(inputScanner);
            break;
        } else {
            System.out.println(Message.INVALID_OPTION);
        }
    }
    }    
}
