package Modal;

import Constants.Message;

public class Add_Cart {

    private int cart_Item_ID;   
    private int buyer_ID;
    private int product_ID;
    private int company_ID;
    private int cart_Quantity;
    private Product product;

    public Add_Cart() {}

    public Add_Cart(int cart_Item_ID, int buyer_ID, int product_ID, int company_ID, int cart_Quantity) {
        this.cart_Item_ID = cart_Item_ID;
        this.buyer_ID = buyer_ID;
        this.product_ID = product_ID;
        this.company_ID = company_ID;
        this.cart_Quantity = cart_Quantity;
    }

    public Add_Cart(int buyer_ID, int product_ID, int company_ID, int cart_Quantity) {
        this(0, buyer_ID, product_ID, company_ID, cart_Quantity);
    }

    public Add_Cart(int cart_Item_ID, int buyer_ID, Product product, int company_ID, int cart_Quantity) {
        this.cart_Item_ID = cart_Item_ID;
        this.buyer_ID = buyer_ID;
        this.company_ID = company_ID;
        this.cart_Quantity = cart_Quantity;
        setProduct(product); 
    }

    

    public int getCart_Item_ID() {
        return cart_Item_ID;
    }

    public void setCart_Item_ID(int cart_Item_ID) {
        this.cart_Item_ID = cart_Item_ID;
    }

    public int getBuyer_ID() {
        return buyer_ID;
    }

    public void setBuyer_ID(int buyer_ID) {
        this.buyer_ID = buyer_ID;
    }

    public int getProduct_ID() {
        return product_ID;
    }

    public void setProduct_ID(int product_ID) {
        this.product_ID = product_ID;
    }

    public int getCompany_ID() {
        return company_ID;
    }

    public void setCompany_ID(int company_ID) {
        this.company_ID = company_ID;
    }

    public int getCart_Quantity() {
        return cart_Quantity;
    }

    public void setCart_Quantity(int cart_Quantity) {
        if (cart_Quantity < 0) {
            throw new IllegalArgumentException();
        }
        this.cart_Quantity = cart_Quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
        if (product != null) {
            this.product_ID = product.getProduct_Id();
        }
    }

    public double getLineTotal() {
        if (product != null) {
            return product.getProduct_Price() * cart_Quantity;
        }
        return 0.0;
    }

    @Override
    public String toString() {
        String productName = (product != null) ? product.getProduct_Name() : "N/A";
        double unitPrice = (product != null) ? product.getProduct_Price() : 0.0;
        return "Add_Cart{" +
                "cart_Item_ID=" + cart_Item_ID +
                ", buyer_ID=" + buyer_ID +
                ", product_ID=" + product_ID +
                ", company_ID=" + company_ID +
                ", cart_Quantity=" + cart_Quantity +
                ", productName='" + productName + '\'' +
                ", unitPrice=" + unitPrice +
                ", lineTotal=" + String.format("%.2f", getLineTotal()) +
                '}';
    }

    
}
