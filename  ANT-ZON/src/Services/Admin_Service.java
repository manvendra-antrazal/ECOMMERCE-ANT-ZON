package Services;

import Constants.Message;
import Controller.Admin_Controller;
import Controller.CompanyController;
import Modal.Company;
import Modal.Product;
import Repository.Admin_Repo;
import static Util.Validations.isValidPassword;

import java.util.List;
import java.util.Scanner;

public class Admin_Service {

    public static void handleAdminLogin(Scanner inputScanner, String role, Company company) {
        while (true) {
            System.out.println(Message.ADMIN_LOGIN_MENU); // Add this in your Message class
            System.out.print(Message.SELECT_OPTION);

            String input = inputScanner.nextLine().trim().toUpperCase();

            switch (input) {
                case "A":
                    CompanyController.handleLoginRoles(company, inputScanner);
                    return;
                case "B":
                    System.out.println(Message.EXIT_MESSAGE);
                    System.exit(0);
                    break;
                case "1":
                    int adminId = loginAdmin(inputScanner, company);
                    if (adminId != -1) {
                        System.out.println("-> Admin login successful.");
                        Admin_Controller.showAdminMenu(inputScanner, company, adminId);
                    } else {
                        System.out.println("-> Invalid admin credentials or unauthorized access.");
                    }
                    break;
                case "2":
                    registerAdmin(inputScanner, role, company);
                    break;
                default:
                    System.out.println(Message.INVALID_OPTION);
                    break;
            }
        }
    }

    // LOGIN ADMIN
    private static int loginAdmin(Scanner inputScanner, Company company) {
        System.out.print(Message.LOGIN_CREDENTIAL);  // "Enter username: "
        String username = inputScanner.nextLine().trim();

        System.out.print(Message.PASSWORD);          // "Enter password: "
        String password = inputScanner.nextLine().trim();

        System.out.println(Message.LOGIN_CREDENTIAL_LOWER); // "Validating credentials..."

        return Admin_Repo.validateAdminLogin(username, password, company.getCompany_Id());
    }

    // register Admin 
    private static void registerAdmin(Scanner inputScanner, String role, Company company) {
        System.out.print(Message.ENTER_NEW_ADMIN_USERNAME);
        String username = inputScanner.nextLine().trim();

        String password;
        while (true) {
            System.out.print(Message.ENTER_NEW_ADMIN_PASSWORD);
            password = inputScanner.nextLine().trim();

            if (isValidPassword(password)) {
                break;  // valid password, exit loop
            } else {
                System.out.println(Message.PASSWORD_INVALID);
            }
        }

        boolean success = Admin_Repo.registerNewAdmin(username, password, company.getCompany_Id());

        if (success) {
            System.out.println(Message.ADMIN_REGISTERED_SUCCESS);
            loginAdmin(inputScanner, company);
        } else {
            System.out.println(Message.ADMIN_REGISTERED_FAILED);
        }
    }


    // company name 
    public static void viewCompanyName(Company company) {
        System.out.println(Message.CURRENT_COMPANY_UPPER);
         System.out.printf("║ Current Company: %-30s║\n", company.getCompany_Name());
        System.out.println(Message.CURRENT_COMPANY_BOTTOM);
    }

    // Revenue 
    public static void viewTotalRevenue(Scanner scanner, Company company, int companyId) {
        double revenue = Admin_Repo.fetchTotalRevenue(companyId);

        System.out.println(Message.TOTAL_REVENUE_FRAME_TOP);
        System.out.printf("║         %-34.2f ║\n", revenue);
        System.out.println(Message.TOTAL_REVENUE_FRAME_BOTTOM);

        while (true) {
            System.out.print(Message.SELECT_OPTION);
            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice) {
                case "A":
                    Admin_Controller.showAdminMenu(scanner, company, 0);  // replace 0 with actual adminId if possible
                    return;
                case "B":
                    System.out.println(Message.EXIT_MESSAGE);
                    System.exit(0);
                default:
                    System.out.println(Message.INVALID_OPTION);
                    break;
            }
        }
    }


    // Most liked Products 
    public static void viewMostLikedProducts(Scanner scanner, Company company, int companyId) {
        List<Product> likedProducts = Admin_Repo.fetchMostLikedProducts(companyId);

        System.out.println(Message.MOST_LIKED_FRAME_TOP);
        System.out.printf("║ %-5s ║ %-25s ║\n", "ID", "Product Name");
        System.out.println(Message.MOST_LIKED_FRAME_DIVIDER);

        if (likedProducts.isEmpty()) {
            System.out.println(Message.NO_LIKED_PRODUCT);
        } else {
            for (Product product : likedProducts) {
                System.out.printf("║ %-5d ║ %-25s ║\n",
                        product.getProduct_Id(),
                        product.getProduct_Name());
            }
        }

        System.out.println(Message.MOST_LIKED_FRAME_BOTTOM);

        while (true) {
            System.out.print(Message.SELECT_OPTION);
            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice) {
                case "A":
                    Admin_Controller.showAdminMenu(scanner, company, 0);
                    return;
                case "B":
                    System.out.println(Message.EXIT_MESSAGE);
                    System.exit(0);
                default:
                    System.out.println(Message.INVALID_OPTION);
                    break;
            }
        }
    }


    public static void viewBestSellerProducts(Scanner scanner, Company company, int companyId) {
    List<Product> bestSellers = Admin_Repo.fetchBestSellerProducts(companyId);

    System.out.println(Message.BEST_SELLERS_FRAME_TOP);
    System.out.printf("║ %-4s║ %-33s║ %-19s║\n", "ID", "Product Name", "Total Sold");
    System.out.println(Message.BEST_SELLERS_FRAME_MIDDLE);

    // System.out.println(Message.BEST_SELLERS_FRAME_BOTTOM);

    if (bestSellers.isEmpty()) {
        System.out.println(Message.NO_BEST_SELLERS);
    } else {
        for (Product product : bestSellers) {
            System.out.printf("║ %-4s║ %-33s║ %-19s║\n",
                    product.getProduct_Id(),
                    product.getProduct_Name(),
                    product.getProduct_Quantity());  
        }
    }

    System.out.println(Message.BEST_SELLERS_FRAME_BOTTOM);

    while (true) {
        System.out.print(Message.SELECT_OPTION);
        String choice = scanner.nextLine().trim().toUpperCase();

        switch (choice) {
            case "A":
                Admin_Controller.showAdminMenu(scanner, company, 0);
                return;
            case "B":
                System.out.println(Message.EXIT_MESSAGE);
                System.exit(0);
            default:
                System.out.println(Message.INVALID_OPTION);
                break;
        }
    }
}


    public static void deleteBuyerAndSeller(Scanner scanner, int companyId) {
        // Admin_Repo.deleteBuyerAndSeller(scanner, companyId);
    }
}
