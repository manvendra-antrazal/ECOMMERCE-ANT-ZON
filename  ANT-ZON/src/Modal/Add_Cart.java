package Modal;

public class Add_Cart {

    private int cart_Id;
    private int cart_Quantity;

    public Add_Cart(int cart_Id, int cart_Quantity) {
        this.cart_Id = cart_Id;
        this.cart_Quantity = cart_Quantity;
    }

    public int getCart_Id() {
        return cart_Id;
    }
    public void setCart_Id(int cart_Id) {
        this.cart_Id = cart_Id;
    }
    public int getCart_Quantity() {
        return cart_Quantity;
    }
    public void setCart_Quantity(int cart_Quantity) {
        this.cart_Quantity = cart_Quantity;
    }
}
