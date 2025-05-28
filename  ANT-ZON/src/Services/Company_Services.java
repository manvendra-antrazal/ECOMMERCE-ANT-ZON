package Services;

import Constants.Message;
import Modal.Company;
import Repository.Company_Repo;
import java.util.List;
import java.sql.SQLException;

public class Company_Services {
    
    public static List<Company> getAllCompanies() throws SQLException {
        try {
            Company_Repo companyRepo = new Company_Repo();
            return companyRepo.getAllExistingComapny();
        } catch (SQLException e) {
            System.out.println(Message.DB_ERROR_FETCHING_COMPANIES);
            throw e; // rethrowing to propagate the error
        } catch (Exception e) {
            System.out.println(Message.UNKNOWN_ERROR);
            throw new SQLException(e); // wrap and rethrow as SQLException to match method signature
        }
    }
}
