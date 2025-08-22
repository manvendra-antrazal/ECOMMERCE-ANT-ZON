package Services;

import java.util.List;

import Constants.Message;
import Modal.Company;
import Modal.Product;
import Repository.AdminRepository;
import View.AdminView;



public class AdminService {
    private static AdminService instance;
    private final AdminRepository adminRepo = AdminRepository.getInstance();

    private AdminService() {}

    public static AdminService getInstance() {
        if (instance == null) {
            instance = new AdminService();
        }
        return instance;
    }

    public Integer loginAdmin(String username, String password, int companyId) {
        return adminRepo.validateAdminLogin(username, password, companyId);
    }

    public boolean registerAdmin(String username, String password, int companyId) {
        if (adminRepo.usernameExists(username, companyId)) {
            throw new IllegalArgumentException(Message.USERNAME_EXISTS);
        }
        return adminRepo.registerNewAdmin(username, password, companyId);
    }

    public double getTotalRevenue(int companyId) {
        return adminRepo.fetchTotalRevenue(companyId);
    }

    public List<Product> getMostLikedProducts(int companyId) {
        return adminRepo.fetchMostLikedProducts(companyId);
    }

    public List<Product> getBestSellerProducts(int companyId) {
        return adminRepo.fetchBestSellerProducts(companyId);
    }

    public void showCompanyName(Company company) {
        AdminView.showCompanyName(company);
    }
}
