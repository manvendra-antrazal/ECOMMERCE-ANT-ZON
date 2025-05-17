package Repository;

import Modal.Company;
import Util.DBConnection;
import Util.Validations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import Constants.Message;
import Constants.Queries;

public class Register_Seller_Repo {

    public boolean REGISTER_SELLER(Scanner inputscanner, String role, Company company) {
        System.out.print("Enter Name: ");
        String name = inputscanner.nextLine();
        System.out.print("Enter Username: ");
        String username = inputscanner.nextLine();

        // Password field validation
        String password;
        while (true) {
            System.out.print("Enter Password: ");
            password = inputscanner.nextLine();
            if (Validations.isValidPassword(password)) {
                break;
            } else {
                System.out.println(Message.PASSWORD_INVALID);
            }
        }

        // Mobile field validation
        String mobile;
        while (true) {
            System.out.print("Enter Mobile Number: ");
            mobile = inputscanner.nextLine();
            if (Validations.isValidMobile(mobile)) {
                break;
            } else {
                System.out.println(Message.MOBILE_INVALID);
            }
        }

        String roleStr = role;
        String query = Queries.INSERT_SELLER;

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
            System.out.println(e.getMessage());
            return false;
        }
    }
}
