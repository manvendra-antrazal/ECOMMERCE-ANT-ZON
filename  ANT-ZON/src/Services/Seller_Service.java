package Services;

import Constants.Message;
import Controller.CompanyController;
import Controller.MenuSelectorController;
import Controller.SellerController;
import Modal.Company;
import Repository.Register_Seller_Repo;
import Repository.Seller_Repo;
import java.util.Scanner;

public class Seller_Service {

    // Handle buyer login as well as Register new user 
    public static void handleSellerLogin(Scanner inputscanner, String role, Company company) {

     while (true) {
        System.out.println(Message.SELLER_LOGIN_MENU);
        System.out.println(Message.BACK_AND_EXIT_FRAME); // A. Back, B. Exit
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
                            if (LOGIN(inputscanner)) {
                                System.out.println(Message.LOGIN_SUCCESS);
                                showSellerMenu(inputscanner, role, company);
                            } else{
                                System.out.println(Message.LOGIN_FAILED);
                            }
                            break;
                        case 2:
                            if (REGISTER_SELLER(inputscanner, role, company)) {
                                System.out.println(Message.SELLER_ADDED);
                                System.out.println(Message.LOGIN_CREDENTIAL);
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

    public static void showSellerMenu(Scanner inputscanner, String role, Company company){
        while (true) {
            System.out.println(Message.SELLER_MENU);
            System.out.println(Message.BACK_LOGOUT_EXIT_FRAME);
            System.out.print(Message.SELECT_OPTION);
             int option;
        try {
            option = inputscanner.nextInt();
            inputscanner.nextLine();
        } catch (Exception e) {
            System.out.println(Message.INVALID_INPUT);
            inputscanner.nextLine(); // Clear invalid input
            continue;
        }

        switch (option) {
            case 1:
                System.out.println("Displaying all products...");
                // call SellerService.viewAllProducts();
                break;
            case 2:
                System.out.println("Adding a new product...");
                // call SellerService.addProduct(inputscanner);
                break;
            case 3:
                System.out.println("Updating product information...");
                // call SellerService.updateProductInfo(inputscanner);
                break;
            case 4:
                System.out.println("Updating product quantity...");
                // call SellerService.updateQuantity(inputscanner);
                break;
            case 5:
                System.out.println("Deleting product...");
                // call SellerService.deleteProduct(inputscanner);
                break;
            case 6:
                System.out.println("Viewing seller stats...");
                // call SellerService.viewStats();
                break;
            case 7:
                System.out.println("Going Back to Login Menu...");
                SellerController.handleSellerFlow(inputscanner, company);
                return;
            case 8:
                System.out.println("Logging Out...");
                return;
            case 9:
                System.out.println("Exiting application. Goodbye!");
                System.exit(0);
            default:
                System.out.println("Invalid option. Try again.");
            }
        }
    } 

    

    // LOGIN as Seller
    public static boolean LOGIN(Scanner inputscanner) {
        System.out.print("Enter Username: ");
        String username = inputscanner.nextLine();
        System.out.print("Enter Password: ");
        String password = inputscanner.nextLine();

        Seller_Repo sellerRepo = new Seller_Repo();
        boolean isValid = sellerRepo.checkSeller(username, password);

       if (isValid) {
            return true;
        } 
            System.out.println(Message.LOGIN_FAILED);
            return false;
    }

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