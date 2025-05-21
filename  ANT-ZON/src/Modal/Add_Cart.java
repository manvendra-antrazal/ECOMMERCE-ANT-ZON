package Modal;

public class Add_Cart {

    private int cart_Item_ID;
    private int cart_Quantity;
    private int product_ID;
    private int company_ID;

    public Add_Cart(int cart_Item_ID, int cart_Quantity, int product_ID, int company_ID) {
        this.cart_Item_ID = cart_Item_ID;
        this.cart_Quantity = cart_Quantity;
        this.product_ID = product_ID;
        this.company_ID = company_ID;
    }

    public int getCart_Item_ID() {
        return cart_Item_ID;
    }

    public void setCart_Item_ID(int cart_Item_ID) {
        this.cart_Item_ID = cart_Item_ID;
    }

    public int getCart_Quantity() {
        return cart_Quantity;
    }

    public void setCart_Quantity(int cart_Quantity) {
        this.cart_Quantity = cart_Quantity;
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
    
}
