package Services;

import Constants.Message;
import Controller.ProductController;

import java.util.Scanner;

public class Category_Service {

    public static void Category(Scanner inputscanner){
        System.out.println(Message.CATEGORY);
        System.out.print(Message.SELECT_OPTION);
        int category = inputscanner.nextInt();
        int product;
        switch(category){
            case 1:
                System.out.println(Message.ELECTRONIC_PRODUCT_LIST);
                System.out.print(Message.SELECT_OPTION);
                product = inputscanner.nextInt();
                ProductController.getProducts(product);


                
                break;
            case 2 :
                System.out.println(Message.FURNITURE_PRODUCT_LIST);
                System.out.print(Message.SELECT_OPTION);
                product = inputscanner.nextInt();
                break;
            default :
        }
    }
    
}
