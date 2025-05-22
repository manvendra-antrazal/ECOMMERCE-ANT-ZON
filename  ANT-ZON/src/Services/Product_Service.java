package Services;

import Constants.Message;
import Modal.Category;
import Modal.Product;
import Modal.Sub_Category;
import Repository.Category_Repo;
import Repository.Product_Repo;
import Repository.SubCategory_Repo;
import Util.Validations;
import java.util.List;
import java.util.Scanner;

public class Product_Service {

    public static void viewAllProducts(Scanner inputScanner, int sellerId) {
    Product_Repo repo = new Product_Repo();
    List<Product> sellerProducts = repo.getProductsBySellerId(sellerId);

    if (sellerProducts.isEmpty()) {
        System.out.println(Message.EMPTY_SELLER_PRODUCTS_LIST);
        return;
    }

    System.out.println(Message.AVAILABLE_PRODUCTS); 
    System.out.println("----------------------------------------------------------------------------------------------------------");
    System.out.printf("| %-3s | %-20s | %-8s | %-8s | %-50s |\n",
            "No", "Product Name", "Price", "Qty", "Description");
    System.out.println("----------------------------------------------------------------------------------------------------------");

    for (int i = 0; i < sellerProducts.size(); i++) {
        Product p = sellerProducts.get(i);
        String truncatedDescription = p.getProduct_Description();
        if (truncatedDescription.length() > 50) {
            truncatedDescription = truncatedDescription.substring(0, 47) + "...";
        }

        System.out.printf("| %-3d | %-20s | %-8.2f | %-8d | %-50s |\n",
                i + 1,
                p.getProduct_Name(),
                p.getProduct_Price(),
                p.getProduct_Quantity(),
                truncatedDescription);
    }

    System.out.println("----------------------------------------------------------------------------------------------------------");

    while (true) {
        System.out.println(Message.BACK_AND_EXIT_FRAME);  // A Back | B Exit
        System.out.print(Message.SELECT_OPTION);          // Select option 
        String choice = inputScanner.nextLine().trim().toUpperCase();

        if (choice.equals("A")) {
            return;
        }
        if (choice.equals("B")) {
            System.out.println(Message.EXIT_MESSAGE);
            System.exit(0);
        }
        System.out.println(Message.INVALID_OPTION);
    }
}



