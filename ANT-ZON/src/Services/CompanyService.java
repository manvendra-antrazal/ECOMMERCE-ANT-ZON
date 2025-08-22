package Services;

import java.sql.SQLException;
import java.util.List;

import Modal.Company;
import Repository.CompanyRepo;

public class CompanyService {

    private static final CompanyService INSTANCE = new CompanyService();
    private final CompanyRepo companyRepo;
    private CompanyService() {
        this.companyRepo = CompanyRepo.getInstance();
    }

    public static CompanyService getInstance() {
        return INSTANCE;
    }

    
    public List<Company> getAllCompanies() throws SQLException {
        try {
            return companyRepo.getAllExistingCompany();
        } catch (SQLException e) {
            throw new SQLException(e);
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }
}
