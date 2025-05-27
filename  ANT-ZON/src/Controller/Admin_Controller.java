package Controller;

import Constants.Message;
import Modal.Company;
import Services.Admin_Service;
import java.util.Scanner;

public class Admin_Controller {

    public static void handleAdminFlow(Scanner scanner, Company company) {
        Admin_Service.handleAdminLogin(scanner, "ADMIN", company);
    }

    public static void showAdminMenu(Scanner scanner, Company company, int adminId){

        while (true) {
            System.out.println(Message.ADMIN_MENU_FRAME);
            System.out.print(Message.SELECT_OPTION);

            String input = scanner.nextLine().trim().toUpperCase();

            switch (input) {
                case "1":
                    Admin_Service.viewCompanyName(company);
                    break;
                case "2":
                    Admin_Service.viewTotalRevenue(scanner, company, company.getCompany_Id());
                    break;
                case "3":
                    Admin_Service.viewMostLikedProducts(scanner, company, company.getCompany_Id());
                    break;
                case "4":
                    Admin_Service.viewBestSellerProducts(scanner, company, company.getCompany_Id());
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
        }
    }
}
