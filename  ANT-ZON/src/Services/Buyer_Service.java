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
import Util.PrintUtil;
import java.util.List;
import java.util.Scanner;

public class Buyer_Service {

    public static void handleBuyerLogin(Scanner inputScanner, String role, Company company) {
        while (true) {
            try {
                PrintUtil.printMessage(Message.BUYER_LOGIN_MENU);
                PrintUtil.printMessage(Message.SELECT_OPTION);
                String input = inputScanner.nextLine().trim();

                switch (input.toUpperCase()) {
                    case "A":
                        CompanyController.handleLoginRoles(company, inputScanner);
                        return;
                    case "B":
                        PrintUtil.printMessage(Message.EXIT_MESSAGE);
                        System.exit(0);
                        break;
                    default:
                        try {
                            int option = Integer.parseInt(input);
                            switch (option) {
                                case 1:
                                    int buyerId = loginBuyer(inputScanner, company);
                                    if (buyerId != -1) {
                                        PrintUtil.printMessage(Message.LOGIN_SUCCESS);
                                        BuyerController.showBuyerMenu(inputScanner, role, company, buyerId);
                                    } else {
                                        PrintUtil.printMessage(Message.LOGIN_FAILED);
                                    }
                                    break;
                                case 2:
                                    REGISTER_BUYER(inputScanner, role, company);
                                    break;
                                default:
                                    PrintUtil.printMessage(Message.INVALID_OPTION);
                            }
                        } catch (NumberFormatException e) {
                            PrintUtil.printMessage(Message.INVALID_INPUT);
                        }
                }
            } catch (Exception e) {
                PrintUtil.printMessageWithException(Message.INTERNAL_ERROR, e);
            }
        }
    }

    private static int loginBuyer(Scanner inputScanner, Company company) {
        try {
            PrintUtil.printMessage(Message.LOGIN_CREDENTIAL);
            String username = inputScanner.nextLine().trim();

            PrintUtil.printMessage(Message.PASSWORD);
            String password = inputScanner.nextLine().trim();

            PrintUtil.printMessage(Message.LOGIN_CREDENTIAL_LOWER);

            Buyer_Repo buyerRepo = new Buyer_Repo();
            return buyerRepo.getBuyerId(username, password);
        } catch (Exception e) {
            PrintUtil.printMessageWithException(Message.LOGIN_ERROR, e);
            throw new RuntimeException("Login failed due to database error", e);
        }
    }

    public static boolean REGISTER_BUYER(Scanner inputscanner, String role, Company company) {
        try {
            Register_Buyer_Repo registerRepo = new Register_Buyer_Repo();
            return registerRepo.REGISTER(inputscanner, role, company);
        } catch (Exception e) {
            PrintUtil.printMessageWithException(Message.REGISTRATION_FAILED, e);
            throw new RuntimeException("Buyer registration failed", e);
        }
    }

