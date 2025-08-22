package Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import Constants.Message;
import Constants.Queries;
import Modal.Company;
import Util.DBConnection;

public class RegisterAdminRepo {

    private static final RegisterAdminRepo instance = new RegisterAdminRepo();
    private RegisterAdminRepo() {}

    public static RegisterAdminRepo getInstance() {
        return instance;
    }

    public boolean register(Scanner inputScanner, Company company) {
        String query = Queries.INSERT_ADMIN_QUERY;

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, company.getCompany_Name());
            ps.setString(2, company.getCompany_Login());
            ps.setString(3, company.getCompany_Password());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println(Message.FAILED_TO_REGISTER);
            return false;
        }
    }
}
