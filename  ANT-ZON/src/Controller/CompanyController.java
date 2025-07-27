package Controller;

import Constants.Message;
import Modal.Company;
import Services.Company_Services;
import Util.PrintUtil;
import java.sql.SQLException;
import java.util.*;

public class CompanyController {

    public static void startCompanySelection(Scanner inputscanner) throws SQLException {
        List<Company> companies = Company_Services.getAllCompanies();

        if (companies.isEmpty()) {
            PrintUtil.printMessage(Message.NO_EXISTING_COMPANY_FOUND);
            return;
        }

        Map<Integer, Company> map = new HashMap<>();
        PrintUtil.printMessage(Message.EXISTING_COMAPNY);
        for (int i = 0; i < companies.size(); i++) {
            map.put(i + 1, companies.get(i));
            System.out.printf("║  %d. %-32s ║\n", (i + 1), companies.get(i).getCompany_Name());
        }
        PrintUtil.printMessage(Message.EXISTING_COMPANY_SEPRATION);

        while (true) {
            System.out.print(Message.SELECT_OPTION);
            String input = inputscanner.nextLine().trim();

            switch (input.toUpperCase()) {
                case "A":
                    new ApplicationController().run();
                    break;
                case "B":
                    PrintUtil.printMessage(Message.EXIT_MESSAGE);
                    System.exit(0);
                    break;
                default:
                    try {
                        int choice = Integer.parseInt(input);
                        if (map.containsKey(choice)) {
                            Company selectedCompany = map.get(choice);
                            handleLoginRoles(selectedCompany, inputscanner);
                        } else {
                            PrintUtil.printMessage(Message.INVALID_INPUT);
                        }
                    } catch (NumberFormatException e) {
                        PrintUtil.printMessage(Message.INVALID_INPUT);
                    }
            }
        }
    }

    public static void handleLoginRoles(Company company, Scanner inputscanner) throws SQLException {
        PrintUtil.printMessage(Message.ROLE_SELECT);
        PrintUtil.printMessage(Message.SELECT_OPTION);

        String input = inputscanner.nextLine().trim();

        switch (input.toUpperCase()) {
            case "A":
                startCompanySelection(inputscanner);
                break;
            case "B":
                PrintUtil.printMessage(Message.EXIT_MESSAGE);
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
                            PrintUtil.printMessage(Message.INVALID_ROLE_SELECTION);
                    }
                } catch (NumberFormatException e) {
                    PrintUtil.printMessage(Message.INVALID_INPUT);
                }
        }
    }
}