        // add product 
        public static void addProduct(Scanner inputscanner, int sellerID, int companyID) {
        Category_Repo catRepo = new Category_Repo();
        // List<Category> categories = catRepo.getAllCategories();
        List<Category> categories = catRepo.getAllCategories();

        if (categories.isEmpty()) {
            System.out.println(Message.CATEGORY_NOT_AVAILABLE);
            return;
        }

        // Display categories with numbers
        System.out.println(Message.SELLER_CATEGORY_FRAME );
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + categories.get(i).getCategory_Name());
        }
        System.out.println(Message.BACK_AND_EXIT_FRAME); // A. Back | C. Exit

        int selectedCategoryIndex = -1;
        while (true) {
            System.out.print(Message.SELECT_OPTION);
            String input = inputscanner.nextLine().trim().toUpperCase();

            if (input.equals("A")) return; // Back
            if (input.equals("B")) {
                System.out.println(Message.EXIT_MESSAGE);
                System.exit(0);
            }

            try {
                selectedCategoryIndex = Integer.parseInt(input) - 1;
                if (selectedCategoryIndex >= 0 && selectedCategoryIndex < categories.size()) break;
                System.out.println(Message.INVALID_OPTION);
            } catch (NumberFormatException e) {
                System.out.println(Message.INVALID_INPUT);
            }
        }

        int selectedCategoryId = categories.get(selectedCategoryIndex).getCategory_Id();
        SubCategory_Repo subCatRepo = new SubCategory_Repo();
        List<Sub_Category> subCategories = subCatRepo.getSubCategoriesByCategoryId(selectedCategoryId, companyID);

        if (subCategories.isEmpty()) {
            System.out.println(Message.NO_SUB_CATEGORY_FOUND);
            return;
        }

        System.out.println(Message.SELLER_SUB_CATEGORY_UPPER_FRAME);
        for (int i = 0; i < subCategories.size(); i++) {
            System.out.println((i + 1) + ". " + subCategories.get(i).getSub_cat_Name());
        }
        System.out.println(Message.SELLER_SUB_CATEGORY_LOWER_FRAME);

        int selectedSubIndex = -1;
        while (true) {
            System.out.print(Message.SELECT_OPTION);
            String input = inputscanner.nextLine().trim().toUpperCase();

            if (input.equals("A")) return; // Back
            if (input.equals("C")) {
                System.out.println(Message.EXIT_MESSAGE);
                System.exit(0);
            }

            try {
                selectedSubIndex = Integer.parseInt(input) - 1;
                if (selectedSubIndex >= 0 && selectedSubIndex < subCategories.size()) break;
                System.out.println(Message.INVALID_OPTION);
            } catch (NumberFormatException e) {
                System.out.println(Message.INVALID_INPUT);
            }
        }

        int subCatId = subCategories.get(selectedSubIndex).getSub_cat_ID();

        String name;
        while (true) {
            System.out.print(Message.PRODUCT_NAME);
            name = inputscanner.nextLine().trim();
            if (Validations.isValidName(name)){break;} 
            System.out.println(Message.INVALID_PRODUCT_NAME);
        }

        String info;
        while (true) {
            System.out.print(Message.PRODUCT_DESCRIPTION);
            info = inputscanner.nextLine().trim();
            if (Validations.isValidDescription
            (info)){break;} 
            System.out.println(Message.INVALID_PRODUCT_DESCRIPTION);
        }

        double price;
        while (true) {
            System.out.print(Message.PRODUCT_PRICE);
            String input = inputscanner.nextLine().trim();
            if (Validations.isValidPrice(input)) {
                price = Double.parseDouble(input);
                break;
            }
            System.out.println(Message.INVALID_PRODUCT_PRICE);
        }

        int quantity;
        while (true) {
            System.out.print(Message.PRODUCT_QUANTITY);
            String input = inputscanner.nextLine().trim();
            if (Validations.isValidQuantity(input)) {
                quantity = Integer.parseInt(input);
                break;
            }
            System.out.println(Message.INVALID_PRODUCT_QUANTITY);
        }

        // Product product = new Product(0, name, info, price, quantity, companyID, sellerID, subCatId, selectedCategoryId);
        // Product_Repo repo = new Product_Repo();

        Product product = new Product(0, name, info, price, quantity, sellerID, companyID, selectedCategoryId, subCatId);
        Product_Repo repo = new Product_Repo();


        if (repo.addProduct(product)) {
            System.out.println(Message.PRODUCT_ADDED);
        } else {
            System.out.println(Message.PRODUCT_NOT_ADDED);
        }
    }


    //  update product discription 
    public static void updateProductInfo(Scanner inputscanner,  int sellerId) {
    Product_Repo repo = new Product_Repo();
    List<Product> sellerProducts = repo.getProductsBySellerId(sellerId);

    if (sellerProducts.isEmpty()) {
        System.out.println(Message.EMPTY_SELLER_PRODUCT_LIST);
        return;
    }

    System.out.println(Message.PRODUCT_LIST);
    for (Product p : sellerProducts) {
        System.out.println("ID: " + p.getProduct_Id() + " | Name: " + p.getProduct_Name());
    }

    int productId;

    while (true) {
        System.out.print(Message.UPDATE_PRODUCT_ID);
        try {
            int inputProductId = Integer.parseInt(inputscanner.nextLine());
            boolean exists = sellerProducts.stream().anyMatch(p -> p.getProduct_Id() == inputProductId);
            if (exists) {
                productId = inputProductId;
                break;
            } else {
                System.out.println(Message.NO_PRODUCT_FOUND);
            }
        } catch (NumberFormatException e) {
            System.out.println(Message.INVALID_OPTION);
        }
    }

    while (true) {
        System.out.println(Message.SELECT_FIELD_TO_UPDATE);
        System.out.print(Message.SELECT_OPTION);
        String choice = inputscanner.nextLine().trim();

        boolean success = false;
        switch (choice) {
            case "1":
                System.out.print(Message.PRODUCT_NAME);
                String newName = inputscanner.nextLine().trim();
                if (Validations.isValidName(newName)) {
                    success = repo.updateProductField(productId, sellerId, "product_Name", newName);
                } else {
                    System.out.println(Message.INVALID_PRODUCT_NAME);
                }
                break;
            case "2":
                System.out.print(Message.PRODUCT_DESCRIPTION);
                String newDesc = inputscanner.nextLine().trim();
                if (Validations.isValidDescription(newDesc)) {
                    success = repo.updateProductField(productId, sellerId, "product_Info", newDesc);
                } else {
                    System.out.println(Message.INVALID_PRODUCT_DESCRIPTION);
                }
                break;
            case "3":
                System.out.print(Message.PRODUCT_PRICE);
                String newPrice = inputscanner.nextLine().trim();
                if (Validations.isValidPrice(newPrice)) {
                    success = repo.updateProductField(productId, sellerId, "product_Price", newPrice);
                } else {
                    System.out.println(Message.INVALID_PRODUCT_PRICE);
                }
                break;
            case "4":
                System.out.print(Message.PRODUCT_QUANTITY);
                String newQty = inputscanner.nextLine().trim();
                if (Validations.isValidQuantity(newQty)) {
                    success = repo.updateProductField(productId, sellerId, "product_Quantity", newQty);
                } else {
                    System.out.println(Message.INVALID_PRODUCT_QUANTITY);
                }
                break;
            case "5":
                return;
            default:
                System.out.println(Message.INVALID_OPTION);
        }

        if (success) {
            System.out.println(Message.PRODUCT_UPDATED);
        } else if (!choice.equals("5")) {
            System.out.println(Message.PRODUCT_NOT_ADDED);
        }
    }
}

    //  delete product 
    public static void deleteProduct(Scanner inputscanner, int sellerId) {
    Product_Repo repo = new Product_Repo();
    List<Product> sellerProducts = repo.getProductsBySellerId(sellerId);

    if (sellerProducts.isEmpty()) {
        System.out.println(Message.NO_PRODUCT_FOUND);
        return;
    }

    System.out.println(Message.PRODUCT_LIST);
    for (Product p : sellerProducts) {
        System.out.println("ID: " + p.getProduct_Id() + " | Name: " + p.getProduct_Name());
    }

    int productId;  // declared outside to use after the loop

    while (true) {
        System.out.print(Message.DELETE_PRODUCT_ID);
        try {
            int inputProductId = Integer.parseInt(inputscanner.nextLine());  // new local variable
            boolean exists = sellerProducts.stream().anyMatch(p -> p.getProduct_Id() == inputProductId);
            if (exists) {
                productId = inputProductId;  // assign to outer variable after validation
                break;
            } else {
                System.out.println(Message.INVALID_DELETE_PRODUCT_ID);
            }
        } catch (NumberFormatException e) {
            System.out.println(Message.INVALID_INPUT);
        }
    }

    System.out.print(Message.DELETING_CONFIRMATION);
    String confirmation = inputscanner.nextLine().trim().toLowerCase();

    if (confirmation.equals("yes") || confirmation.equals("y")) {
        boolean deleted = repo.deleteProduct(productId, sellerId);
        if (deleted) {
            System.out.println(Message.PRODUCT_DELETED);
        } else {
            System.out.println(Message.DELETING_FAILED);
        }
    } else {
        System.out.println(Message.DELETED_CANCEL);
        }
    }


    // stats 
