package Services;

import Constants.Message;
import Modal.Company;
import Repository.Company_Repo;
import Util.PrintUtil;
import java.sql.SQLException;
import java.util.List;

public class Company_Services {

    public static List<Company> getAllCompanies() throws SQLException {
        try {
            Company_Repo companyRepo = new Company_Repo();
            return companyRepo.getAllExistingComapny();
        } catch (SQLException e) {
            PrintUtil.printMessageWithException(Message.DB_ERROR_FETCHING_COMPANIES, e);
            throw e; 
        } catch (Exception e) {
            PrintUtil.printMessageWithException(Message.UNKNOWN_ERROR, e);
            throw new SQLException(e); 
        }
    }
}
