package Controller;

import Constants.Message;
import Modal.Company;
import Services.Product_Service;
import java.util.Scanner;

public class ProductController {

    // Existing getProducts (Electronics)
    // public static void getProducts(int product) {
    //     Product_Repo repo = new Product_Repo();
    //     List<Product> products = repo.getProductsByCategory("Electronics");

    //     if (products.isEmpty()) {
    //         System.out.println("No electronics products found.");
    //     } else {
    //         for (Product p : products) {
    //             System.out.println(p);
    //         }
    //     }
    // }

    

    public static void showSellerMenu(Scanner inputscanner, String role, Company company, int sellerId) {
    while (true) {
        System.out.println(Message.SELLER_MENU);
        // System.out.println(Message.BACK_LOGOUT_EXIT_FRAME);
        System.out.print(Message.SELECT_OPTION);

        String input = inputscanner.nextLine().trim();

        switch (input.toUpperCase()) {
            case "A":
                // Go back to role selection
                CompanyController.handleLoginRoles(company, inputscanner);
                return;

            case "B":
                // Logout and go back to company selection
                CompanyController.startCompanySelection(inputscanner);
                return;

            case "C":
                System.out.println(Message.EXIT_MESSAGE);
                System.exit(0);
                break;

            case "1":
                Product_Service.viewAllProducts(inputscanner, sellerId);
                break;

            case "2":
                Product_Service.addProduct(inputscanner, sellerId, company.getCompany_Id());
                break;

            case "3":
                Product_Service.updateProductInfo(inputscanner, role, company, sellerId);
                break;

            case "4":
                Product_Service.deleteProduct(inputscanner, sellerId);
                break;

            // case "5":
            //     // Product_Service.viewProductStats(sellerId);
            //     break;

            default:
                System.out.println(Message.INVALID_INPUT);
        }
    }
}
    
}
