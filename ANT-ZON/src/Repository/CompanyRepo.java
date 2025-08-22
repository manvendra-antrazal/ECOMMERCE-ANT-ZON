package Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Constants.Message;
import Constants.Queries;
import Modal.Company;
import Util.DBConnection;

public class CompanyRepo {

    private static final CompanyRepo instance = new CompanyRepo();

    private CompanyRepo() {}

    public static CompanyRepo getInstance() {
        return instance;
    }

    public List<Company> getAllExistingCompany() throws SQLException {
        List<Company> existingCompanyList = new ArrayList<>();
        String query = Queries.GET_ALL_EXISTING_COMPANY;
        try (Connection connection = DBConnection.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("company_ID");
                String name = resultSet.getString("company_Name");
                Company company = new Company(id, name);
                existingCompanyList.add(company);
            }
        } catch (SQLException e) {
            throw new SQLException(Message.FETCHING_FAILED + ": " + e.getMessage(), e);
        }
        return existingCompanyList;
    }
}
