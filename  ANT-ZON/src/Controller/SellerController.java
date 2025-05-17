package Controller;

import java.util.Scanner;

import Modal.Company;
import Services.Seller_Service;

public class SellerController {

    public static void handleSellerFlow(Scanner inputscanner, Company company) {
        Seller_Service.handleSellerLogin(inputscanner, "SELLER" , company);
    } 
}
