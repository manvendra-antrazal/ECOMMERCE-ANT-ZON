package Repository;

import Constants.Message;
import Constants.Queries;
import Modal.Company;
import Util.DBConnection;
import Util.PrintUtil;
import Util.Validations;
import View.SellerView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Register_Seller_Repo {

    public boolean REGISTER_SELLER(Scanner inputScanner, String role, Company company) {
        PrintUtil.printMessage(Message.ENTER_NEW_SELLER_NAME);
        String name = inputScanner.nextLine().trim();

        String username = readValidUsername(inputScanner);
        String password = readValidPassword(inputScanner);
        String mobile = readValidMobile(inputScanner);

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(Queries.INSERT_SELLER_QUERY)) {

            ps.setString(1, name);
            ps.setString(2, username);
            ps.setString(3, password);
            ps.setString(4, mobile);
            ps.setInt(5, company.getCompany_Id());
            ps.setString(6, role);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            throw new RuntimeException(Message.DATABASE_ERROR);
        } catch (Exception e) {
            throw new RuntimeException(Message.UNEXPECTED_ERROR);
        }
    }

    private String readValidUsername(Scanner scanner) {
        while (true) {
            PrintUtil.printMessage(Message.ENTER_NEW_SELLER_USERNAME);
            String input = scanner.nextLine().trim();
            if (Validations.isValidUsername(input)) {
                return input;
            } else {
                SellerView.showMessage(Message.USERNAME_INVALID);
            }
        }
    }

    private String readValidPassword(Scanner scanner) {
        while (true) {
            PrintUtil.printMessage(Message.ENTER_PASSWORD);
            String input = scanner.nextLine().trim();
            if (Validations.isValidPassword(input)) {
                return input;
            } else {
                SellerView.showMessage(Message.PASSWORD_INVALID);
            }
        }
    }

    private String readValidMobile(Scanner scanner) {
        while (true) {
            PrintUtil.printMessage(Message.ENTER_MOBILE_NUM);
            String input = scanner.nextLine().trim();
            if (Validations.isValidMobile(input)) {
                return input;
            } else {
                SellerView.showMessage(Message.MOBILE_INVALID);
            }
        }
    }
}
