package Services;

import Constants.Message;
import Controller.CompanyController;
import Controller.MenuSelectorController;
import Controller.SellerController;
import Modal.Company;
import Repository.Buyer_Repo;
import Repository.Register_Buyer_Repo;
import Util.Validations;

import java.util.Scanner;
import javax.swing.MenuSelectionManager;

public class Buyer_Service {
    public static void handleBuyerLogin(Scanner inputscanner, String role, Company company) {
        while (true) {
            System.out.println(Message.BUYER_LOGIN_MENU);
            System.out.println(Message.BACK_AND_EXIT_FRAME);
            System.out.print(Message.SELECT_OPTION);

            String input = inputscanner.nextLine().trim();

            switch (input.toUpperCase()) {
                case "A":
                     // Go back to role selection
                    CompanyController.handleLoginRoles(company, inputscanner); 
                    return;
                case "B":
                    System.out.println(Message.EXIT_MESSAGE);
                    System.exit(0);
                    break;
                default:
                    try {
                        int option = Integer.parseInt(input);
                        switch (option) {
                            case 1:
                                if (LOGIN(inputscanner)) {
                                    System.out.println(Message.LOGIN_SUCCESS);
                                    showBuyerMenu(inputscanner, role, company);
                                }else{
                                    System.out.println(Message.LOGIN_FAILED);
                                }
                                break;
                            case 2:
                                if (REGISTER_BUYER(inputscanner, role, company)) {
                                    System.out.println(Message.BUYER_ADDED);
                                    LOGIN(inputscanner);
                                } else {
                                    System.out.println(Message.REGISTER_FAILED);
                                }
                                break;
                            default:
                                System.out.println(Message.INVALID_INPUT);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(Message.INVALID_INPUT);
                    }
            }
        }
    }

    public static void showBuyerMenu(Scanner inputscanner, String role, Company company) {

        while (true) {
            System.out.println(Message.BUYER_MENU);
            System.out.println(Message.BACK_LOGOUT_EXIT_FRAME);
            System.out.print(Message.SELECT_OPTION);

             String input = inputscanner.nextLine().trim();

        switch (input.toUpperCase()) {
            case "A":
                // Go back to login menu
                handleBuyerLogin(inputscanner, role, company);
                return;
            case "B" : 
                CompanyController.startCompanySelection(inputscanner);
            case "C":
                System.out.println(Message.EXIT_MESSAGE);
                System.exit(0);
                break;
            default:
                try {
                    int option = Integer.parseInt(input);
                    switch (option) {
                        case 1:
                            Category_Service.Category(inputscanner);
                            break;
                        case 2:
                            System.out.println("Adding to Wishlist...");
                            break;
                        case 3:
                            System.out.println("Adding to Cart...");
                            break;
                        case 4:
                            System.out.println("Checkout...");
                            break;
                        case 5:
                            System.out.println("Generating Invoice...");
                            break;
                        case 6:
                            System.out.println("Checking Order Status...");
                            break;
                        default:
                            System.out.println(Message.INVALID_INPUT);
                    }
                } catch (NumberFormatException e) {
                    System.out.println(Message.INVALID_INPUT);
                }
           }
        }
    }

    public static boolean LOGIN(Scanner inputscanner) {
       System.out.print(Message.LOGIN_CREDENTIAL);
    String username = inputscanner.nextLine();

    String password;
    while (true) {
        System.out.print(Message.PASSWORD);
        password = inputscanner.nextLine();

        if (Validations.isValidPassword(password)) {
            break;
        } else {
            System.out.println(Message.PASSWORD_INVALID);
        }
    }

        Buyer_Repo buyerRepo = new Buyer_Repo();
        return buyerRepo.checkBuyer(username, password);
    }

    public static boolean REGISTER_BUYER(Scanner inputscanner, String role, Company company) {
        Register_Buyer_Repo registerRepo = new Register_Buyer_Repo();
        return registerRepo.REGISTER(inputscanner, role, company);
    }

    public static void Browse_Products() {
        // Browse products logic
    }
}
