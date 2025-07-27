package Services;

import Constants.Message;
import Controller.ProductController;
import Modal.Category;
import Modal.Company;
import Modal.Product;
import Modal.Sub_Category;
import Repository.Category_Repo;
import Repository.Product_Repo;
import Repository.SubCategory_Repo;
import Util.PrintUtil;
import Util.Validations;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Product_Service {

    public static void viewAllProducts(Scanner inputScanner, int sellerId) throws SQLException {
        Product_Repo repo = new Product_Repo();
        List<Product> sellerProducts = repo.getProductsBySellerId(sellerId);

        if (sellerProducts.isEmpty()) {
            PrintUtil.printMessage(Message.EMPTY_SELLER_PRODUCTS_LIST);
            return;
        }

        PrintUtil.printMessages(
            Message.AVAILABLE_PRODUCTS,
            Message.PRODUCT_UPPER
        );

        System.out.printf("║ %-3s ║ %-20s ║ %-8s ║ %-8s ║ %-50s ║\n",
                "No", "Product Name", "Price", "Qty", "Description");

        PrintUtil.printMessage(Message.PRODUCTS_MIDDLE);

        for (int i = 0; i < sellerProducts.size(); i++) {
            Product p = sellerProducts.get(i);
            String truncatedDescription = p.getProduct_Description();
            if (truncatedDescription.length() > 50) {
                truncatedDescription = truncatedDescription.substring(0, 47) + "...";
            }

            System.out.printf("║ %-3d ║ %-20s ║ %-8.2f ║ %-8d ║ %-50s ║\n",
                    i + 1,
                    p.getProduct_Name(),
                    p.getProduct_Price(),
                    p.getProduct_Quantity(),
                    truncatedDescription);
        }

        PrintUtil.printMessage(Message.PRODUCTS_LAST);

        while (true) {
            PrintUtil.printMessage(Message.SELECT_OPTION_A_B);
            String choice = inputScanner.nextLine().trim().toUpperCase();

            if (choice.equals("A")) return;
            if (choice.equals("B")) {
                PrintUtil.printMessage(Message.EXIT_MESSAGE);
                System.exit(0);
            }
            PrintUtil.printMessage(Message.INVALID_OPTION);
        }
    }

    public static void addProduct(Scanner inputscanner, int sellerID, int companyID) throws SQLException {
        Category_Repo catRepo = new Category_Repo();
        List<Category> categories = catRepo.getAllCategories();

        if (categories.isEmpty()) {
            PrintUtil.printMessage(Message.CATEGORY_NOT_AVAILABLE);
            return;
        }

        PrintUtil.printMessage(Message.SELLER_CATEGORY_FRAME);
        for (int i = 0; i < categories.size(); i++) {
            System.out.printf("║   %-2d. %-31s  ║\n", (i + 1), categories.get(i).getCategory_Name());
        }
        PrintUtil.printMessage(Message.SELLER_CATEGORY_LOWER_FRAME);

        int selectedCategoryIndex = -1;
        while (true) {
            PrintUtil.printMessage(Message.SELECT_OPTION);
            String input = inputscanner.nextLine().trim().toUpperCase();

            if (input.equals("A")) return;
            if (input.equals("B")) {
                PrintUtil.printMessage(Message.EXIT_MESSAGE);
                System.exit(0);
            }

            try {
                selectedCategoryIndex = Integer.parseInt(input) - 1;
                if (selectedCategoryIndex >= 0 && selectedCategoryIndex < categories.size()) break;
                PrintUtil.printMessage(Message.INVALID_OPTION);
            } catch (NumberFormatException e) {
                PrintUtil.printMessage(Message.INVALID_INPUT);
            }
        }

        int selectedCategoryId = categories.get(selectedCategoryIndex).getCategory_Id();
        SubCategory_Repo subCatRepo = new SubCategory_Repo();
        List<Sub_Category> subCategories = subCatRepo.getSubCategoriesByCategoryId(selectedCategoryId, companyID);

        if (subCategories.isEmpty()) {
            PrintUtil.printMessage(Message.NO_SUB_CATEGORY_FOUND);
            return;
        }

        PrintUtil.printMessage(Message.SELLER_SUB_CATEGORY_UPPER_FRAME);
        for (int i = 0; i < subCategories.size(); i++) {
            System.out.printf("║   %-2d. %-31s  ║\n", i + 1, subCategories.get(i).getSub_cat_Name());
        }
        PrintUtil.printMessage(Message.SELLER_SUB_CATEGORY_LOWER_FRAME);

        int selectedSubIndex = -1;
        while (true) {
            PrintUtil.printMessage(Message.SELECT_OPTION);
            String input = inputscanner.nextLine().trim().toUpperCase();

            if (input.equals("A")) return;
            if (input.equals("C")) {
                PrintUtil.printMessage(Message.EXIT_MESSAGE);
                System.exit(0);
            }

            try {
                selectedSubIndex = Integer.parseInt(input) - 1;
                if (selectedSubIndex >= 0 && selectedSubIndex < subCategories.size()) break;
                PrintUtil.printMessage(Message.INVALID_OPTION);
            } catch (NumberFormatException e) {
                PrintUtil.printMessage(Message.INVALID_INPUT);
            }
        }

        int subCatId = subCategories.get(selectedSubIndex).getSub_cat_ID();

        String name;
        while (true) {
            PrintUtil.printMessage(Message.PRODUCT_NAME);
            name = inputscanner.nextLine().trim();
            if (Validations.isValidName(name)) break;
            PrintUtil.printMessage(Message.INVALID_PRODUCT_NAME);
        }

        String info;
        while (true) {
            PrintUtil.printMessage(Message.PRODUCT_DESCRIPTION);
            info = inputscanner.nextLine().trim();
            if (Validations.isValidDescription(info)) break;
            PrintUtil.printMessage(Message.INVALID_PRODUCT_DESCRIPTION);
        }

        double price;
        while (true) {
            PrintUtil.printMessage(Message.PRODUCT_PRICE);
            String input = inputscanner.nextLine().trim();
            if (Validations.isValidPrice(input)) {
                price = Double.parseDouble(input);
                break;
            }
            PrintUtil.printMessage(Message.INVALID_PRODUCT_PRICE);
        }

        int quantity;
        while (true) {
            PrintUtil.printMessage(Message.PRODUCT_QUANTITY);
            String input = inputscanner.nextLine().trim();
            if (Validations.isValidQuantity(input)) {
                quantity = Integer.parseInt(input);
                break;
            }
            PrintUtil.printMessage(Message.INVALID_PRODUCT_QUANTITY);
        }

        Product product = new Product(0, name, info, price, quantity, sellerID, companyID, selectedCategoryId, subCatId, 0);
        Product_Repo repo = new Product_Repo();

        if (repo.addProduct(product)) {
            PrintUtil.printMessage(Message.PRODUCT_ADDED);
        } else {
            PrintUtil.printMessage(Message.PRODUCT_NOT_ADDED);
        }
    }

    // update product discription
    public static void updateProductInfo(Scanner inputscanner, String role, Company company, int sellerId) throws SQLException {
    Product_Repo repo = new Product_Repo();
    List<Product> sellerProducts = repo.getProductsBySellerId(sellerId);

    if (sellerProducts.isEmpty()) {
        PrintUtil.printMessage(Message.EMPTY_SELLER_PRODUCT_LIST);
        return;
    }

    PrintUtil.printMessage(Message.PRODUCT_LIST);
    for (Product p : sellerProducts) {
        PrintUtil.printMessage("ID: " + p.getProduct_Id() + " | Name: " + p.getProduct_Name());
    }

    int productId;

    while (true) {
        PrintUtil.printMessage(Message.UPDATE_PRODUCT_ID);
        try {
            int inputProductId = Integer.parseInt(inputscanner.nextLine());
            boolean exists = sellerProducts.stream().anyMatch(p -> p.getProduct_Id() == inputProductId);
            if (exists) {
                productId = inputProductId;
                break;
            } else {
                PrintUtil.printMessage(Message.NO_PRODUCT_FOUND);
            }
        } catch (NumberFormatException e) {
            PrintUtil.printMessage(Message.INVALID_OPTION);
        }
    }

    while (true) {
        PrintUtil.printMessage(Message.SELECT_FIELD_TO_UPDATE);
        PrintUtil.printMessage(Message.SELECT_OPTION);
        String choice = inputscanner.nextLine().trim();

        boolean success = false;
        switch (choice) {

            case "A":
                ProductController.showSellerMenu(inputscanner, role, company, sellerId);
                return;

            case "B":
                PrintUtil.printMessage(Message.EXIT_MESSAGE);
                System.exit(0);
                break;

            case "1":
                PrintUtil.printMessage(Message.PRODUCT_NAME);
                String newName = inputscanner.nextLine().trim();
                if (Validations.isValidName(newName)) {
                    success = repo.updateProductField(productId, sellerId, "product_Name", newName);
                } else {
                    PrintUtil.printMessage(Message.INVALID_PRODUCT_NAME);
                }
                break;

            case "2":
                PrintUtil.printMessage(Message.PRODUCT_DESCRIPTION);
                String newDesc = inputscanner.nextLine().trim();
                if (Validations.isValidDescription(newDesc)) {
                    success = repo.updateProductField(productId, sellerId, "product_Info", newDesc);
                } else {
                    PrintUtil.printMessage(Message.INVALID_PRODUCT_DESCRIPTION);
                }
                break;

            case "3":
                PrintUtil.printMessage(Message.PRODUCT_PRICE);
                String newPrice = inputscanner.nextLine().trim();
                if (Validations.isValidPrice(newPrice)) {
                    success = repo.updateProductField(productId, sellerId, "product_Price", newPrice);
                } else {
                    PrintUtil.printMessage(Message.INVALID_PRODUCT_PRICE);
                }
                break;

            case "4":
                PrintUtil.printMessage(Message.PRODUCT_QUANTITY);
                String newQty = inputscanner.nextLine().trim();
                if (Validations.isValidQuantity(newQty)) {
                    success = repo.updateProductField(productId, sellerId, "product_Quantity", newQty);
                } else {
                    PrintUtil.printMessage(Message.INVALID_PRODUCT_QUANTITY);
                }
                break;

            case "5":
                return;

            default:
                PrintUtil.printMessage(Message.INVALID_OPTION);
        }

        if (success) {
            PrintUtil.printMessage(Message.PRODUCT_UPDATED);
        } else if (!choice.equals("5")) {
            PrintUtil.printMessage(Message.PRODUCT_NOT_ADDED);
        }
    }
}

// delete product
public static void deleteProduct(Scanner inputscanner, int sellerId) throws SQLException {
    Product_Repo repo = new Product_Repo();
    List<Product> sellerProducts = repo.getProductsBySellerId(sellerId);

    if (sellerProducts.isEmpty()) {
        PrintUtil.printMessage(Message.NO_PRODUCT_FOUND);
        return;
    }

    PrintUtil.printMessage(Message.PRODUCT_LIST);
    for (Product p : sellerProducts) {
        // Keep printf formatting as-is (PrintUtil doesn’t yet support formatting)
        System.out.printf("║ %-4d ║ %-25s ║\n", p.getProduct_Id(), p.getProduct_Name());
    }

    int productId;

    while (true) {
        PrintUtil.printMessage(Message.DELETE_PRODUCT_ID);
        try {
            int inputProductId = Integer.parseInt(inputscanner.nextLine());
            boolean exists = sellerProducts.stream().anyMatch(p -> p.getProduct_Id() == inputProductId);
            if (exists) {
                productId = inputProductId;
                break;
            } else {
                PrintUtil.printMessage(Message.INVALID_DELETE_PRODUCT_ID);
            }
        } catch (NumberFormatException e) {
            PrintUtil.printMessage(Message.INVALID_INPUT);
        }
    }

    PrintUtil.printMessage(Message.DELETING_CONFIRMATION);
    String confirmation = inputscanner.nextLine().trim().toLowerCase();

    if (confirmation.equals("yes") || confirmation.equals("y")) {
        boolean deleted = repo.deleteProduct(productId, sellerId);
        if (deleted) {
            PrintUtil.printMessage(Message.PRODUCT_DELETED);
        } else {
            PrintUtil.printMessage(Message.DELETING_FAILED);
        }
    } else {
        PrintUtil.printMessage(Message.DELETED_CANCEL);
    }
} 
}
