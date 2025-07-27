package Controller;

import Constants.Message;
import Modal.Company;
import Services.Seller_Service;
import Util.PrintUtil;
import java.util.Scanner;

public class SellerController {

    public static void handleSellerFlow(Scanner inputscanner, Company company) {
        try {
            Seller_Service.handleSellerLogin(inputscanner, "SELLER", company);
        } catch (Exception e) {
            PrintUtil.printMessageWithException(Message.SELLER_FLOW_ERROR, e);
        }
    }
}
