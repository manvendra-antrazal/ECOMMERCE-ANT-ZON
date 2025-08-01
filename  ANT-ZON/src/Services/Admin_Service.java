package Services;

import Constants.Message;
import Controller.Admin_Controller;
import Controller.CompanyController;
import Modal.Company;
import Modal.Product;
import Repository.Admin_Repo;
import Util.PrintUtil;
import static Util.Validations.isValidPassword;
import java.util.List;
import java.util.Scanner;

public class Admin_Service {

    public static void handleAdminLogin(Scanner inputScanner, String role, Company company) {
        while (true) {
            try {
                PrintUtil.printMessage(Message.ADMIN_LOGIN_MENU);
                PrintUtil.printMessage(Message.SELECT_OPTION);
                String input = inputScanner.nextLine().trim().toUpperCase();

                switch (input) {
                    case "A":
                        CompanyController.handleLoginRoles(company, inputScanner);
                        return;
                    case "B":
                        PrintUtil.printMessage(Message.EXIT_MESSAGE);
                        System.exit(0);
                        break;
                    case "1":
                        int adminId = loginAdmin(inputScanner, company);
                        if (adminId != -1) {
                            PrintUtil.printMessage(Message.ADMIN_LOGIN_SUCCESS);
                            Admin_Controller.showAdminMenu(inputScanner, company, adminId);
                        } else {
                            PrintUtil.printMessage(Message.ADMIN_LOGIN_FAILED);
                        }
                        break;
                    case "2":
                        registerAdmin(inputScanner, role, company);
                        break;
                    default:
                        PrintUtil.printMessage(Message.INVALID_OPTION);
                        break;
                }
            } catch (Exception e) {
                PrintUtil.printMessageWithException(Message.UNEXPECTED_ERROR, e);
                throw new RuntimeException(Message.UNEXPECTED_ERROR + e.getMessage(), e);
            }
        }
    }

    private static int loginAdmin(Scanner inputScanner, Company company) {
        try {
            PrintUtil.printMessage(Message.LOGIN_CREDENTIAL);
            String username = inputScanner.nextLine().trim();
            
            PrintUtil.printMessage(Message.PASSWORD);
            
            String password = inputScanner.nextLine().trim();

            PrintUtil.printMessage(Message.LOGIN_CREDENTIAL_LOWER);
            return Admin_Repo.validateAdminLogin(username, password, company.getCompany_Id());
        } catch (Exception e) {
            PrintUtil.printMessageWithException(Message.LOGIN_FAILED, e);
            throw new RuntimeException(Message.LOGIN_FAILED + e.getMessage(), e);
        }
    }

    private static void registerAdmin(Scanner inputScanner, String role, Company company) {
        try {
            PrintUtil.printMessage(Message.ENTER_NEW_ADMIN_USERNAME);
            String username = inputScanner.nextLine().trim();

            String password;
            while (true) {
                PrintUtil.printMessage(Message.ENTER_NEW_ADMIN_PASSWORD);
                password = inputScanner.nextLine().trim();

                if (isValidPassword(password)) {
                    break;
                } else {
                    PrintUtil.printMessage(Message.PASSWORD_INVALID);
                }
            }

            boolean success = Admin_Repo.registerNewAdmin(username, password, company.getCompany_Id());

            if (success) {
                PrintUtil.printMessage(Message.ADMIN_REGISTERED_SUCCESS);
                loginAdmin(inputScanner, company);
            } else {
                PrintUtil.printMessage(Message.ADMIN_REGISTERED_FAILED);
            }
        } catch (Exception e) {
            PrintUtil.printMessageWithException(Message.REGISTRATION_FAILED, e);
            throw new RuntimeException(Message.REGISTRATION_FAILED + e.getMessage(), e);
        }
    }

    public static void viewCompanyName(Company company) {
        try {
            PrintUtil.printMessage(Message.CURRENT_COMPANY_UPPER);
            System.out.printf("║ Current Company: %-30s║\n", company.getCompany_Name());
            PrintUtil.printMessage(Message.CURRENT_COMPANY_BOTTOM);
        } catch (Exception e) {
            PrintUtil.printMessageWithException(Message.COMPANY_VIEW_ERROR, e);
            throw new RuntimeException(Message.COMPANY_VIEW_ERROR + e.getMessage(), e);
        }
    }

