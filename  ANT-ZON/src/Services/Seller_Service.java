package Services;

import Constants.Message;
import Controller.CompanyController;
import Controller.ProductController;
import Modal.Company;
import Repository.Register_Seller_Repo;
import Repository.Seller_Repo;
import java.util.Scanner;

public class Seller_Service {

    // Handle buyer login as well as Register new user 
    public static void handleSellerLogin(Scanner inputscanner, String role, Company company) {

     while (true) {
        System.out.println(Message.SELLER_LOGIN_MENU);
        // System.out.println(Message.BACK_AND_EXIT_FRAME); 
        System.out.print(Message.SELECT_OPTION);

        String input = inputscanner.nextLine().trim();

        switch (input.toUpperCase()) {
            case "A":
                CompanyController.handleLoginRoles(company, inputscanner); // Go back to role selection
                return;

            case "B":
                System.out.println(Message.EXIT_MESSAGE);
                System.exit(0);
                break;

            default:
                try {
                    int type = Integer.parseInt(input);
                    switch (type) {
                        case 1:
                            int sellerId = LOGIN(inputscanner);
                            if (sellerId > 0) {
                                ProductController.showSellerMenu(inputscanner, role, company, sellerId);
                            } 
                            break;
                        case 2:
                            if (REGISTER_SELLER(inputscanner, role, company)) {
                                System.out.println(Message.SELLER_ADDED);
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

     

    // LOGIN as Seller
    public static int LOGIN(Scanner inputscanner) {
        
        System.out.print(Message.LOGIN_CREDENTIAL);
        String username = inputscanner.nextLine();
        System.out.print(Message.ENTER_PASSWORD);
        String password = inputscanner.nextLine();
        System.out.println(Message.LOGIN_CREDENTIAL_LOWER);

        Seller_Repo sellerRepo = new Seller_Repo();
        int sellerId = sellerRepo.getSellerId(username, password);

    if (sellerId > 0) {
        System.out.println(Message.LOGIN_SUCCESS);
        return sellerId;
    } else {
        System.out.println(Message.LOGIN_FAILED);
        return -1;
    }
    }

    // Register new seller 
    public static boolean  REGISTER_SELLER(Scanner inputscanner, String role,  Company company) {
        Register_Seller_Repo register_Seller_Repo = new Register_Seller_Repo();
        boolean isRegister = register_Seller_Repo.REGISTER_SELLER(inputscanner, role, company);

            if (isRegister) {
                return true;
            } else {
                return false;
            }       
    }
}