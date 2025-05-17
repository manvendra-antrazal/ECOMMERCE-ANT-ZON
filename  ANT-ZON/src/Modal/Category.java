package Modal;

public class Category {

    private int category_Id;
    private String category_Name;

    public Category(int category_Id, String category_Name) {
        this.category_Id = category_Id;
        this.category_Name = category_Name;
    }

    public int getCategory_Id() {
        return category_Id;
    }

    public void setCategory_Id(int category_Id) {
        this.category_Id = category_Id;
    }

    public String getCategory_Name() {
        return category_Name;
    }

    public void setCategory_Name(String category_Name) {
        this.category_Name = category_Name;
    }
}
