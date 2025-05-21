package Services;

import Modal.Product;
import Repository.Cart_Repo;


public class Cart_Service {

        public static void addToCart(Product product, int companyId, int buyerId, int quantity) {
        Cart_Repo repo = new Cart_Repo();
        repo.insertCartItem(product.getProduct_Id(), companyId, buyerId, quantity); 
    }

}
