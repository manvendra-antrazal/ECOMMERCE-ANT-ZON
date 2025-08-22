package Modal;

public class Category {

    private int category_Id;
    private String category_Name;
    private int company_Id;

    public Category() {
    }

    public Category(int category_Id, String category_Name, int company_Id) {
        this.category_Id = category_Id;
        this.category_Name = category_Name;
        this.company_Id = company_Id;
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

    public int getCompany_Id() {
        return company_Id;
    }

    public void setCompany_Id(int company_Id) {
        this.company_Id = company_Id;
    }

    @Override
    public String toString() {
        return "Category{" +
                "category_Id=" + category_Id +
                ", category_Name='" + category_Name + '\'' +
                ", company_Id=" + company_Id +
                '}';
    }

}
