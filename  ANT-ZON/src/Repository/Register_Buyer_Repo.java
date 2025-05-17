package Repository;

import Constants.Message;
import Constants.Queries;
import Modal.Company;
import Util.DBConnection;
import Util.Validations;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;  
import java.util.Scanner;

public class Register_Buyer_Repo {

    public boolean REGISTER(Scanner inputscanner, String role, Company company){
    System.out.print("Enter Username: ");
    String username = inputscanner.nextLine();

    System.out.print("Enter Email: ");
    String email = inputscanner.nextLine();

    // Validate Password
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

    String roleStr = role;
    System.out.print("Enter City: ");
    String city = inputscanner.nextLine();
    System.out.print("Enter State: ");
    String state = inputscanner.nextLine();
    System.out.print("Enter County: ");
    String county = inputscanner.nextLine();
    System.out.print("Enter Local Address: ");
    String localAddress = inputscanner.nextLine();
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

    System.out.println(Message.REGISTER);
    String query = Queries.INSERT_BUYER;

    try {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setString(1, username);
        preparedStatement.setString(2, email);
        preparedStatement.setString(3, password);
        preparedStatement.setString(4, roleStr);
        preparedStatement.setString(5, city);
        preparedStatement.setString(6, state);
        preparedStatement.setString(7, county);
        preparedStatement.setString(8, localAddress);
        preparedStatement.setString(9, mobile);
        preparedStatement.setInt(10, company.getCompany_Id());

        int affected = preparedStatement.executeUpdate();
        return affected > 0;
    } catch (SQLException e) {
        System.out.println(e.getMessage());
        return false;
    }
}

}
