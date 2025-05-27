package Modal;

import java.sql.Timestamp;

public class Admin {

    private int adminId;
    private int companyId;
    private String username;
    private String password;
    private Timestamp createdAt;

    // Constructors
    public Admin() {
    }

    public Admin(int adminId, int companyId, String username, String password, Timestamp createdAt) {
        this.adminId = adminId;
        this.companyId = companyId;
        this.username = username;
        this.password = password;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    // Optional: toString method for logging/debugging
    @Override
    public String toString() {
        return "Admin{" +
                "adminId=" + adminId +
                ", companyId=" + companyId +
                ", username='" + username + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
