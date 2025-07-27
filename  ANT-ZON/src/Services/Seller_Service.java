package Services;

import Constants.Message;
import Controller.CompanyController;
import Controller.ProductController;
import Modal.Company;
import Repository.Register_Seller_Repo;
import Repository.Seller_Repo;
import Util.PrintUtil;
import java.util.Scanner;

public class Seller_Service {

    public static void handleSellerLogin(Scanner inputScanner, String role, Company company) {
        while (true) {
            try {
                PrintUtil.printMessage(Message.SELLER_LOGIN_MENU);
                PrintUtil.printMessage(Message.SELECT_OPTION);

                String input = inputScanner.nextLine().trim();

                switch (input.toUpperCase()) {
                    case "A":
                        CompanyController.handleLoginRoles(company, inputScanner);
                        return;
                    case "B":
                        PrintUtil.printMessage(Message.EXIT_MESSAGE);
                        System.exit(0);
                        break;
                    default:
                        try {
                            int option = Integer.parseInt(input);
                            switch (option) {
                                case 1:
                                    int sellerId = loginSeller(inputScanner);
                                    if (sellerId != -1) {
                                        PrintUtil.printMessage(Message.LOGIN_SUCCESS);
                                        ProductController.showSellerMenu(inputScanner, role, company, sellerId);
                                    } else {
                                        PrintUtil.printMessage(Message.LOGIN_FAILED);
                                    }
                                    break;
                                case 2:
                                    boolean isRegistered = registerSeller(inputScanner, role, company);
                                    if (isRegistered) {
                                        PrintUtil.printMessage(Message.SELLER_ADDED);
                                    } else {
                                        PrintUtil.printMessage(Message.REGISTER_FAILED);
                                    }
                                    break;
                                default:
                                    PrintUtil.printMessage(Message.INVALID_OPTION);
                            }
                        } catch (NumberFormatException e) {
                            PrintUtil.printMessage(Message.INVALID_INPUT);
                        }
                }
            } catch (Exception e) {
                PrintUtil.printMessageWithException(Message.INTERNAL_ERROR, e);
            }
        }
    }

    private static int loginSeller(Scanner inputScanner) {
        try {
            PrintUtil.printMessage(Message.LOGIN_CREDENTIAL);
            String username = inputScanner.nextLine().trim();

            PrintUtil.printMessage(Message.ENTER_PASSWORD);
            String password = inputScanner.nextLine().trim();

            PrintUtil.printMessage(Message.LOGIN_CREDENTIAL_LOWER);

            Seller_Repo sellerRepo = new Seller_Repo();
            return sellerRepo.getSellerId(username, password);
        } catch (Exception e) {
            PrintUtil.printMessageWithException(Message.LOGIN_ERROR, e);
            throw new RuntimeException(Message.LOGIN_FAILED_DB_ERROR, e);
        }
    }

    public static boolean registerSeller(Scanner inputScanner, String role, Company company) {
        try {
            Register_Seller_Repo registerRepo = new Register_Seller_Repo();
            return registerRepo.REGISTER_SELLER(inputScanner, role, company);
        } catch (Exception e) {
            PrintUtil.printMessageWithException(Message.REGISTRATION_FAILED, e);
            throw new RuntimeException(Message.SELLER_REGISTER_FAILED, e);
        }
    }
}
