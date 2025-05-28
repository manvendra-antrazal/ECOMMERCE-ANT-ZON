package Controller;

import Constants.Message;
import Modal.Company;
import Services.Product_Service;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ProductController {

    public static void showSellerMenu(Scanner inputScanner, String role, Company company, int sellerId) {
        while (true) {
            try {
                System.out.println(Message.SELLER_MENU);
                System.out.print(Message.SELECT_OPTION);

                String input = inputScanner.nextLine().trim().toUpperCase();

                switch (input) {
                    case "A":
                        CompanyController.handleLoginRoles(company, inputScanner);
                        return;

                    case "B":
                        CompanyController.startCompanySelection(inputScanner);
                        return;

                    case "C":
                        System.out.println(Message.EXIT_MESSAGE);
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

                    // case "5":
                    //     Product_Service.viewProductStats(sellerId);
                    //     break;

                    default:
                        System.out.println(Message.INVALID_INPUT);
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println(Message.INVALID_INPUT);
                inputScanner.nextLine(); // Clear the invalid input
            } catch (Exception e) {
                System.out.println(Message.UNEXPECTED_ERROR + e.getMessage());
            }
        }
    }
}
