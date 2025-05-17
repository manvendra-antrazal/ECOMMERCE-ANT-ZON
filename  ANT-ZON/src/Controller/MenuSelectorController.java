package Controller;

import Services.Buyer_Service;
import Services.Seller_Service;

public class MenuSelectorController {

    public static void Seller_Menu(Scanner inputscanner, String role, Company company){
        Seller_Service.showSellerMenu(inputscanner, role, company);
    }
    
    public static void Buyer_Menu(Scanner inputscanner, String role, Company company){
        Buyer_Service.showBuyerMenu(inputscanner, role, company);
    }
    
}
