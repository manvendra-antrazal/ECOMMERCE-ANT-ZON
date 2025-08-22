package Controller;

import Modal.Company;
import Services.CompanyService;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CompanyController {

    private static final CompanyController instance = new CompanyController();
    private static final CompanyService companyService = CompanyService.getInstance();

    private CompanyController() {}

    public static CompanyController getInstance() {
        return instance;
    }

    public List<Company> getAllCompanies() throws SQLException {
        return companyService.getAllCompanies();
    }

    public Company getCompanyByIndex(List<Company> companies, int index) {
        if (index >= 0 && index < companies.size()) {
            return companies.get(index);
        }
        return null;
    }

    public static void startCompanySelection(Scanner inputScanner) throws SQLException {
    List<Company> companies = companyService.getAllCompanies();
    if (companies == null || companies.isEmpty()) {
        View.CompanyView.printNoCompanyFound();
        return;
    }

    Map<Integer, Company> map = View.CompanyView.displayCompanies(companies);
    View.CompanyView.promptSelectOption();
    String choice = inputScanner.nextLine().trim();
    int idx;
    try {
        idx = Integer.parseInt(choice);
    } catch (NumberFormatException e) {
        View.CompanyView.printInvalidInput();
        return;
    }

    Company company = map.get(idx);
    if (company == null) {
        View.CompanyView.printInvalidInput();
        return;
    }

    View.CompanyView.printRoleSelection();
    String role = inputScanner.nextLine().trim().toUpperCase();

    switch (role) {
        case "ADMIN":
            View.AdminView.loginOrRegister(company);
            break;

        case "BUYER":
            BuyerController.handleBuyerFlow(inputScanner, company);
            break;

        case "SELLER":
            SellerController.handleSellerFlow(inputScanner, company);
            break;

        default:
            View.CompanyView.printInvalidRoleSelection();
            break;
    }
}

}
