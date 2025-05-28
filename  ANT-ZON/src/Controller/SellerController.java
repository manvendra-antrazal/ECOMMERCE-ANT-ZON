package Controller;

import Constants.Message;
import java.util.Scanner;

import Modal.Company;
import Services.Seller_Service;


public class SellerController {

    public static void handleSellerFlow(Scanner inputscanner, Company company) {
        try {
            Seller_Service.handleSellerLogin(inputscanner, "SELLER", company);
        } catch (Exception e) {
            System.out.println("\n" + Message.SELLER_FLOW_ERROR);
            e.printStackTrace();
        }
    } 
}
