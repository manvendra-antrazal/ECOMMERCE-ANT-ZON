package Controller;

import Constants.Message;
import Modal.Company;
import Services.Company_Services;
import java.sql.SQLException;
import java.util.*;

public class CompanyController {

    public static void startCompanySelection(Scanner inputscanner) throws SQLException {
        List<Company> companies = Company_Services.getAllCompanies();

        if (companies.isEmpty()) {
            System.out.println(Message.NO_EXISTING_COMPANY_FOUND);
            return;
        }

        Map<Integer, Company> map = new HashMap<>();
        System.out.println(Message.EXISTING_COMAPNY);
        for (int i = 0; i < companies.size(); i++) {
        map.put(i + 1, companies.get(i));
        System.out.printf("║  %d. %-32s ║\n", (i + 1), companies.get(i).getCompany_Name());
    }
        System.out.println(Message.EXISTING_COMPANY_SEPRATION);
        // System.out.println(Message.BACK_AND_EXIT_FRAME);
        // System.out.println(Message.EXISTING_COMPANY_SEPRATION);

        while (true) {
            System.out.print(Message.SELECT_OPTION);
            String input = inputscanner.nextLine().trim();

            switch (input.toUpperCase()) {
                case "A":
                    new ApplicationController().run();
                    break;
                case "B":
                    System.out.println(Message.EXIT_MESSAGE);
                    System.exit(0);
                    break;
                default:
                    try {
                        int choice = Integer.parseInt(input);
                        if (map.containsKey(choice)) {
                            Company selectedCompany = map.get(choice);
                            handleLoginRoles(selectedCompany, inputscanner);
                        } else {
                            System.out.println(Message.INVALID_INPUT);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(Message.INVALID_INPUT);
                    }
            }
        }
    }

    public static void handleLoginRoles(Company company, Scanner inputscanner) throws SQLException {
        System.out.println("\n" + Message.ROLE_SELECT);
        System.out.print(Message.SELECT_OPTION);

        String input = inputscanner.nextLine().trim();

        switch (input.toUpperCase()) {
            case "A":
                startCompanySelection(inputscanner);
                break;
            case "B":
                System.out.println(Message.EXIT_MESSAGE);
                System.exit(0);
                break;
            default:
                try {
                    int role = Integer.parseInt(input);
                    switch (role) {
                        case 1:
                            BuyerController.handleBuyerFlow(inputscanner, company);
                            break;
                        case 2:
                            SellerController.handleSellerFlow(inputscanner, company);
                            break;
                        case 3:
                            Admin_Controller.handleAdminFlow(inputscanner, company);
                            break;
                        default:
                            System.out.println(Message.INVALID_ROLE_SELECTION);
                    }
                } catch (NumberFormatException e) {
                    System.out.println(Message.INVALID_INPUT);
                }
        }
    }
}
