package View;

import Constants.Message;
import Controller.SellerController;
import Modal.Product;
import java.util.List;
import java.util.Scanner;


public class SellerView {

    private static final Scanner scanner = new Scanner(System.in);
    private static final SellerController sellerController = SellerController.getInstance();

    public static void openSellerMenu(int sellerId, int companyId) {
        boolean exit = false;
        while (!exit) {
            String choice = showSellerMenuAndGetChoice();

            try {
                switch (choice) {
                    case "1": 
                        Product toAdd = getProductDetailsForAdd(companyId, sellerId);
                        boolean added = sellerController.addProduct(sellerId, toAdd);
                        showMessage(added ? Message.PRODUCT_ADDED : Message.FAILED_TO_ADD_PRODUCT);
                        break;

                    case "2": 
                        List<Product> list = sellerController.getSellerProducts(sellerId);
                        displayProducts(list);
                        break;

                    case "3": 
                        int updId = getProductIdFromSeller("update");
                        Product updated = getUpdatedProductDetails(updId);
                        boolean upOk = sellerController.updateProduct(sellerId, updated);
                        showMessage(upOk ? Message.PRODUCT_UPDATED : Message.FAILED_UPDATE_PRODUCT);
                        break;

                    case "4": 
                        int delId = getProductIdFromSeller("delete");
                        boolean delOk = sellerController.removeProduct(sellerId, delId);
                        showMessage(delOk ? Message.PRODUCT_DELETED : Message.FAILED_DELETE_PRODUCT);
                        break;

                    case "5": 
                        showMessage(Message.LOGOUT_SUCCESS);
                        exit = true;
                        break;

                    default:
                        showMessage(Message.INVALID_OPTION);
                        break;
                }
            } catch (Exception e) {
                showMessage(e.getMessage());
            }
        }
    }

    public static String showSellerMenuAndGetChoice() {
        System.out.println(Message.SELLER_MENU);
        return scanner.nextLine().trim();
    }

    public static Product getProductDetailsForAdd(int companyId, int sellerId) {
        System.out.print(Message.PRODUCT_NAME);
        String name = scanner.nextLine().trim();

        double price = readDouble(Message.PRODUCT_PRICE);
        int quantity = readInt(Message.PRODUCT_QUANTITY);
        String description = scanner.nextLine().trim();

        int categoryId = readInt(Message.CATEGORY_ID);
        int subCatId = readInt(Message.SUB_CATEGORY_ID);

        Product p = new Product();
        p.setProduct_Name(name);
        p.setProduct_Price(price);
        p.setProduct_Quantity(quantity);
        p.setProduct_Description(description.isEmpty() ? null : description);
        p.setCompany_ID(companyId);
        p.setSeller_ID(sellerId);
        p.setCategory_ID(categoryId);
        p.setSub_cat_ID(subCatId);
        p.setLikes(0);

        return p;
    }
    public static Product getUpdatedProductDetails(int productId) {
        System.out.print(Message.PRODUCT_NAME);
        String name = scanner.nextLine().trim();

        String priceStr;
        Double price = null;
        while (true) {
            System.out.print(Message.PRODUCT_PRICE);
            priceStr = scanner.nextLine().trim();
            if (priceStr.isEmpty()) break;
            try {
                price = Double.parseDouble(priceStr);
                if (price < 0) {
                    System.out.println(Message.INVALID_PRODUCT_PRICE);
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println(Message.INVALID_PRODUCT_PRICE);
            }
        }

        String qtyStr;
        Integer quantity = null;
        while (true) {
            qtyStr = scanner.nextLine().trim();
            if (qtyStr.isEmpty()) break;
            try {
                quantity = Integer.parseInt(qtyStr);
                if (quantity < 0) {
                    System.out.println(Message.INVALID_PRODUCT_PRICE);
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println(Message.INVALID_OPTION);
            }
        }

        System.out.print(Message.PRODUCT_DESCRIPTION);
        String description = scanner.nextLine().trim();

        Product p = new Product();
        p.setProduct_Id(productId);
        if (!name.isEmpty()) p.setProduct_Name(name);
        if (price != null) p.setProduct_Price(price);
        if (quantity != null) p.setProduct_Quantity(quantity);
        if (!description.isEmpty()) p.setProduct_Description(description);
        return p;
    }

    public static int getProductIdFromSeller(String action) {
        return readInt("Enter Product ID to " + action + ": ");
    }

    public static void displayProducts(List<Product> products) {
        if (products == null || products.isEmpty()) {
            System.out.println(Message.NO_PRODUCT_FOUND);
            return;
        }
        System.out.println(Message.PRODUCT_LIST);
        System.out.printf("%-8s %-28s %-12s %-10s %-40s%n",
                "ID", "Name", "Price", "Qty", "Description");
        System.out.println("------------------------------------------------------------------------------------------");
        for (Product p : products) {
            String desc = p.getProduct_Description();
            if (desc != null && desc.length() > 40) {
                desc = desc.substring(0, 37) + "...";
            }
            System.out.printf("%-8d %-28s $%-11.2f %-10d %-40s%n",
                    p.getProduct_Id(),
                    safe(p.getProduct_Name()),
                    p.getProduct_Price(),
                    p.getProduct_Quantity(),
                    desc == null ? "N/A" : desc);
        }
    }

    public static void showMessage(String message) {
        System.out.println(message);
    }


    public static String promptUsername() {
        System.out.print(Message.ENTER_NEW_SELLER_USERNAME);
        return scanner.nextLine().trim();
    }

    public static String promptPassword() {
        System.out.print(Message.ENTER_NEW_SELLER_PASSWORD);
        return scanner.nextLine().trim();
    }

    private static int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String line = scanner.nextLine().trim();
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println(Message.INVALID_OPTION);
            }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String line = scanner.nextLine().trim();
                return Double.parseDouble(line);
            } catch (NumberFormatException e) {
                System.out.println(Message.INVALID_OPTION);
            }
        }
    }

    private static String safe(String s) {
        return s == null ? "N/A" : s;
    }

    public static void printAddProductResult(boolean added) {
        showMessage(added ? Message.PRODUCT_ADDED : Message.FAILED_TO_ADD_PRODUCT);
    }

    public static void printUpdateProductResult(boolean updated) {
        showMessage(updated ? Message.PRODUCT_UPDATED : Message.FAILED_UPDATE_PRODUCT);
    }

    public static int getProductIdForDelete() {
        System.out.println(Message.PRODUCT_DELETED);
        System.out.print(Message.ENTER_PRODUCT_ID);
        return Integer.parseInt(scanner.nextLine().trim());
    }

    public static void printDeleteProductResult(boolean removed) {
       showMessage(removed ? Message.PRODUCT_DELETED : Message.FAILED_DELETE_PRODUCT);
    }

    public static void printInvalidChoice() {
        System.out.println(Message.INVALID_OPTION);
    }

    public static Integer loginOrRegisterSeller(Scanner inputScanner, int company_Id) {
        System.out.println(Message.LOGIN_CREDENTIAL);
        String username = scanner.nextLine().trim();
        String password = scanner.nextLine().trim();
        return 1;
    }

}




