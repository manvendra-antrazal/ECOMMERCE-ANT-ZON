package View;

import Constants.Message;
import Controller.BuyerController;
import Modal.Company;
import Util.PrintUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class CompanyView {

    private static final Scanner sc = new Scanner(System.in);

    private static void printLine(String s) {
        try {
            PrintUtil.printMessage(s);
        } catch (Throwable t) {
            System.out.println(s);
        }
    }

    public static void printNoCompanyFound() {
        System.out.println(Message.NO_EXISTING_COMPANY_FOUND);
    }

    public static Map<Integer, Company> displayCompanies(List<Company> companies) {
        Map<Integer, Company> map = new HashMap<>();
        if (companies == null || companies.isEmpty()) {
            printNoCompanyFound();
            return map;
        }

        for (int i = 0; i < companies.size(); i++) {
            Company c = companies.get(i);
            map.put(i + 1, c);
            System.out.printf("║ %2d. %-50s ║%n", (i + 1), safe(c.getCompany_Name()));
        }
        return map;
    }

    public static void promptSelectOption() {
        System.out.print(Message.SELECT_OPTION);
    }

    public static void printExitMessage() {
        printLine(Message.EXIT_MESSAGE);
    }

    public static void printInvalidInput() {
        printLine(Message.INVALID_OPTION);
    }

    public static void printRoleSelection() {
        System.out.print(Message.ROLE_SELECT);
    }

    public static void printInvalidRoleSelection() {
        printLine(Message.INVALID_ROLE_SELECTION);
    }

    public static void routeByRole(Scanner scanner, Company company) {
        printRoleSelection();
        String role = scanner.nextLine().trim().toUpperCase();
        switch (role) {
            case "ADMIN":
                View.AdminView.loginOrRegister(company);
                break;
            case "BUYER":
                BuyerController.handleBuyerFlow(scanner, company);
                break;
            case "SELLER":
                System.out.println("Launching Seller flow...");
                break;
            default:
                printInvalidRoleSelection();
                break;
        }
    }

    private static String safe(String s) {
        return s == null ? "N/A" : s;
    }
}
