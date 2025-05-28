package Controller;

import Constants.Message;
import Modal.Company;
import Services.Admin_Service;
import java.util.Scanner;

public class Admin_Controller {

    public static void handleAdminFlow(Scanner scanner, Company company) {
        try {
            Admin_Service.handleAdminLogin(scanner, "ADMIN", company);
        } catch (Exception e) {
            System.out.println(Message.ERROR_ADMIN_LOGIN + " " + e.getMessage());
            // Optionally re-throw or handle further
        }
    }

    public static void showAdminMenu(Scanner scanner, Company company, int adminId){
        while (true) {
            try {
                System.out.println(Message.ADMIN_MENU_FRAME);
                System.out.print(Message.SELECT_OPTION);

                String input = scanner.nextLine().trim().toUpperCase();

                switch (input) {
                    case "1":
                        try {
                            Admin_Service.viewCompanyName(company);
                        } catch (Exception e) {
                            System.out.println(Message.ERROR_VIEW_COMPANY + " " + e.getMessage());
                        }
                        break;
                    case "2":
                        try {
                            Admin_Service.viewTotalRevenue(scanner, company, company.getCompany_Id());
                        } catch (Exception e) {
                            System.out.println(Message.ERROR_VIEW_REVENUE + " " + e.getMessage());
                        }
                        break;
                    case "3":
                        try {
                            Admin_Service.viewMostLikedProducts(scanner, company, company.getCompany_Id());
                        } catch (Exception e) {
                            System.out.println(Message.ERROR_VIEW_MOST_LIKED + " " + e.getMessage());
                        }
                        break;
                    case "4":
                        try {
                            Admin_Service.viewBestSellerProducts(scanner, company, company.getCompany_Id());
                        } catch (Exception e) {
                            System.out.println(Message.ERROR_VIEW_BEST_SELLER + " " + e.getMessage());
                        }
                        break;

                    // case "5":
                    //     Admin_Service.deleteBuyerAndSeller(scanner, company.getCompany_Id());
                    //     break;
                    
                    case "A":
                        CompanyController.startCompanySelection(scanner);
                        return;
                    case "B":
                        System.out.println(Message.EXIT_MESSAGE);
                        System.exit(0);
                        break;
                    default:
                        System.out.println(Message.INVALID_INPUT);
                }
            } catch (Exception e) {
                System.out.println(Message.UNEXPECTED_ERROR + " " + e.getMessage());
            }
        }
    }
}
