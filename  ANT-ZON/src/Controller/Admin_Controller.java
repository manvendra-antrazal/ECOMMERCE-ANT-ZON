package Controller;

import Constants.Message;
import Modal.Company;
import Services.Admin_Service;
import Util.PrintUtil;
import java.util.Scanner;



public class Admin_Controller {
    public static void handleAdminFlow(Scanner scanner, Company company) {
        try {
            Admin_Service.handleAdminLogin(scanner, "ADMIN", company);
        } catch (Exception e) {
            System.out.println(Message.ERROR_ADMIN_LOGIN + " " + e.getMessage());
              PrintUtil.printMessageWithException(Message.ERROR_ADMIN_LOGIN, e);
        }
    }

    public static void showAdminMenu(Scanner scanner, Company company, int adminId){
        while (true) {
            try {
                PrintUtil.printMessage(Message.ADMIN_MENU_FRAME);
                PrintUtil.printMessage(Message.SELECT_OPTION);

                String input = scanner.nextLine().trim().toUpperCase();

                switch (input) {
                    case "1":
                        try {
                            Admin_Service.viewCompanyName(company);
                        } catch (Exception e) {
                              PrintUtil.printMessageWithException(Message.ERROR_VIEW_COMPANY, e);
                        }
                        break;
                    case "2":
                        try {
                            Admin_Service.viewTotalRevenue(scanner, company, company.getCompany_Id());
                        } catch (Exception e) {
                             PrintUtil.printMessageWithException(Message.ERROR_VIEW_REVENUE, e);
                        }
                        break;
                    case "3":
                        try {
                            Admin_Service.viewMostLikedProducts(scanner, company, company.getCompany_Id());
                        } catch (Exception e) {
                                                            PrintUtil.printMessageWithException(Message.ERROR_VIEW_MOST_LIKED, e);

                        }
                        break;
                    case "4":
                        try {
                            Admin_Service.viewBestSellerProducts(scanner, company, company.getCompany_Id());
                        } catch (Exception e) {
                             PrintUtil.printMessageWithException(Message.ERROR_VIEW_BEST_SELLER, e);
                        }
                        break;
                    
                    case "A":
                        CompanyController.startCompanySelection(scanner);
                        return;
                    case "B":
                        PrintUtil.printMessage(Message.EXIT_MESSAGE);
                        System.exit(0);
                        break;
                    default:
                        PrintUtil.printMessage(Message.INVALID_INPUT);
                }
            } catch (Exception e) {
                PrintUtil.printMessageWithException(Message.UNEXPECTED_ERROR, e);
            }
        }
    }
}
