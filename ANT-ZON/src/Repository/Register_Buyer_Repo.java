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

    public boolean REGISTER(Scanner inputScanner, String role, Company company) {
        String username = readUsername(inputScanner);
        String email = readEmail(inputScanner);
        String password = readPassword(inputScanner);
        String city = readCity(inputScanner);
        String state = readState(inputScanner);
        String country = readCountry(inputScanner);
        String localAddress = readAddress(inputScanner);
        String mobile = readMobile(inputScanner);

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(Queries.INSERT_BUYER_QUERY)) {

            ps.setString(1, username);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setString(4, role);
            ps.setString(5, city);
            ps.setString(6, state);
            ps.setString(7, country);
            ps.setString(8, localAddress);
            ps.setString(9, mobile);
            ps.setInt(10, company.getCompany_Id());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) return true;
            throw new RuntimeException(Message.REGISTRATION_FAILED);

        } catch (SQLException e) {
            throw new RuntimeException(Message.DATABASE_ERROR);
        } catch (Exception e) {
            throw new RuntimeException(Message.UNEXPECTED_ERROR);
        }
    }

    // ---- Separate methods for each input ----
    private String readUsername(Scanner scanner) {
        while (true) {
            PrintUtil.printMessage(Message.USERNAME);
            String input = scanner.nextLine().trim();
            if (Validations.isValidUsername(input)) return input;
            PrintUtil.printMessage(Message.USERNAME_INVALID);
        }
    }

    private String readEmail(Scanner scanner) {
        while (true) {
            PrintUtil.printMessage(Message.ENETR_EMAIL);
            String input = scanner.nextLine().trim();
            if (Validations.isValidEmail(input)) return input;
            PrintUtil.printMessage(Message.EMAIL_INVALID);
        }
    }

    private String readPassword(Scanner scanner) {
        while (true) {
            PrintUtil.printMessage(Message.ENETR_PASSWORD);
            String input = scanner.nextLine().trim();
            if (Validations.isValidPassword(input)) return input;
            PrintUtil.printMessage(Message.PASSWORD_INVALID);
        }
    }

    private String readCity(Scanner scanner) {
        while (true) {
            PrintUtil.printMessage(Message.ENTER_CITY);
            String input = scanner.nextLine().trim();
            if (Validations.isValidString(input)) return input;
            PrintUtil.printMessage(Message.INVALID_CITY_ERROR);
        }
    }

    private String readState(Scanner scanner) {
        while (true) {
            PrintUtil.printMessage(Message.ENTER_STATE);
            String input = scanner.nextLine().trim();
            if (Validations.isValidString(input)) return input;
            PrintUtil.printMessage(Message.INVALID_STATE_ERROR);
        }
    }

    private String readCountry(Scanner scanner) {
        while (true) {
            PrintUtil.printMessage(Message.ENTER_COUNTRY);
            String input = scanner.nextLine().trim();
            if (Validations.isValidString(input)) return input;
            PrintUtil.printMessage(Message.INVALID_COUNTRY_ERROR);
        }
    }

    private String readAddress(Scanner scanner) {
        while (true) {
            PrintUtil.printMessage(Message.ENTER_ADDRESS);
            String input = scanner.nextLine().trim();
            if (Validations.isValidAddress(input)) return input;
            PrintUtil.printMessage(Message.INVALID_ADDRESS_ERROR);
        }
    }

    private String readMobile(Scanner scanner) {
        while (true) {
            PrintUtil.printMessage(Message.ENTER_MOBILE_NUM);
            String input = scanner.nextLine().trim();
            if (Validations.isValidMobile(input)) return input;
            PrintUtil.printMessage(Message.MOBILE_INVALID);
        }
    }
}
