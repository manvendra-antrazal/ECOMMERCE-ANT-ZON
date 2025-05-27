package Services;

import Constants.Message;
import Controller.CompanyController;
import Modal.Product;
import Repository.Cart_Repo;
import Repository.Category_Repo;
import Repository.Product_Repo;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;


public class Cart_Service {

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
            System.out.printf("║ %-3s | %-20s | %-10s | %-12s | %-8s | %-50s             ║\n",
                    "No", "Product Name", "Price", "Discounted", "Qty", "Description");
            System.out.println(Message.CART_MIDDLE_FRAME);

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

                System.out.printf("║ %-3d | %-20s | %-10.2f | %-12.2f | %-8d | %-50s             ║\n",
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
                System.out.print(Message.CHOICE_INPUT);
                String input = sc.nextLine().trim().toUpperCase();

                switch (input) {
                    case "A":
                        return;

                    case "B": {
                        try {
                            System.out.print(Message.ENTER_PRODUCT_NO);
                            int prodNo = Integer.parseInt(sc.nextLine());

                            if (prodNo < 1 || prodNo > cartItems.size()) {
                                System.out.println(Message.INVALID_CHOICE);
                                break;
                            }

                            Product selectedProduct = cartItems.get(prodNo - 1);

                            System.out.print(Message.QUANTITY);
                            int qty = Integer.parseInt(sc.nextLine());

                            if (qty <= 0) {
                                System.out.println(Message.QUANTITY_GREATER_ZERO);
                            } else {
                                Product actualProduct = new Product_Repo().getProductById(selectedProduct.getProduct_Id());

                                if (actualProduct == null) {
                                    System.out.println(Message.PRODUCT_NOT_FOUND);
                                } else if (qty > actualProduct.getProduct_Quantity()) {
                                    System.out.println(Message.QUANTITY_EXCEED);
                                } else {
                                    Order_Service.placeOrder(actualProduct, qty, buyerId);
                                    System.out.println(Message.ORDER_SUCCESSFUL);
                                    return;
                                }
                            }
                        } catch (NumberFormatException e) {
                            System.out.println(Message.VALID_NUMBER);
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

                    case "D":
                        CompanyController.startCompanySelection(sc);
                        return;

                    default:
                        System.out.println(Message.INVALID_CHOICE);
                }
            }
        }

}
