package Controller;

import Constants.Message;
import Modal.Company;
import Services.Product_Service;
import Util.PrintUtil;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ProductController {

    public static void showSellerMenu(Scanner inputScanner, String role, Company company, int sellerId) {
        while (true) {
            try {
                PrintUtil.printMessage(Message.SELLER_MENU);
                PrintUtil.printMessage(Message.SELECT_OPTION);

                String input = inputScanner.nextLine().trim().toUpperCase();

                switch (input) {
                    case "A":
                        CompanyController.handleLoginRoles(company, inputScanner);
                        return;

                    case "B":
                        CompanyController.startCompanySelection(inputScanner);
                        return;

                    case "C":
                        PrintUtil.printMessage(Message.EXIT_MESSAGE);
                        System.exit(0);
                        break;

                    case "1":
                        Product_Service.viewAllProducts(inputScanner, sellerId);
                        break;

                    case "2":
                        Product_Service.addProduct(inputScanner, sellerId, company.getCompany_Id());
                        break;

                    case "3":
                        Product_Service.updateProductInfo(inputScanner, role, company, sellerId);
                        break;

                    case "4":
                        Product_Service.deleteProduct(inputScanner, sellerId);
                        break;

                    default:
                        PrintUtil.printMessage(Message.INVALID_INPUT);
                        break;
                }

            } catch (InputMismatchException e) {
                PrintUtil.printMessage(Message.INVALID_INPUT);
                inputScanner.nextLine(); // Clear invalid input
            } catch (Exception e) {
                PrintUtil.printMessageWithException(Message.UNEXPECTED_ERROR, e);
            }
        }
    }
}
