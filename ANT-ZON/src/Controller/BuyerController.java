package Controller;

import Modal.Company;
import Services.BuyerService;
import java.util.Scanner;

public class BuyerController {

    private static final BuyerService buyerService = new BuyerService();
    public static void handleBuyerFlow(Scanner inputScanner, Company company) {
        buyerService.handleBuyerFlow(inputScanner, company);
    }


    public static void showBuyerMenu(Scanner inputScanner, String role, Company company, int buyerId) {
        buyerService.handleBuyerMenu(inputScanner, role, company, buyerId);
    }
}
