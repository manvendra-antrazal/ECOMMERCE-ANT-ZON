package Modal;

public class Buyer {

    private int userID;
    private String userName;
    private String userEmail;
    private String userPsd;       
    private String role;          
    private String city;
    private String state;
    private String county;        
    private String localAddress;
    private String mobileNo;
    private int companyID;

    public Buyer() {
    }

    public Buyer(int userID,
                 String userName,
                 String userEmail,
                 String userPsd,
                 String role,
                 String city,
                 String state,
                 String county,
                 String localAddress,
                 String mobileNo,
                 int companyID) {
        this.userID = userID;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPsd = userPsd;
        this.role = role;
        this.city = city;
        this.state = state;
        this.county = county;
        this.localAddress = localAddress;
        this.mobileNo = mobileNo;
        this.companyID = companyID;
    }

    public Buyer(String userName,
                 String userEmail,
                 String userPsd,
                 String role,
                 String city,
                 String state,
                 String county,
                 String localAddress,
                 String mobileNo,
                 int companyID) {
        this(0, userName, userEmail, userPsd, role, city, state, county, localAddress, mobileNo, companyID);
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        if (userName == null || userName.trim().isEmpty()) {
            throw new IllegalArgumentException("Buyer username cannot be null or empty");
        }
        this.userName = userName.trim();
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        if (userEmail == null || userEmail.trim().isEmpty()) {
            throw new IllegalArgumentException("Buyer email cannot be null or empty");
        }
        this.userEmail = userEmail.trim();
    }

    public String getUserPsd() {
        return userPsd;
    }

    public void setUserPsd(String userPsd) {
        if (userPsd == null || userPsd.trim().isEmpty()) {
            throw new IllegalArgumentException("Buyer password cannot be null or empty");
        }
        this.userPsd = userPsd;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        if (role == null || role.trim().isEmpty()) {
            throw new IllegalArgumentException("Buyer role cannot be null or empty");
        }
        this.role = role.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        if (city != null) {
            city = city.trim();
        }
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        if (state != null) {
            state = state.trim();
        }
        this.state = state;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        if (county != null) {
            county = county.trim();
        }
        this.county = county;
    }

    public String getLocalAddress() {
        return localAddress;
    }

    public void setLocalAddress(String localAddress) {
        if (localAddress != null) {
            localAddress = localAddress.trim();
        }
        this.localAddress = localAddress;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        if (mobileNo == null || mobileNo.trim().isEmpty()) {
            throw new IllegalArgumentException("Buyer mobile number cannot be null or empty");
        }
        this.mobileNo = mobileNo.trim();
    }

    public int getCompanyID() {
        return companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }

    @Override
    public String toString() {
        return "Buyer{" +
                "userID=" + userID +
                ", userName='" + userName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", role='" + role + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", county='" + county + '\'' +
                ", localAddress='" + localAddress + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", companyID=" + companyID +
                '}';
    }


}
