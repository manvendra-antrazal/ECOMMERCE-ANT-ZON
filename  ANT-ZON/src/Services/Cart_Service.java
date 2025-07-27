package Services;

import Constants.Message;
import Controller.CompanyController;
import Modal.Product;
import Repository.Cart_Repo;
import Repository.Category_Repo;
import Repository.Product_Repo;
import Util.PrintUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Cart_Service {

    public static void addToCart(Product product, int companyId, int buyerId, int quantity) throws SQLException {
        Cart_Repo repo = new Cart_Repo();
        boolean exists = repo.isProductInCart(product.getProduct_Id(), companyId, buyerId);
        if (exists) {
            PrintUtil.printMessage(Message.PRODUCT_ALREADY_IN_CART);
        } else {
            repo.insertCartItem(product.getProduct_Id(), companyId, buyerId, quantity);
            PrintUtil.printMessage(Message.PRODUCT_ADDED_TO_CART);
        }
    }

    public static void viewCart(Scanner sc, int buyerId) throws SQLException {
        List<Product> cartItems;
        try {
            cartItems = new Cart_Repo().getCartItemsByBuyerId(buyerId);
        } catch (Exception e) {
            PrintUtil.printMessageWithException(Message.ERROR_FETCHING_CART, e);
            return;
        }

        if (cartItems.isEmpty()) {
            PrintUtil.printMessage(Message.EMPTY_CART);
            return;
        }

        Category_Repo categoryRepo = new Category_Repo();
        Integer furnitureCatId;
        Integer eventSubCatId;
        try {
            furnitureCatId = categoryRepo.getCategoryIdByName("furniture");
            eventSubCatId = categoryRepo.getSubCategoryIdByName("event");
        } catch (Exception e) {
            PrintUtil.printMessageWithException(Message.ERROR_FETCHING__CATEGORY_AND_SUBCAT, e);
            return;
        }

        if (furnitureCatId == null || eventSubCatId == null) {
            PrintUtil.printMessage(Message.ERROR_FETCHING__CATEGORY_AND_SUBCAT);
            return;
        }

        boolean hasSpecialLaptop = false;
        boolean hasEventFurniture = false;

        for (Product p : cartItems) {
            String name = p.getProduct_Name().toLowerCase();
            if (name.contains("dell 7640") || name.contains("lenovo 5540")) {
                hasSpecialLaptop = true;
            }
            if (p.getCategory_ID() == furnitureCatId && p.getSub_cat_ID() == eventSubCatId) {
                hasEventFurniture = true;
            }
        }

        PrintUtil.printMessage(Message.CART_UPPER_FRAME);
        int index = 1;
        for (Product p : cartItems) {
            double originalPrice = p.getProduct_Price();
            double discountedPrice = originalPrice;
            String name = p.getProduct_Name().toLowerCase();

            boolean isLaptop = name.contains("dell 7640") || name.contains("lenovo 5540");
            boolean isEventFurniture = (p.getCategory_ID() == furnitureCatId && p.getSub_cat_ID() == eventSubCatId);

            double discount = 0.0;
            if (isLaptop) discount += 2.5;
            if (isEventFurniture) discount += 2.5;

            discountedPrice = originalPrice - (originalPrice * discount / 100.0);

            System.out.printf("║ %-3d ║ %-23s ║ %-10.2f ║ %-12.2f ║ %-8d ║ %-54s ║\n",
                    index++,
                    p.getProduct_Name(),
                    originalPrice,
                    discountedPrice,
                    p.getProduct_Quantity(),
                    p.getProduct_Description());
        }

        PrintUtil.printMessage(Message.CART_LOWER_FRAME);

        while (true) {
            PrintUtil.printMessage(Message.CART_CHOICE_INPUT);
            String input = sc.nextLine().trim().toUpperCase();

            switch (input) {
                case "A":
                    return;

                case "B": {
                    try {
                         PrintUtil.printMessage(Message.COMA_SEPRATED);
                        String[] selections = sc.nextLine().split(",");

                        List<Product> purchasedProducts = new ArrayList<>();
                        List<Integer> quantities = new ArrayList<>();
                        double totalPrice = 0.0;
                        List<String> txnIds = new ArrayList<>();

                        for (String selection : selections) {
                            int prodNo;
                            try {
                                prodNo = Integer.parseInt(selection.trim());
                            } catch (NumberFormatException e) {
                                PrintUtil.printMessage(Message.INVALID_INPUT + ": " + selection.trim());
                                continue;
                            }

                            if (prodNo < 1 || prodNo > cartItems.size()) {
                                PrintUtil.printMessage(Message.INVALID_CHOICE + ": " + prodNo);
                                continue;
                            }

                            Product selectedProduct = cartItems.get(prodNo - 1);
                            Product actualProduct = new Product_Repo().getProductById(selectedProduct.getProduct_Id());

                            if (actualProduct == null) {
                                PrintUtil.printMessage(Message.PRODUCT_NOT_FOUND + ": " + selectedProduct.getProduct_Name());
                                continue;
                            }

                            System.out.print("Enter quantity for " + actualProduct.getProduct_Name() + ": ");
                            int qty;
                            try {
                                qty = Integer.parseInt(sc.nextLine().trim());
                                if (qty < 1) {
                                    PrintUtil.printMessage(Message.QUANTITY_GREATER_ZERO);
                                    continue;
                                }
                            } catch (NumberFormatException e) {
                                PrintUtil.printMessage(Message.VALID_NUMBER);
                                continue;
                            }

                            if (actualProduct.getProduct_Quantity() < qty) {
                                PrintUtil.printMessage(String.format(Message.STOCK_INSUFFICIENT, actualProduct.getProduct_Name(), actualProduct.getProduct_Quantity()));
                                continue;
                            }

                            double discounted = Order_Service.placeOrderReturnPrice(actualProduct, buyerId, qty);
                            totalPrice += discounted;

                            purchasedProducts.add(actualProduct);
                            quantities.add(qty);

                            String txnId = Message.TRANSACTION_ID_PREFIX + new Random().nextInt(99999999);
                            txnIds.add(txnId);

                            PrintUtil.printMessage("✔ Order placed for: " + actualProduct.getProduct_Name() + " (Qty: " + qty + ")");
                        }

                        if (!purchasedProducts.isEmpty()) {
                            Invoice_Service.generateInvoice(buyerId, purchasedProducts, quantities, totalPrice, txnIds);
                            PrintUtil.printMessage(Message.ORDER_SUCCESSFUL);
                        } else {
                            PrintUtil.printMessage(Message.NO_VALID_PRODUCTS_PURCHASED);
                        }

                    } catch (Exception e) {
                        PrintUtil.printMessageWithException(Message.ORDER_FAILED, e);
                    }
                    break;
                }

                case "C": {
                    try {
                        PrintUtil.printMessage(Message.ENTER_PRODUCT_NO_REMOVE);
                        int removeIndex = Integer.parseInt(sc.nextLine());
                        if (removeIndex < 1 || removeIndex > cartItems.size()) {
                            PrintUtil.printMessage(Message.INVALID_CHOICE);
                        } else {
                            Product toRemove = cartItems.get(removeIndex - 1);
                            new Cart_Repo().removeFromCart(buyerId, toRemove.getProduct_Id());
                            PrintUtil.printMessage(Message.PRODUCT_REMOVED_CART);
                            return;
                        }
                    } catch (NumberFormatException e) {
                        PrintUtil.printMessage(Message.VALID_NUMBER);
                    } catch (Exception e) {
                        PrintUtil.printMessageWithException(Message.REMOVE_FAILED, e);
                    }
                    break;
                }

                case "D": {
                    try {
                        PrintUtil.printMessage(Message.ENTER_PRODUCT_NO_UPDATE);
                        int updateIndex = Integer.parseInt(sc.nextLine().trim());

                        if (updateIndex < 1 || updateIndex > cartItems.size()) {
                            PrintUtil.printMessage(Message.INVALID_CHOICE);
                            break;
                        }

                        Product toUpdate = cartItems.get(updateIndex - 1);
                        Product actualProduct = new Product_Repo().getProductById(toUpdate.getProduct_Id());

                        if (actualProduct == null) {
                            PrintUtil.printMessage(Message.PRODUCT_NOT_FOUND);
                            break;
                        }

                        PrintUtil.printMessage(Message.ENTER_NEW_QUANTITY);
                        int newQty = Integer.parseInt(sc.nextLine().trim());

                        if (newQty <= 0) {
                            PrintUtil.printMessage(Message.QUANTITY_GREATER_ZERO);
                        } else if (newQty > actualProduct.getProduct_Quantity()) {
                            PrintUtil.printMessage(Message.QUANTITY_EXCEED);
                        } else {
                            new Cart_Repo().updateCartQuantity(buyerId, toUpdate.getProduct_Id(), newQty);
                            PrintUtil.printMessage(Message.QUANTITY_UPDATED);
                            return;
                        }

                    } catch (NumberFormatException e) {
                        PrintUtil.printMessage(Message.VALID_NUMBER);
                    } catch (Exception e) {
                        PrintUtil.printMessageWithException(Message.UPDATE_FAILED, e);
                    }
                    break;
                }

                case "E":
                    CompanyController.startCompanySelection(sc);
                    return;

                default:
                    PrintUtil.printMessage(Message.INVALID_CHOICE);
            }
        }
    }
}
