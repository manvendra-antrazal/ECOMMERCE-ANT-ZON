package Controller;

import Modal.Company;
import Modal.Product;
import Modal.Seller;
import Services.SellerService;
import View.SellerView;
import java.util.List;
import java.util.Scanner;

public class SellerController {

    private static final SellerController INSTANCE = new SellerController();
    private static final SellerService sellerService = SellerService.getInstance();

    private SellerController() {}
    

    public static SellerController getInstance() {
        return INSTANCE;
    }

    public Integer loginSeller(String username, String password, int companyId) {
        return sellerService.loginSeller(username, password, companyId);
    }

    public boolean registerSeller(String username, String password, int companyId) {
        return sellerService.registerSeller(username, password, companyId);
    }

    public boolean addProduct(int sellerId, Product product) {
        return sellerService.addProduct(product, sellerId);
    }

    public boolean updateProduct(int sellerId, Product product) {
        return sellerService.updateProduct(product, sellerId);
    }

    public boolean removeProduct(int sellerId, int productId) {
        return sellerService.deleteProduct(productId, sellerId);
    }

    public List<Product> getSellerProducts(int sellerId) {
        return sellerService.getProductsBySeller(sellerId);
    }

    public double getTotalSales(int sellerId) {
        return sellerService.getTotalSales(sellerId);
    }

    public Seller getSellerProfile(int sellerId) {
        return sellerService.getSellerProfile(sellerId);
    }


    public static void handleSellerFlow(Scanner inputScanner, Company company) {
        boolean exit = false;
        Integer sellerId = SellerView.loginOrRegisterSeller(inputScanner, company.getCompany_Id());
        if (sellerId == null) {
            return; 
        }

        while (!exit) {
            String choice = SellerView.showSellerMenuAndGetChoice();

            switch (choice) {
                case "1": 
                    List<Product> products = SellerController.getInstance().getSellerProducts(sellerId);
                    SellerView.displayProducts(products);
                    break;

                case "2": 
                    Product toAdd = SellerView.getProductDetailsForAdd(company.getCompany_Id(), sellerId);
                    boolean added = SellerController.getInstance().addProduct(sellerId, toAdd);
                    SellerView.printAddProductResult(added);
                    break;

                case "3": 
                    Product toUpdate = SellerView.getProductDetailsForAdd(company.getCompany_Id(), sellerId);
                    boolean updated = SellerController.getInstance().updateProduct(sellerId, toUpdate);
                    SellerView.printUpdateProductResult(updated);
                    break;

                case "4": 
                    int productId = SellerView.getProductIdForDelete();
                    boolean removed = SellerController.getInstance().removeProduct(sellerId, productId);
                    SellerView.printDeleteProductResult(removed);
                    break;

                case "0": 
                    exit = true;
                    break;

                default:
                    SellerView.printInvalidChoice();
                    break;
            }
        }
    }


}


