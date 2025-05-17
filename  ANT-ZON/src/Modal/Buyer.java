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

    public Buyer(String city, int companyID, String county, String localAddress, String mobileNo, String role, String state, String userEmail, int userID, String userName, String userPsd) {
        this.city = city;
        this.companyID = companyID;
        this.county = county;
        this.localAddress = localAddress;
        this.mobileNo = mobileNo;
        this.role = role;
        this.state = state;
        this.userEmail = userEmail;
        this.userID = userID;
        this.userName = userName;
        this.userPsd = userPsd;
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
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPsd() {
        return userPsd;
    }

    public void setUserPsd(String userPsd) {
        this.userPsd = userPsd;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getLocalAddress() {
        return localAddress;
    }

    public void setLocalAddress(String localAddress) {
        this.localAddress = localAddress;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public int getCompanyID() {
        return companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Buyer{");
        sb.append("userID=").append(userID);
        sb.append(", userName=").append(userName);
        sb.append(", userEmail=").append(userEmail);
        sb.append(", userPsd=").append(userPsd);
        sb.append(", role=").append(role);
        sb.append(", city=").append(city);
        sb.append(", state=").append(state);
        sb.append(", county=").append(county);
        sb.append(", localAddress=").append(localAddress);
        sb.append(", mobileNo=").append(mobileNo);
        sb.append(", companyID=").append(companyID);
        sb.append('}');
        return sb.toString();
    }

    
    
}