    public static void browseProducts(Scanner inputScanner, String role, Company company, int buyer) {
        try {
            Category_Repo categoryRepo = new Category_Repo();
            List<Category> categories = categoryRepo.getAllCategories();

            if (categories.isEmpty()) {
                PrintUtil.printMessage(Message.CATEGORY_NOT_AVAILABLE);
                return;
            }

            PrintUtil.printMessage(Message.SELLER_CATEGORY_FRAME);
            for (int i = 0; i < categories.size(); i++) {
                System.out.printf("║   %-2d. %-31s  ║\n", (i + 1), categories.get(i).getCategory_Name());
            }
            PrintUtil.printMessage(Message.SELLER_CATEGORY_LOWER_FRAME);

            int selectedCategoryIndex = -1;
            while (true) {
                PrintUtil.printMessage(Message.SELECT_OPTION);
                String input = inputScanner.nextLine().trim().toUpperCase();

                if (input.equals("A")) {
                    BuyerController.showBuyerMenu(inputScanner, role, company, buyer);
                    return;
                } else if (input.equals("B")) {
                    PrintUtil.printMessage(Message.EXIT_MESSAGE);
                    System.exit(0);
                }

                try {
                    selectedCategoryIndex = Integer.parseInt(input) - 1;
                    if (selectedCategoryIndex >= 0 && selectedCategoryIndex < categories.size()) break;
                    PrintUtil.printMessage(Message.INVALID_OPTION);
                } catch (NumberFormatException e) {
                    PrintUtil.printMessage(Message.INVALID_INPUT);
                }
            }

            int categoryId = categories.get(selectedCategoryIndex).getCategory_Id();

            SubCategory_Repo subCategoryRepo = new SubCategory_Repo();
            List<Sub_Category> subCategories = subCategoryRepo.getSubCategoriesByCategoryId(categoryId, company.getCompany_Id());

            if (subCategories.isEmpty()) {
                PrintUtil.printMessage(Message.NO_SUB_CATEGORY_FOUND);
                return;
            }

            PrintUtil.printMessage(Message.SELLER_SUB_CATEGORY_UPPER_FRAME);
            for (int i = 0; i < subCategories.size(); i++) {
                System.out.printf("║   %-2d. %-31s  ║\n", i + 1, subCategories.get(i).getSub_cat_Name());
            }
            PrintUtil.printMessage(Message.SELLER_SUB_CATEGORY_LOWER_FRAME);

            int selectedSubIndex = -1;
            while (true) {
                PrintUtil.printMessage(Message.SELECT_OPTION);
                String input = inputScanner.nextLine().trim().toUpperCase();

                if (input.equals("A")) {
                    BuyerController.showBuyerMenu(inputScanner, role, company, buyer);
                    return;
                } else if (input.equals("B")) {
                    PrintUtil.printMessage(Message.EXIT_MESSAGE);
                    System.exit(0);
                }

                try {
                    selectedSubIndex = Integer.parseInt(input) - 1;
                    if (selectedSubIndex >= 0 && selectedSubIndex < subCategories.size()) break;
                    PrintUtil.printMessage(Message.INVALID_OPTION);
                } catch (NumberFormatException e) {
                    PrintUtil.printMessage(Message.INVALID_INPUT);
                }
            }

            int subCategoryId = subCategories.get(selectedSubIndex).getSub_cat_ID();

            Product_Repo productRepo = new Product_Repo();
            List<Product> products = productRepo.getProductsByCategoryAndSubCategory(company.getCompany_Id(), subCategoryId);

            if (products.isEmpty()) {
                PrintUtil.printMessage(Message.NO_PRODUCT_FOUND);
                return;
            }

            PrintUtil.printMessages(
                Message.AVAILABLE_PRODUCTS,
                Message.PRODUCT_UPPER
            );
            System.out.printf("║ %-3s ║ %-20s ║ %-8s ║ %-8s ║ %-50s ║\n", 
                            "No", "Product Name", "Price", "Qty", "Description");
            PrintUtil.printMessage(Message.PRODUCTS_MIDDLE);

            for (int i = 0; i < products.size(); i++) {
                Product p = products.get(i);
                String desc = p.getProduct_Description();
                if (desc.length() > 50) desc = desc.substring(0, 47) + "...";
                System.out.printf("║ %-3d ║ %-20s ║ %-8.2f ║ %-8d ║ %-50s ║\n", 
                                  i + 1, p.getProduct_Name(), p.getProduct_Price(), p.getProduct_Quantity(), desc);
            }

            PrintUtil.printMessage(Message.PRODUCTS_LOWER);
            PrintUtil.printMessage(Message.SELECT_OPTION);

            int selectedIndex = -1;
            while (true) {
                try {
                    String input = inputScanner.nextLine().trim();
                    int choice = Integer.parseInt(input);
                    if (choice > 0 && choice <= products.size()) {
                        selectedIndex = choice - 1;
                        break;
                    } else {
                        PrintUtil.printMessage(Message.INVALID_OPTION);
                    }
                } catch (NumberFormatException e) {
                    PrintUtil.printMessage(Message.INVALID_OPTION);
                }
            }

            Product selectedProduct = products.get(selectedIndex);
            PrintUtil.printMessage(Message.BACK_ADDCART_ADDWISHLIST_LOGOUT);

            while (true) {
                
                PrintUtil.printMessage(Message.SELECT_OPTION);
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
                    PrintUtil.printMessage(Message.INVALID_OPTION);
                }
            }

        } catch (Exception e) {
            PrintUtil.printMessageWithException(Message.INTERNAL_ERROR, e);
        }
    }
}
