package Modal;

public class Sub_Category {

    private int sub_cat_ID;
    private String sub_cat_Name;
    private int category_ID;
    private int company_ID;

    public Sub_Category() {
    }

    public Sub_Category(int sub_cat_ID, String sub_cat_Name, int category_ID, int company_ID) {
        this.sub_cat_ID = sub_cat_ID;
        this.sub_cat_Name = sub_cat_Name;
        this.category_ID = category_ID;
        this.company_ID = company_ID;
    }

    public int getSub_cat_ID() {
        return sub_cat_ID;
    }

    public void setSub_cat_ID(int sub_cat_ID) {
        this.sub_cat_ID = sub_cat_ID;
    }

    public String getSub_cat_Name() {
        return sub_cat_Name;
    }

    public void setSub_cat_Name(String sub_cat_Name) {
        this.sub_cat_Name = sub_cat_Name;
    }

    public int getCategory_ID() {
        return category_ID;
    }

    public void setCategory_ID(int category_ID) {
        this.category_ID = category_ID;
    }

    public int getCompany_ID() {
        return company_ID;
    }

    public void setCompany_ID(int company_ID) {
        this.company_ID = company_ID;
    }

    @Override
    public String toString() {
        return "Sub_Category{" +
                "sub_cat_ID=" + sub_cat_ID +
                ", sub_cat_Name='" + sub_cat_Name + '\'' +
                ", category_ID=" + category_ID +
                ", company_ID=" + company_ID +
                '}';
    }

}
