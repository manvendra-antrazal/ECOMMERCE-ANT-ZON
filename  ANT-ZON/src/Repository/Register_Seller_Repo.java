package Repository;

import Constants.Message;
import Constants.Queries;
import Modal.Company;
import Util.DBConnection;
import Util.PrintUtil;
import Util.Validations;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Register_Seller_Repo {

    public boolean REGISTER_SELLER(Scanner inputscanner, String role, Company company) {

        PrintUtil.printMessage(Message.ENTER_NEW_SELLER_NAME);
        String name = inputscanner.nextLine();

        String username;
        while (true) {
            PrintUtil.printMessage(Message.ENTER_NEW_SELLER_USERNAME);
            username = inputscanner.nextLine().trim();

            if (Validations.isValidUsername(username)) {
                break;
            } else {
                PrintUtil.printMessage(Message.USERNAME_INVALID);
            }
        }

        String password;
        while (true) {
            PrintUtil.printMessage(Message.ENTER_PASSWORD);
            password = inputscanner.nextLine();
            if (Validations.isValidPassword(password)) {
                break;
            } else {
                PrintUtil.printMessage(Message.PASSWORD_INVALID);
            }
        }

        String mobile;
        while (true) {
            PrintUtil.printMessage(Message.ENTER_MOBILE_NUM);
            mobile = inputscanner.nextLine();
            if (Validations.isValidMobile(mobile)) {
                break;
            } else {
                PrintUtil.printMessage(Message.MOBILE_INVALID);
            }
        }

        String roleStr = role;
        String query = Queries.INSERT_SELLER_QUERY;

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, mobile);
            preparedStatement.setInt(5, company.getCompany_Id());
            preparedStatement.setString(6, roleStr);

            int affected = preparedStatement.executeUpdate();
            return affected > 0;

        } catch (SQLException e) {
            PrintUtil.printMessageWithException(Message.DATABASE_ERROR, e);
            return false;
        } catch (Exception e) {
            PrintUtil.printMessageWithException(Message.UNEXPECTED_ERROR, e);
            return false;
        }
    }
}
