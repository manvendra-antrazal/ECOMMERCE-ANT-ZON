package Services;

import Constants.Message;
import Controller.BuyerController;
import Controller.CompanyController;
import Modal.Company;
import Repository.Buyer_Repo;
import Repository.Register_Buyer_Repo;
import Util.Validations;
import java.util.Scanner;

public class Buyer_Service {
    public static void handleBuyerLogin(Scanner inputscanner, String role, Company company) {
        while (true) {
            System.out.println(Message.BUYER_LOGIN_MENU);
            System.out.println(Message.BACK_AND_EXIT_FRAME);
            System.out.print(Message.SELECT_OPTION);

            String input = inputscanner.nextLine().trim();

            switch (input.toUpperCase()) {
                case "A":
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
                                int buyerId = LOGIN(inputscanner);
                                if (buyerId > 0) {
                                    System.out.println(Message.LOGIN_SUCCESS);
                                    BuyerController.showBuyerMenu(inputscanner, role, company, buyerId);
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

    

    public static int LOGIN(Scanner inputscanner) {
    System.out.print(Message.LOGIN_CREDENTIAL);
    String username = inputscanner.nextLine();

    String password;
    while (true) {
        System.out.print(Message.PASSWORD);
        password = inputscanner.nextLine();

        if (Validations.isValidPassword(password)) {
            break;
        } else {
            System.out.println(Message.WRONG_PASSWORD);
        }
    }

        Buyer_Repo buyerRepo = new Buyer_Repo();
        int buyerId = buyerRepo.getBuyerId(username, password);

    if (buyerId > 0) {
        System.out.println(Message.LOGIN_SUCCESS);
        return buyerId;
    } else {
        System.out.println(Message.LOGIN_FAILED);
        return -1;
    }
    }

    public static boolean REGISTER_BUYER(Scanner inputscanner, String role, Company company) {
        Register_Buyer_Repo registerRepo = new Register_Buyer_Repo();
        return registerRepo.REGISTER(inputscanner, role, company);
    }
}
