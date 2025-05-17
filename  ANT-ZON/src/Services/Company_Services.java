package Services;

import Modal.Company;
import Repository.Company_Repo;
import java.util.List;

public class Company_Services {
    
    public static List<Company> getAllCompanies() {
        Company_Repo companyRepo = new Company_Repo();
        return companyRepo.getAllExistingComapny();
    }
}