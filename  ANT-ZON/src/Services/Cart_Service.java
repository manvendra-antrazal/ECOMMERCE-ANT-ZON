package Services;

import Constants.Message;
import Controller.CompanyController;
import Modal.Product;
import Repository.Cart_Repo;
import Repository.Product_Repo;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;


public class Cart_Service {

        public static void addToCart(Product product, int companyId, int buyerId, int quantity) throws SQLException {
        Cart_Repo repo = new Cart_Repo();
        repo.insertCartItem(product.getProduct_Id(), companyId, buyerId, quantity); 
    }


    public static void viewCart(Scanner sc, int buyerId) {

     List<Product> cartItems = new Cart_Repo().getCartItemsByBuyerId(buyerId);

    if (cartItems.isEmpty()) {
        System.out.println(Message.EMPTY_CART);
        return;
    }

    System.out.println(Message.CART_UPPER_FRAME);
    System.out.printf("║ %-3s | %-20s | %-8s | %-8s | %-50s    ║\n", 
                      "No", "Product Name", "Price", "Qty", "Description");
    System.out.println(Message.CART_MIDDLE_FRAME);

    int index = 1;
    for (Product p : cartItems) {
        System.out.printf("║ %-3d | %-20s | %-8.2f | %-8d | %-50s    ║\n",
                          index++,
                          p.getProduct_Name(),
                          p.getProduct_Price(),
                          p.getProduct_Quantity(),  
                          p.getProduct_Description());
    }
    System.out.println(Message.CART_LOWER_FRAME);
    // here it takes input for Back, BuyNow, Remove, Logout
    while (true) {
            System.out.print(Message.CHOICE_INPUT);
            String input = sc.nextLine().trim().toUpperCase();

            switch (input) {
                // back 
                case "A":
                    return;

                case "B": {
                // BuyNow
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
                            // Get actual product from product inventory
                            Product actualProduct = new Product_Repo().getProductById(selectedProduct.getProduct_Id());

                            if (actualProduct == null) {
                                System.out.println(Message.PRODUCT_NOT_FOUND);
                            } else if (qty > actualProduct.getProduct_Quantity()) {
                                System.out.println(Message.QUANTITY_EXCEED);
                            } else {
                                Order_Service.placeOrder(actualProduct, qty, buyerId); // Pass actual product to order
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

                // Remove Product 
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

                // LOGOUT
                case "D":
                    CompanyController.startCompanySelection(sc);

                default:
                    System.out.println(Message.INVALID_CHOICE);
            }
        }
    }
}
