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

public class Register_Buyer_Repo {

    public boolean REGISTER(Scanner inputscanner, String role, Company company) {

        String username;
        while (true) {
            PrintUtil.printMessage(Message.USERNAME);
            username = inputscanner.nextLine().trim();
            if (Validations.isValidUsername(username)) {
                break;
            } else {
                PrintUtil.printMessage(Message.USERNAME_INVALID);
            }
        }

        String email;
        while (true) {
            PrintUtil.printMessage(Message.ENETR_EMAIL);
            email = inputscanner.nextLine().trim();
            if (Validations.isValidEmail(email)) {
                break;
            } else {
                PrintUtil.printMessage(Message.EMAIL_INVALID);
            }
        }

        String password;
        while (true) {
            PrintUtil.printMessage(Message.ENETR_PASSWORD);
            password = inputscanner.nextLine();
            if (Validations.isValidPassword(password)) {
                break;
            } else {
                PrintUtil.printMessage(Message.PASSWORD_INVALID);
            }
        }

        String roleStr = role;

        String city;
        while (true) {
            PrintUtil.printMessage(Message.ENTER_CITY);
            city = inputscanner.nextLine().trim();
            if (Validations.isValidString(city)) break;
            else PrintUtil.printMessage(Message.INVALID_CITY_ERROR);
        }

        String state;
        while (true) {
            PrintUtil.printMessage(Message.ENTER_STATE);
            state = inputscanner.nextLine().trim();
            if (Validations.isValidString(state)) break;
            else PrintUtil.printMessage(Message.INVALID_STATE_ERROR);
        }

        String county;
        while (true) {
            PrintUtil.printMessage(Message.ENTER_COUNTRY);
            county = inputscanner.nextLine().trim();
            if (Validations.isValidString(county)) break;
            else PrintUtil.printMessage(Message.INVALID_COUNTRY_ERROR);
        }

        String localAddress;
        while (true) {
            PrintUtil.printMessage(Message.ENTER_ADDRESS);
            localAddress = inputscanner.nextLine().trim();
            if (Validations.isValidAddress(localAddress)) break;
            else PrintUtil.printMessage(Message.INVALID_ADDRESS_ERROR);
        }

        String mobile;
        while (true) {
            PrintUtil.printMessage(Message.ENTER_MOBILE_NUM);
            mobile = inputscanner.nextLine();
            if (Validations.isValidMobile(mobile)) break;
            else PrintUtil.printMessage(Message.MOBILE_INVALID);
        }

        PrintUtil.printMessage(Message.REGISTER);

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
            if (affected > 0) return true;
            else throw new RuntimeException(Message.REGISTRATION_FAILED);

        } catch (SQLException e) {
            PrintUtil.printMessageWithException(Message.DATABASE_ERROR, e);
            throw new RuntimeException(e.getMessage());
        } catch (Exception e) {
            PrintUtil.printMessageWithException(Message.UNEXPECTED_ERROR, e);
            throw new RuntimeException(e.getMessage());
        }
    }
}
