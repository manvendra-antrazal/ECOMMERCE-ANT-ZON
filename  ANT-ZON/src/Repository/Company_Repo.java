package Repository;


import Constants.Message;
import Constants.Queries;
import Modal.Company;
import Util.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class Company_Repo {

    // this method has Company list which stores all the companies
    public List<Company> getAllExistingComapny() throws SQLException {

        List<Company> existingCompanyList = new ArrayList<>();
        String query = Queries.GET_ALL_EXISTING_COMPANY;

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultset = statement.executeQuery(query);

            while (resultset.next()) {
                // company_Name, company_Login, company_psd
                int id = resultset.getInt("company_ID");
                String name = resultset.getString("company_Name");
                Company company = new Company(id, name);
                existingCompanyList.add(company);
            }

        } catch (SQLException e) {
            throw new SQLException(Message.FETCHING_FAILED + e.getMessage());
        }

        return existingCompanyList;
    }
}