//     public static void viewProductStats(int sellerId) {
//     Product_Repo repo = new Product_Repo();

//     // 1. Total Revenue
//     double totalRevenue = repo.getTotalRevenueBySeller(sellerId);

//     // 2. Most Liked Product
//     Product mostLikedProduct = repo.getMostLikedProductBySeller(sellerId);

//     // 3. Best Selling Product
//     Product bestSellingProduct = repo.getBestSellingProductBySeller(sellerId);

//     System.out.println("===== Product Statistics =====");
//     System.out.printf("Total Revenue: $%.2f\n", totalRevenue);

//     if (mostLikedProduct != null) {
//         System.out.println("Most Liked Product:");
//         System.out.println("  ID: " + mostLikedProduct.getProduct_Id());
//         System.out.println("  Name: " + mostLikedProduct.getProduct_Name());
//         // Assuming you have a rating or likes attribute
//     } else {
//         System.out.println("Most Liked Product: No data available");
//     }

//     if (bestSellingProduct != null) {
//         System.out.println("Best Selling Product:");
//         System.out.println("  ID: " + bestSellingProduct.getProduct_Id());
//         System.out.println("  Name: " + bestSellingProduct.getProduct_Name());
//         // Assuming quantity sold is available
//     } else {
//         System.out.println("Best Selling Product: No data available");
//     }
// }
}
