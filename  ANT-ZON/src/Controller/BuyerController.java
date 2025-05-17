package Controller;

import Modal.Company;
import Services.Buyer_Service;
import java.util.Scanner;

public class BuyerController {
    public static void handleBuyerFlow(Scanner inputscanner, Company company) {
        Buyer_Service.handleBuyerLogin(inputscanner, "BUYER", company);
    }
}