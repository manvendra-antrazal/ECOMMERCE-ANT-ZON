package Modal;

public class Sub_Category {

    private int subCat_id;
    private String subCat_Name;
    
    public Sub_Category(int subCat_id, String subCat_Name) {
        this.subCat_id = subCat_id;
        this.subCat_Name = subCat_Name;
    }

    public int getSubCat_id() {
        return subCat_id;
    }

    public void setSubCat_id(int subCat_id) {
        this.subCat_id = subCat_id;
    }

    public String getSubCat_Name() {
        return subCat_Name;
    }

    public void setSubCat_Name(String subCat_Name) {
        this.subCat_Name = subCat_Name;
    }    
}
