package Modal;

public class Product {
    
    private int product_Id;
    private String product_Name;
    private String product_Info;
    private double product_Price;
    private int product_Quantity;

    public Product(int product_Id, String product_Info, String product_Name, double product_Price, int product_Quantity) {
        this.product_Id = product_Id;
        this.product_Info = product_Info;
        this.product_Name = product_Name;
        this.product_Price = product_Price;
        this.product_Quantity = product_Quantity;
    }

    public int getProduct_Id() {
        return product_Id;
    }

    public String getProduct_Name() {
        return product_Name;
    }

    public String getProduct_Info() {
        return product_Info;
    }

    public double getProduct_Price() {
        return product_Price;
    }

    public int getProduct_Quantity() {
        return product_Quantity;
    }

    @Override
    public String toString() {
        return "Product [product_Id=" + product_Id + ", product_Name=" + product_Name + ", product_Info=" + product_Info
                + ", product_Price=" + product_Price + ", product_Quantity=" + product_Quantity + "]";
    }   
   
}
