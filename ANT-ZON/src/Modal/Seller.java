package Modal;

public class Seller {

    private int seller_ID;
    private String seller_Name;
    private String seller_Username;
    private String seller_Password;
    private String role;
    private String seller_MobileNo;
    private int company_ID;

    public Seller() {
    }

    public Seller(int company_ID,
                  String role,
                  int seller_ID,
                  String seller_MobileNo,
                  String seller_Name,
                  String seller_Password,
                  String seller_Username) {
        this.company_ID = company_ID;
        this.role = role;
        this.seller_ID = seller_ID;
        this.seller_MobileNo = seller_MobileNo;
        this.seller_Name = seller_Name;
        this.seller_Password = seller_Password;
        this.seller_Username = seller_Username;
    }

    public Seller(String seller_Name,
                  String seller_Username,
                  String seller_Password,
                  String seller_MobileNo,
                  String role,
                  int company_ID) {
        this(0, role, 0, seller_MobileNo, seller_Name, seller_Password, seller_Username);
        this.company_ID = company_ID;
    }

    public int getSeller_ID() {
        return seller_ID;
    }

    public void setSeller_ID(int seller_ID) {
        this.seller_ID = seller_ID;
    }

    public String getSeller_Name() {
        return seller_Name;
    }

    public void setSeller_Name(String seller_Name) {
        this.seller_Name = seller_Name;
    }

    public String getSeller_Username() {
        return seller_Username;
    }

    public void setSeller_Username(String seller_Username) {
        if (seller_Username == null || seller_Username.trim().isEmpty()) {
            throw new IllegalArgumentException("Seller username cannot be null or empty");
        }
        this.seller_Username = seller_Username.trim();
    }

    public String getSeller_Password() {
        return seller_Password;
    }

    public void setSeller_Password(String seller_Password) {
        if (seller_Password == null || seller_Password.trim().isEmpty()) {
            throw new IllegalArgumentException("Seller password cannot be null or empty");
        }
        this.seller_Password = seller_Password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        if (role == null || role.trim().isEmpty()) {
            throw new IllegalArgumentException("Role cannot be null or empty");
        }
        this.role = role.trim();
    }

    public String getSeller_MobileNo() {
        return seller_MobileNo;
    }

    public void setSeller_MobileNo(String seller_MobileNo) {
        if (seller_MobileNo == null || seller_MobileNo.trim().isEmpty()) {
            throw new IllegalArgumentException("Mobile number cannot be null or empty");
        }
        this.seller_MobileNo = seller_MobileNo.trim();
    }

    public int getCompany_ID() {
        return company_ID;
    }

    public void setCompany_ID(int company_ID) {
        this.company_ID = company_ID;
    }

    // ===== Utility =====

    @Override
    public String toString() {
        return "Seller{" +
                "seller_ID=" + seller_ID +
                ", seller_Name='" + seller_Name + '\'' +
                ", seller_Username='" + seller_Username + '\'' +
                ", role='" + role + '\'' +
                ", seller_MobileNo='" + seller_MobileNo + '\'' +
                ", company_ID=" + company_ID +
                '}';
    }


}
