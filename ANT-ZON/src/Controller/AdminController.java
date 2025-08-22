package Controller;

import java.util.List;

import Modal.Company;
import Modal.Product;
import Services.AdminService;


public class AdminController {

    private static final AdminController instance = new AdminController();
    private static final AdminService adminService = AdminService.getInstance();
    private AdminController() {
    }

    public static AdminController getInstance() {
        return instance;
    }

    public Integer loginAdmin(String username, String password, int companyId) {
        return adminService.loginAdmin(username, password, companyId);
    }

    public boolean registerAdmin(String username, String password, int companyId) {
        return adminService.registerAdmin(username, password, companyId);
    }

    public double getTotalRevenue(int companyId) {
        return adminService.getTotalRevenue(companyId);
    }

    public List<Product> getMostLikedProducts(int companyId) {
        return adminService.getMostLikedProducts(companyId);
    }

    public List<Product> getBestSellerProducts(int companyId) {
        return adminService.getBestSellerProducts(companyId);
    }

    public void showCompanyName(Company company) {
        adminService.showCompanyName(company);
    }
}
