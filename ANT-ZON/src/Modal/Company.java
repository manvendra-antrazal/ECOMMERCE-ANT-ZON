package Modal;

public class Company {

    private int company_Id;
    private String company_Name;
    private String company_Login;
    private String company_Password;
    public Company() {
    }

    public Company(int company_Id, String company_Name) {
        this.company_Id = company_Id;
        this.company_Name = company_Name;
    }

    public Company(int company_Id, String company_Name, String company_Login, String company_Password) {
        this.company_Id = company_Id;
        this.company_Name = company_Name;
        this.company_Login = company_Login;
        this.company_Password = company_Password;
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

    public String getCompany_Login() {
        return company_Login;
    }

    public void setCompany_Login(String company_Login) {
        this.company_Login = company_Login;
    }

    public String getCompany_Password() {
        return company_Password;
    }

    public void setCompany_Password(String company_Password) {
        this.company_Password = company_Password;
    }

    @Override
    public String toString() {
        return "Company{" +
                "company_Id=" + company_Id +
                ", company_Name='" + company_Name + '\'' +
                '}';
    }

}
