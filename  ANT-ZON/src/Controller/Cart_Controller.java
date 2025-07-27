package Controller;

import Constants.Message;
import Modal.Company;
import Modal.Product;
import Services.Cart_Service;
import Util.PrintUtil;
import java.util.Scanner;

public class Cart_Controller {

    public static void handleAddToCart(Scanner sc, Product product, Company company, int buyerId) {
        while (true) {
            int defaultQuantity = 1;
            try {
                if (defaultQuantity > product.getProduct_Quantity()) {
                    PrintUtil.printMessage(Message.QUANTITY_EXCEED);
                    continue;
                }

                Cart_Service.addToCart(product, company.getCompany_Id(), buyerId, defaultQuantity);
                PrintUtil.printMessage(Message.PRODUCT_ADDED_TO_CART);
                break;

            } catch (NumberFormatException e) {
                PrintUtil.printMessageWithException(Message.VALID_NUMBER, e);
            } catch (Exception e) {
                PrintUtil.printMessageWithException(Message.CART_ERROR, e);
                break;
            }
        }
    }
}
