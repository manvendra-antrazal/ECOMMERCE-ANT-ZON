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

    String username;
    while (true) {
        System.out.print(Message.USERNAME);
        username = inputscanner.nextLine().trim();

        if (Validations.isValidUsername(username)) {
            break;
        } else {
            System.out.println(Message.USERNAME_INVALID);
        }
    }

     String email;
    while (true) {
        System.out.print(Message.ENETR_EMAIL);
        email = inputscanner.nextLine().trim();

        if (Validations.isValidEmail(email)) {
            break;
        } else {
            System.out.println(Message.EMAIL_INVALID);
        }
    }

    // Validate Password
    String password;
    while (true) {
        System.out.print(Message.ENETR_PASSWORD);
        password = inputscanner.nextLine();
        if (Validations.isValidPassword(password)) {
            break;
        } else {
            System.out.println(Message.PASSWORD_INVALID);
        }
    }

    String roleStr = role;

    String city;
    while (true) {
        System.out.print(Message.ENTER_CITY);
        city = inputscanner.nextLine().trim();
        if (Validations.isValidString(city))break;
        else System.out.println(Message.INVALID_CITY_ERROR);
    }

    String state;
    while (true) {
        System.out.print(Message.ENTER_STATE);
        state = inputscanner.nextLine().trim();
        if (Validations.isValidString(state)) break;
        else System.out.println(Message.INVALID_STATE_ERROR);
    }

    String county;
    while (true) {
        System.out.print(Message.ENTER_COUNTRY);
        county = inputscanner.nextLine().trim();
        if (Validations.isValidString(county)) break;
        else System.out.println(Message.INVALID_COUNTRY_ERROR);
    }

    String localAddress;
    while (true) {
        System.out.print(Message.ENTER_ADDRESS);
        localAddress = inputscanner.nextLine().trim();
        if (Validations.isValidAddress(localAddress)) break;
        else System.out.println(Message.INVALID_ADDRESS_ERROR);
    }
    // Mobile field validation
    String mobile;
    while (true) {
        System.out.print(Message.ENTER_MOBILE_NUM);
        mobile = inputscanner.nextLine();
        if (Validations.isValidMobile(mobile)) {
            break;
        } else {
            System.out.println(Message.MOBILE_INVALID);
            }
        }

    System.out.println(Message.REGISTER);
    String query = Queries.INSERT_BUYER_QUERY;

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
