package Controller;

import Constants.Message;
import Modal.Company;
import Services.Buyer_Service;
import static Services.Buyer_Service.handleBuyerLogin;
import Services.Cart_Service;
import Services.Order_Service;
import Services.Wishlist_Service;
import Util.PrintUtil;
import java.util.Scanner;

public class BuyerController {

    public static void handleBuyerFlow(Scanner inputscanner, Company company) {
        try {
            Buyer_Service.handleBuyerLogin(inputscanner, "BUYER", company);
        } catch (Exception e) {
            PrintUtil.printMessageWithException(Message.GENERAL_ERROR, e);
        }
    }

    public static void showBuyerMenu(Scanner inputscanner, String role, Company company, int buyerId) {
        while (true) {
            try {
                PrintUtil.printMessage(Message.BUYER_MENU);
                PrintUtil.printMessage(Message.SELECT_OPTION);

                String input = inputscanner.nextLine().trim();

                if (input.isEmpty()) {
                    PrintUtil.printMessage(Message.EMPTY_INPUT);
                    continue;
                }

                switch (input.toUpperCase()) {
                    case "A":
                        handleBuyerLogin(inputscanner, role, company);
                        return;
                    case "B":
                        CompanyController.startCompanySelection(inputscanner);
                        return;
                    case "C":
                        PrintUtil.printMessage(Message.EXIT_MESSAGE);
                        System.exit(0);
                        break;
                    default:
                        try {
                            int option = Integer.parseInt(input);
                            switch (option) {
                                case 1:
                                    Buyer_Service.browseProducts(inputscanner, role, company, buyerId);
                                    break;
                                case 2:
                                    Wishlist_Service.viewWishlist(inputscanner, buyerId);
                                    break;
                                case 3:
                                    Cart_Service.viewCart(inputscanner, buyerId);
                                    break;
                                case 4:
                                    Order_Service.viewOrderHistory(inputscanner, role, company, buyerId);
                                    break;
                                default:
                                    PrintUtil.printMessage(Message.INVALID_OPTION);
                            }
                        } catch (NumberFormatException e) {
                            PrintUtil.printMessage(Message.INVALID_INPUT);
                        } catch (Exception e) {
                            PrintUtil.printMessageWithException(Message.GENERAL_ERROR, e);
                        }
                }
            } catch (Exception e) {
                PrintUtil.printMessageWithException(Message.GENERAL_ERROR, e);
            }
        }
    }
}