    public static void viewTotalRevenue(Scanner scanner, Company company, int companyId) {
        try {
            double revenue = Admin_Repo.fetchTotalRevenue(companyId);
            PrintUtil.printMessage(Message.TOTAL_REVENUE_FRAME_TOP);
            System.out.printf("║         %-34.2f ║\n", revenue);
            PrintUtil.printMessage(Message.TOTAL_REVENUE_FRAME_BOTTOM);

            while (true) {
                System.out.print(Message.SELECT_OPTION);
                String choice = scanner.nextLine().trim().toUpperCase();
                switch (choice) {
                    case "A":
                        Admin_Controller.showAdminMenu(scanner, company, 0);
                        return;
                    case "B":
                        PrintUtil.printMessage(Message.EXIT_MESSAGE);
                        System.exit(0);
                    default:
                        PrintUtil.printMessage(Message.INVALID_OPTION);
                        break;
                }
            }
        } catch (Exception e) {
            PrintUtil.printMessageWithException(Message.REVENUE_VIEW_ERROR, e);
            throw new RuntimeException(Message.REVENUE_VIEW_ERROR + e.getMessage(), e);
        }
    }

    public static void viewMostLikedProducts(Scanner scanner, Company company, int companyId) {
        try {
            List<Product> likedProducts = Admin_Repo.fetchMostLikedProducts(companyId);

            PrintUtil.printMessage(Message.MOST_LIKED_FRAME_TOP);
            System.out.printf("║ %-5s ║ %-25s ║\n", "ID", "Product Name");
            PrintUtil.printMessage(Message.MOST_LIKED_FRAME_DIVIDER);

            if (likedProducts.isEmpty()) {
                PrintUtil.printMessage(Message.NO_LIKED_PRODUCT);
            } else {
                for (Product product : likedProducts) {
                    System.out.printf("║ %-5d ║ %-25s ║\n",
                            product.getProduct_Id(),
                            product.getProduct_Name());
                }
            }

            PrintUtil.printMessage(Message.MOST_LIKED_FRAME_BOTTOM);

            while (true) {
                PrintUtil.printMessage(Message.SELECT_OPTION);
                String choice = scanner.nextLine().trim().toUpperCase();

                switch (choice) {
                    case "A":
                        Admin_Controller.showAdminMenu(scanner, company, 0);
                        return;
                    case "B":
                        PrintUtil.printMessage(Message.EXIT_MESSAGE);
                        System.exit(0);
                    default:
                        PrintUtil.printMessage(Message.INVALID_OPTION);
                        break;
                }
            }
        } catch (Exception e) {
            PrintUtil.printMessageWithException(Message.MOST_LIKED_VIEW_ERROR, e);
            throw new RuntimeException(Message.MOST_LIKED_VIEW_ERROR + e.getMessage(), e);
        }
    }

    public static void viewBestSellerProducts(Scanner scanner, Company company, int companyId) {
        try {
            List<Product> bestSellers = Admin_Repo.fetchBestSellerProducts(companyId);

            PrintUtil.printMessage(Message.BEST_SELLERS_FRAME_TOP);
            System.out.printf("║ %-4s║ %-33s║ %-19s║\n", "ID", "Product Name", "Total Sold");
            PrintUtil.printMessage(Message.BEST_SELLERS_FRAME_MIDDLE);

            if (bestSellers.isEmpty()) {
                PrintUtil.printMessage(Message.NO_BEST_SELLERS);
            } else {
                for (Product product : bestSellers) {
                    System.out.printf("║ %-4d║ %-33s║ %-19d║\n",
                            product.getProduct_Id(),
                            product.getProduct_Name(),
                            product.getProduct_Quantity());
                }
            }

            PrintUtil.printMessage(Message.BEST_SELLERS_FRAME_BOTTOM);

            while (true) {
                PrintUtil.printMessage(Message.SELECT_OPTION);
                String choice = scanner.nextLine().trim().toUpperCase();

                switch (choice) {
                    case "A":
                        Admin_Controller.showAdminMenu(scanner, company, 0);
                        return;
                    case "B":
                        PrintUtil.printMessage(Message.EXIT_MESSAGE);
                        System.exit(0);
                    default:
                        PrintUtil.printMessage(Message.INVALID_OPTION);
                        break;
                }
            }
        } catch (Exception e) {
            PrintUtil.printMessageWithException(Message.BEST_SELLERS_VIEW_ERROR, e);
            throw new RuntimeException(Message.BEST_SELLERS_VIEW_ERROR + e.getMessage(), e);
        }
    }

    public static void deleteBuyerAndSeller(Scanner scanner, int companyId) {
        try {
        } catch (Exception e) {
            PrintUtil.printMessageWithException(Message.DELETE_USER_ERROR, e);
            throw new RuntimeException(Message.DELETE_USER_ERROR + e.getMessage(), e);
        }
    }
}
