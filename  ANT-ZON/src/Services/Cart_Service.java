package Services;

import Constants.Message;
import Controller.CompanyController;
import Modal.Product;
import Repository.Cart_Repo;
import Repository.Category_Repo;
import Repository.Product_Repo;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Cart_Service {

        // product add to cart 
        public static void addToCart(Product product, int companyId, int buyerId, int quantity) throws SQLException {
        Cart_Repo repo = new Cart_Repo();
        boolean exists = repo.isProductInCart(product.getProduct_Id(), companyId, buyerId);

        if (exists) {
            System.out.println(Message.PRODUCT_ALREADY_IN_CART); 
        } else {
        repo.insertCartItem(product.getProduct_Id(), companyId, buyerId, quantity);
        System.out.println(Message.PRODUCT_ADDED_TO_CART); 
        }
    }

        // view cart 
        public static void viewCart(Scanner sc, int buyerId) {
            List<Product> cartItems = new Cart_Repo().getCartItemsByBuyerId(buyerId);

            if (cartItems.isEmpty()) {
                System.out.println(Message.EMPTY_CART);
                return;
            }

            // Fetch event category and subcategory IDs dynamically
            Category_Repo categoryRepo = new Category_Repo();
            Integer furnitureCatId = categoryRepo.getCategoryIdByName("furniture");
            Integer eventSubCatId = categoryRepo.getSubCategoryIdByName("event");

            if (furnitureCatId == null || eventSubCatId == null) {
                System.out.println(Message.ERROR_FETCHING__CATEGORY_AND_SUBCAT);
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

            // Display the cart with Discounted Price column
            System.out.println(Message.CART_UPPER_FRAME);
            
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

                System.out.printf( "║ %-3d ║ %-23s ║ %-10.2f ║ %-12.2f ║ %-8d ║ %-54s ║\n",
                        index++,
                        p.getProduct_Name(),
                        originalPrice,
                        discountedPrice,
                        p.getProduct_Quantity(),
                        p.getProduct_Description());
            }

            System.out.println(Message.CART_LOWER_FRAME);

            // Cart options
            while (true) {
                System.out.print(Message.CART_CHOICE_INPUT);
                String input = sc.nextLine().trim().toUpperCase();

                switch (input) {
                    case "A":
                        return;

                case "B": {
                    try {
                        System.out.print("Enter product numbers to buy (comma-separated): ");
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
                                System.out.println("Invalid product number: " + selection.trim());
                                continue;
                            }

                            if (prodNo < 1 || prodNo > cartItems.size()) {
                                System.out.println(Message.INVALID_CHOICE + ": " + prodNo);
                                continue;
                            }

                            Product selectedProduct = cartItems.get(prodNo - 1);
                            Product actualProduct = new Product_Repo().getProductById(selectedProduct.getProduct_Id());

                            if (actualProduct == null) {
                                System.out.println(Message.PRODUCT_NOT_FOUND + ": " + selectedProduct.getProduct_Name());
                                continue;
                            }

                            // Ask for quantity to buy
                            System.out.print("Enter quantity for " + actualProduct.getProduct_Name() + ": ");
                            int qty;
                            try {
                                qty = Integer.parseInt(sc.nextLine().trim());
                                if (qty < 1) {
                                    System.out.println("Quantity must be at least 1.");
                                    continue;
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid quantity entered.");
                                continue;
                            }

                            if (actualProduct.getProduct_Quantity() < qty) {
                                System.out.println(String.format(Message.STOCK_INSUFFICIENT, actualProduct.getProduct_Name(), actualProduct.getProduct_Quantity()));
                                continue;
                            }

                            // Place order and get final price (with any discounts applied)
                            double discounted = Order_Service.placeOrderReturnPrice(actualProduct, buyerId, qty); // Adjust placeOrderReturnPrice to accept qty
                            totalPrice += discounted;

                            purchasedProducts.add(actualProduct);
                            quantities.add(qty);

                            // Generate pseudo transaction ID
                            String txnId = Message.TRANSACTION_ID_PREFIX + new Random().nextInt(99999999);
                            txnIds.add(txnId);

                            System.out.println("✔ Order placed for: " + actualProduct.getProduct_Name() + " (Qty: " + qty + ")");
                        }

                        // Generate invoice if any product was ordered
                        if (!purchasedProducts.isEmpty()) {
                            Invoice_Service.generateInvoice(buyerId, purchasedProducts, quantities, totalPrice, txnIds);
                            System.out.println(Message.ORDER_SUCCESSFUL);
                        } else {
                            System.out.println("No valid products were selected for purchase.");
                        }

                    } catch (Exception e) {
                        System.out.println(Message.ORDER_FAILED);
                        e.printStackTrace();
                    }
                    break;
                }


                    case "C": {
                        try {
                            System.out.print(Message.ENTER_PRODUCT_NO_REMOVE);
                            int removeIndex = Integer.parseInt(sc.nextLine());
                            if (removeIndex < 1 || removeIndex > cartItems.size()) {
                                System.out.println(Message.INVALID_CHOICE);
                            } else {
                                Product toRemove = cartItems.get(removeIndex - 1);
                                new Cart_Repo().removeFromCart(buyerId, toRemove.getProduct_Id());
                                System.out.println(Message.PRODUCT_REMOVED_CART);
                                return;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println(Message.VALID_NUMBER);
                        }
                        break;
                    }

                    case "D": {
                        try {
                            System.out.print(Message.ENTER_PRODUCT_NO_UPDATE); // Prompt for product number
                            String prodInput = sc.nextLine().trim();

                            // Validate if it's a number
                            int updateIndex = Integer.parseInt(prodInput);
                            if (updateIndex < 1 || updateIndex > cartItems.size()) {
                                System.out.println(Message.INVALID_CHOICE);
                                break;
                            }

                            Product toUpdate = cartItems.get(updateIndex - 1);

                            Product actualProduct = new Product_Repo().getProductById(toUpdate.getProduct_Id());
                            if (actualProduct == null) {
                                System.out.println(Message.PRODUCT_NOT_FOUND);
                                break;
                            }

                            System.out.print(Message.ENTER_NEW_QUANTITY); 
                            String qtyInput = sc.nextLine().trim();

                            int newQty = Integer.parseInt(qtyInput);
                            if (newQty <= 0) {
                                System.out.println(Message.QUANTITY_GREATER_ZERO);
                            } else if (newQty > actualProduct.getProduct_Quantity()) {
                                System.out.println(Message.QUANTITY_EXCEED);
                            } else {
                                new Cart_Repo().updateCartQuantity(buyerId, toUpdate.getProduct_Id(), newQty);
                                System.out.println(Message.QUANTITY_UPDATED);
                                return;
                            }

                        } catch (NumberFormatException e) {
                            System.out.println(Message.VALID_NUMBER); // Handles invalid number inputs
                        } catch (Exception e) {
                            System.out.println(Message.UPDATE_FAILED); // Handles any DB or unknown exceptions
                            e.printStackTrace(); // Optional: log for debugging
                        }
                        break;
                    }

                    case "E" :
                        CompanyController.startCompanySelection(sc);
                        return;

                    default:
                        System.out.println(Message.INVALID_CHOICE);
                }
            }
        }

}
