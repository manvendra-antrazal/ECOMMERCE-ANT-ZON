package Controller;

import Constants.Message;
import Modal.Company;
import Services.AdminService;
import java.util.Scanner;

public class AdminController {



    public static void handleAdminFlow(Scanner inputScanner, Company company) {
        while (true) {
            System.out.println(Message.ADMIN_MENU_FRAME);
            System.out.print(Message.SELECT_OPTION);

            String input = inputScanner.nextLine().trim().toUpperCase();

            switch (input) {
                case "1":
                    System.out.println("-> Current Company: " + company.getCompany_Name());
                    break;

                case "2":
                    AdminService.viewTotalRevenue(company.getCompany_Id());
                    break;

                case "3":
                    AdminService.viewMostLikedProducts(company.getCompany_Id());
                    break;

                case "4":
                    AdminService.viewBestSellerProducts(company.getCompany_Id());
                    break;

                case "5":
                    AdminService.deleteBuyerAndSeller(inputScanner, company.getCompany_Id());
                    break;

                case "A":
                    CompanyController.startCompanySelection(inputScanner);
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
