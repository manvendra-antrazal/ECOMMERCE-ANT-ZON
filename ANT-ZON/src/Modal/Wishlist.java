package Modal;

import java.sql.Timestamp;

public class Wishlist {

    private int wishlist_Id;
    private int company_Id;
    private int buyer_Id;
    private int product_Id;
    private Timestamp added_on;
    private Product product;

    public Wishlist() {
    }

    public Wishlist(int wishlist_Id, int buyer_Id, int company_Id, int product_Id, Timestamp added_on) {
        this.wishlist_Id = wishlist_Id;
        this.buyer_Id = buyer_Id;
        this.company_Id = company_Id;
        this.product_Id = product_Id;
        this.added_on = added_on;
    }

    public Wishlist(int buyer_Id, int company_Id, int product_Id) {
        this(0, buyer_Id, company_Id, product_Id, null);
    }

    public int getWishlist_Id() {
        return wishlist_Id;
    }

    public void setWishlist_Id(int wishlist_Id) {
        this.wishlist_Id = wishlist_Id;
    }

    public int getCompany_Id() {
        return company_Id;
    }

    public void setCompany_Id(int company_Id) {
        this.company_Id = company_Id;
    }

    public int getBuyer_Id() {
        return buyer_Id;
    }

    public void setBuyer_Id(int buyer_Id) {
        this.buyer_Id = buyer_Id;
    }

    public int getProduct_Id() {
        return product_Id;
    }

    public void setProduct_Id(int product_Id) {
        this.product_Id = product_Id;
    }

    public Timestamp getAdded_on() {
        return added_on;
    }

    public void setAdded_on(Timestamp added_on) {
        this.added_on = added_on;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
        if (product != null) {
            this.product_Id = product.getProduct_Id();
        }
    }

    @Override
    public String toString() {
        String productName = (product != null) ? product.getProduct_Name() : "N/A";
        return "Wishlist{" +
                "wishlist_Id=" + wishlist_Id +
                ", company_Id=" + company_Id +
                ", buyer_Id=" + buyer_Id +
                ", product_Id=" + product_Id +
                ", added_on=" + added_on +
                ", productName='" + productName + '\'' +
                '}';
    }
}
