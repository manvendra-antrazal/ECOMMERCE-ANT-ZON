package Modal;

public class Product {
    private int product_Id;
    private String product_Name;
    private String product_Description;
    private double product_Price;
    private int product_Quantity;
    private int company_ID;
    private int seller_ID;
    private int category_ID;
    private int sub_cat_ID;

    public Product(int product_Id, String product_Name, String product_Description, double product_Price,
               int product_Quantity, int company_ID, int seller_ID, int category_ID, int sub_cat_ID) {
    this.product_Id = product_Id;
    this.product_Name = product_Name;
    this.product_Description = product_Description;
    this.product_Price = product_Price;
    this.product_Quantity = product_Quantity;
    this.company_ID = company_ID;
    this.seller_ID = seller_ID;
    this.category_ID = category_ID; // ✅
    this.sub_cat_ID = sub_cat_ID;
}



    public int getProduct_Id() {
        return product_Id;
    }

    public void setProduct_Id(int product_Id) {
        this.product_Id = product_Id;
    }

    public String getProduct_Name() {
        return product_Name;
    }

    public void setProduct_Name(String product_Name) {
        this.product_Name = product_Name;
    }

    public String getProduct_Description() {
        return product_Description;
    }

    public void setProduct_Description(String product_Description) {
        this.product_Description = product_Description;
    }

    public double getProduct_Price() {
        return product_Price;
    }

    public void setProduct_Price(double product_Price) {
        this.product_Price = product_Price;
    }

    public int getProduct_Quantity() {
        return product_Quantity;
    }

    public void setProduct_Quantity(int product_Quantity) {
        this.product_Quantity = product_Quantity;
    }

    public int getCompany_ID() {
        return company_ID;
    }

    public void setCompany_ID(int company_ID) {
        this.company_ID = company_ID;
    }

    public int getSeller_ID() {
        return seller_ID;
    }

    public void setSeller_ID(int seller_ID) {
        this.seller_ID = seller_ID;
    }

    public int getSub_cat_ID() {
        return sub_cat_ID;
    }

    public void setSub_cat_ID(int sub_cat_ID) {
        this.sub_cat_ID = sub_cat_ID;
    }

    public int getCategory_ID() {
        return category_ID;
    }

    public void setCategory_ID(int category_ID) {
        this.category_ID = category_ID;
    }
  
    
    @Override
    public String toString() {
        return "-----------------------------------\n" +
            "Product ID     : " + product_Id + "\n" +
            "Name           : " + product_Name + "\n" +
            "Description    : " + product_Description + "\n" +
            "Price          : $" + product_Price + "\n" +
            "Quantity       : " + product_Quantity + "\n" +
            "Company ID     : " + company_ID + "\n" +
            "Seller ID      : " + seller_ID + "\n" +
            "Subcategory ID : " + sub_cat_ID + "\n" +
            "Category ID     : " + category_ID + "\n" +
            "-----------------------------------";
    }



}
