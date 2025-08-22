package Services;

import Constants.Message;
import Controller.BuyerController;
import Modal.Company;
import Modal.Product;
import Repository.*;
import View.BuyerView;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class BuyerService {

    // Repositories
    private final BuyerRepository buyerRepo = BuyerRepository.getInstance();
    private final ProductRepo productRepo = ProductRepo.getInstance();
    private final Wishlist_repo wishlistRepo = new Wishlist_repo();
    private final CartRepository cartRepo = CartRepository.getInstance();
    private final OrderRepo orderRepo = OrderRepo.getInstance();

    // ----------- Buyer Flow -----------
    public void handleBuyerFlow(Scanner inputScanner, Company company) {
        BuyerView.showWelcomeMenu();
        int choice = inputScanner.nextInt();
        inputScanner.nextLine();

        try {
            switch (choice) {
                case 1:
                    BuyerView.showLoginPrompt();
                    String username = BuyerView.getUsername(inputScanner);
                    String password = BuyerView.getPassword(inputScanner);
                    int buyerId = login(username, password);
                    if (buyerId > 0) {
                        BuyerView.showLoginSuccess();
                        BuyerController.showBuyerMenu(inputScanner, "Buyer", company, buyerId);
                    } else {
                        BuyerView.showInvalidCredentials();
                    }
                    break;

                case 2:
                    BuyerView.showRegisterPrompt();
                    String newUser = BuyerView.getUsername(inputScanner);
                    String newPass = BuyerView.getPassword(inputScanner);
                    if (register(newUser, newPass)) {
                        BuyerView.showRegistrationSuccess();
                    } else {
                        BuyerView.showRegistrationFailure();
                    }
                    break;

                case 3:
                    BuyerView.showExitMessage();
                    return;

                default:
                    BuyerView.showMessage(Message.INVALID_OPTION);
            }
        } catch (Exception e) {
            BuyerView.showMessage("Error: " + e.getMessage());
        }
    }

    // ----------- Buyer Menu -----------
    public void handleBuyerMenu(Scanner scanner, String role, Company company, int buyerId) {
        boolean exit = false;
        while (!exit) {
            String choice = showBuyerMenuAndGetChoice(scanner);
            try {
                switch (choice) {
                    case "1":
                        int subCatId = BuyerView.getSubCategoryChoice(scanner);
                        List<Product> products = browseProducts(company.getCompany_Id(), subCatId);
                        BuyerView.showProducts(products);
                        break;

                    case "2":
                        String keyword = BuyerView.getSearchKeyword(scanner);
                        int subCat = BuyerView.getSubCategoryChoice(scanner);
                        List<Product> searchResults = searchProductByName(company.getCompany_Id(), subCat, keyword);
                        BuyerView.showProducts(searchResults);
                        break;

                    case "3":
                        int productIdWishlist = BuyerView.getProductIdForWishlist(scanner);
                        if (addToWishlist(buyerId, productIdWishlist, company.getCompany_Id())) {
                            BuyerView.showMessage(Message.PRODUCT_ADDED_TO_WISHLIST);
                        } else {
                            BuyerView.showMessage(Message.PRODUCT_EXISTS_IN_WISHLIST);
                        }
                        break;

                    case "4":
                        BuyerView.showWishlist(getWishlist(buyerId));
                        break;

                    case "5":
                        int productIdCart = BuyerView.getProductIdForCart(scanner);
                        int qty = BuyerView.getProductQty(scanner);
                        if (addToCart(buyerId, productIdCart, company.getCompany_Id(), qty)) {
                            BuyerView.showMessage(Message.PRODUCT_ADDED_TO_CART);
                        } else {
                            BuyerView.showMessage(Message.ADD_PRODUCT_ERROR);
                        }
                        break;

                    case "6":
                        BuyerView.showCart(getCartItems(buyerId));
                        break;

                    case "7":
                        BuyerView.showOrders(getOrders(buyerId));
                        break;

                    case "8":
                        BuyerView.showMessage(Message.LOGOUT_SUCCESS);
                        exit = true;
                        break;

                    default:
                        BuyerView.showMessage(Message.INVALID_OPTION);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    // --- Helper for menu choice ---
    private String showBuyerMenuAndGetChoice(Scanner scanner) {
        BuyerView.showBuyerMenu();
        return scanner.nextLine();
    }

    // ----------- Auth Methods -----------
    public int login(String username, String password) throws SQLException {
        return buyerRepo.getBuyerId(username, password);
    }

    public boolean register(String username, String password) throws SQLException {
        return buyerRepo.registerBuyer(username, password);
    }

    // ----------- Product Methods -----------
    public List<Product> browseProducts(int companyId, int subCatId) throws SQLException {
        return productRepo.getProductsByCategoryAndSubCategory(companyId, subCatId);
    }

    public List<Product> searchProductByName(int companyId, int subCatId, String keyword) throws SQLException {
        List<Product> products = productRepo.getProductsByCategoryAndSubCategory(companyId, subCatId);
        products.removeIf(p -> p.getProduct_Name() == null ||
                !p.getProduct_Name().toLowerCase().contains(keyword.toLowerCase()));
        return products;
    }

    public boolean addToWishlist(int buyerId, int product_Id, int companyId) throws SQLException {
        try {
            if (wishlistRepo.isProductInWishlist(buyerId, product_Id, companyId)) {
                return false;
            }
        } catch (Exception e) {
            throw new SQLException(e);
        }
        try {
            return wishlistRepo.addToWishlist(buyerId, product_Id, companyId);
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

    public List<Product> getWishlist(int userID) throws SQLException {
        try {
            return Wishlist_repo.getWishlistByBuyerId(userID);
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

    public boolean addToCart(int buyerId, int product_Id, int companyId, int qty) throws SQLException {
        if (qty <= 0) {
            throw new IllegalArgumentException(Message.INVALID_PRODUCT_QUANTITY);
        }
        if (cartRepo.isProductInCart(product_Id, companyId, buyerId)) {
            return cartRepo.updateCartQuantity(buyerId, product_Id, qty);
        } else {
            return cartRepo.insertCartItem(product_Id, companyId, buyerId, qty);
        }
    }

    public List<Product> getCartItems(int buyerId) throws SQLException {
        return cartRepo.getCartItemsByBuyerId(buyerId);
    }

    public List<Modal.Order> getOrders(int buyerId) throws SQLException {
        return orderRepo.getOrdersByBuyerId(buyerId);
    }
}
