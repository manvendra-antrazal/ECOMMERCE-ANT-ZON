package Modal;

public class Company {
     
    private int company_Id;
    private String company_Name;

    public Company(int company_Id, String company_Name) {
        this.company_Id = company_Id;
        this.company_Name = company_Name;
    }

    public int getCompany_Id() {
        return company_Id;
    }

    public void setCompany_Id(int company_Id) {
        this.company_Id = company_Id;
    }

    public String getCompany_Name() {
        return company_Name;
    }

    public void setCompany_Name(String company_Name) {
        this.company_Name = company_Name;
    }

    
}
