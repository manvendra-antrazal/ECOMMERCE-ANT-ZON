package Services;

import Constants.Message;
import Controller.CompanyController;
import Controller.ProductController;
import Modal.Company;
import Repository.Register_Seller_Repo;
import Repository.Seller_Repo;
import java.util.Scanner;

public class Seller_Service {

    public static void handleSellerLogin(Scanner inputScanner, String role, Company company) {
        while (true) {
            try {
                System.out.println(Message.SELLER_LOGIN_MENU);
                System.out.print(Message.SELECT_OPTION);
                String input = inputScanner.nextLine().trim();

                switch (input.toUpperCase()) {
                    case "A":
                        CompanyController.handleLoginRoles(company, inputScanner);
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
                                    int sellerId = loginSeller(inputScanner);
                                    if (sellerId != -1) {
                                        System.out.println(Message.LOGIN_SUCCESS);
                                        ProductController.showSellerMenu(inputScanner, role, company, sellerId);
                                    } else {
                                        System.out.println(Message.LOGIN_FAILED);
                                    }
                                    break;
                                case 2:
                                    boolean isRegistered = registerSeller(inputScanner, role, company);
                                    if (isRegistered) {
                                        System.out.println(Message.SELLER_ADDED);
                                    } else {
                                        System.out.println(Message.REGISTER_FAILED);
                                    }
                                    break;
                                default:
                                    System.out.println(Message.INVALID_OPTION);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println(Message.INVALID_INPUT);
                        }
                }
            } catch (Exception e) {
                System.out.println(Message.INTERNAL_ERROR);
                e.printStackTrace();
            }
        }
    }

    private static int loginSeller(Scanner inputScanner) {
        try {
            System.out.print(Message.LOGIN_CREDENTIAL);
            String username = inputScanner.nextLine().trim();

            System.out.print(Message.ENTER_PASSWORD);
            String password = inputScanner.nextLine().trim();

            System.out.println(Message.LOGIN_CREDENTIAL_LOWER);

            Seller_Repo sellerRepo = new Seller_Repo();
            return sellerRepo.getSellerId(username, password);
        } catch (Exception e) {
            System.out.println(Message.LOGIN_ERROR);
            throw new RuntimeException(Message.LOGIN_FAILED_DB_ERROR, e);
        }
    }

    public static boolean registerSeller(Scanner inputScanner, String role, Company company) {
        try {
            Register_Seller_Repo registerRepo = new Register_Seller_Repo();
            return registerRepo.REGISTER_SELLER(inputScanner, role, company);
        } catch (Exception e) {
            System.out.println(Message.REGISTRATION_FAILED);
            throw new RuntimeException(Message.SELLER_REGISTER_FAILED, e);
        }
    }
}
