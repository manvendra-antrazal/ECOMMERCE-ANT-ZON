package Modal;

import java.sql.Timestamp;


public class Admin {

    private int adminId;
    private int companyId;
    private String username;
    private String password;
    private Timestamp createdAt;
    public Admin() {
    }

    public Admin(int adminId, int companyId, String username, String password, Timestamp createdAt) {
        this.adminId = adminId;
        this.companyId = companyId;
        this.username = username;
        this.password = password;
        this.createdAt = createdAt;
    }

    public Admin(int companyId, String username, String password) {
        this.companyId = companyId;
        this.username = username;
        this.password = password;
    }

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
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Admin username cannot be null or empty");
        }
        this.username = username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Admin password cannot be null or empty");
        }
        this.password = password;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

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
