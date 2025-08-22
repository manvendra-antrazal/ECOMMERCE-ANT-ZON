package View;

import Constants.Message;
import Controller.AdminController;
import Modal.Company;
import Modal.Product;
import java.util.List;
import java.util.Scanner;

public class AdminView {

    private static final Scanner SC = new Scanner(System.in);
    private static final AdminController adminController = AdminController.getInstance();

    public static void loginOrRegister(Company company) {
        while (true) {
            printAuthMenu();
            System.out.print(Message.SELECT_OPTION);
            String option = SC.nextLine().trim().toUpperCase();

            switch (option) {
                case "1":
                    handleLogin(company);
                    break;
                case "2":
                    handleRegister(company);
                    break;
                case "B":
                    return; 
                default:
                    println(Message.INVALID_OPTION);
            }
        }
    }

    private static void handleLogin(Company company) {
        System.out.println(Message.LOGIN_CREDENTIAL);
        String username = SC.nextLine().trim();
        String password = SC.nextLine().trim();

        try {
            Integer adminId = adminController.loginAdmin(username, password, company.getCompany_Id());
            if (adminId != null && adminId > 0) {
                println("Login successful.");
                adminMenu(company, adminId);
            } else {
                println(Message.LOGIN_FAILED);
            }
        } catch (Exception e) {
            println("Error during login: " + safe(e.getMessage()));
        }
    }

    private static void handleRegister(Company company) {
        System.out.print("Choose admin username: ");
        String username = SC.nextLine().trim();
        System.out.print("Choose admin password: ");
        String password = SC.nextLine().trim();

        try {
            boolean ok = adminController.registerAdmin(username, password, company.getCompany_Id());
            if (ok) {
                println(Message.REGISTER);
            } else {
                println(Message.FAILED_TO_REGISTER);
            }
        } catch (IllegalArgumentException e) {
            println("Invalid input: " + safe(e.getMessage()));
        } catch (Exception e) {
            println("Unexpected error: " + safe(e.getMessage()));
        }
    }

    public static void adminMenu(Company company, int adminId) {
        while (true) {
            printAdminMenu(company);
            System.out.print("Enter option: ");
            String option = SC.nextLine().trim().toUpperCase();

            try {
                switch (option) {
                    case "1":
                        showCompanyInfo(company);
                        break;
                    case "2":
                        double revenue = adminController.getTotalRevenue(company.getCompany_Id());
                        showTotalRevenue(revenue);
                        break;
                    case "3":
                        List<Product> liked = adminController.getMostLikedProducts(company.getCompany_Id());
                        showMostLikedProducts(liked);
                        break;
                    case "4":
                        List<Product> best = adminController.getBestSellerProducts(company.getCompany_Id());
                        showBestSellerProducts(best);
                        break;
                    case "B":
                        return; 
                    default:
                        println(Message.INVALID_OPTION);
                }
            } catch (Exception e) {
                println("Operation failed: " + safe(e.getMessage()));
            }
        }
    }

    private static void showCompanyInfo(Company company) {
        System.out.println("\n=== Company Information ===");
        System.out.println("Company Name: " + safe(company.getCompany_Name()));
        System.out.println("Company ID  : " + company.getCompany_Id());
        System.out.println();
    }

    private static void showTotalRevenue(double revenue) {
        System.out.printf("\nTotal Revenue: %.2f%n%n", revenue);
    }

    private static void showMostLikedProducts(List<Product> liked) {
        System.out.println("\n=== Most Liked Products ===");
        if (liked == null || liked.isEmpty()) {
            System.out.println("No data available.\n");
            return;
        }
        System.out.printf("%-8s %-30s %-8s%n", "ID", "Name", "Likes");
        System.out.println("-----------------------------------------------");
        for (Product p : liked) {
            System.out.printf("%-8d %-30s %-8d%n",
                    p.getProduct_Id(),
                    safe(p.getProduct_Name()),
                    p.getLikes());
        }
        System.out.println();
    }

    private static void showBestSellerProducts(List<Product> best) {
        System.out.println("\n=== Best Seller Products ===");
        if (best == null || best.isEmpty()) {
            System.out.println("No data available.\n");
            return;
        }
        System.out.printf("%-8s %-30s %-8s%n", "ID", "Name", "Sold");
        System.out.println("-----------------------------------------------");
        for (Product p : best) {
            System.out.printf("%-8d %-30s %-8d%n",
                    p.getProduct_Id(),
                    safe(p.getProduct_Name()),
                    p.getProduct_Quantity());
        }
        System.out.println();
    }


    private static void printAuthMenu() {
        System.out.println(Message.LOGIN_CREDENTIAL);
    }

    private static void printAdminMenu(Company company) {
        System.out.println(Message.ADMIN_MENU_FRAME);
    }

    private static void println(String msg) {
        System.out.println(msg);
    }

    private static String safe(String s) {
        return s == null ? "N/A" : s;
    }

    public static void showCompanyName(Company company) {
        if (company == null) {
            System.out.println(Message.EXISTING_COMAPNY);
            System.out.println(Message.EXISTING_COMPANY_SEPRATION);
            return;
        }
    
        String name = company.getCompany_Name();
        int id = company.getCompany_Id();
    
        System.out.println("\n=== Company ===");
        System.out.println("Name: " + (name == null || name.isBlank() ? "N/A" : name));
        System.out.println("ID  : " + (id > 0 ? id : 0));
        System.out.println();
    }
    
}
