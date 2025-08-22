package Controller;

import Util.Scanner_Singleton;
import View.ApplicationView;
import java.sql.SQLException;
import java.util.Scanner;

public class ApplicationController {

    private final Scanner inputScanner = Scanner_Singleton.getInstance().getScanner();
    private final ApplicationView appView = new ApplicationView();
    public void run() {
        boolean running = true;

        while (running) {
            appView.showMainMenu();

            String line = inputScanner.nextLine().trim();
            int inputUser;
            try {
                inputUser = Integer.parseInt(line);
            } catch (NumberFormatException e) {
                appView.showInvalidInputFormat();
                continue;
            }

            switch (inputUser) {
                case 1:
                    try {
                        CompanyController.startCompanySelection(inputScanner);
                    } catch (SQLException e) {
                        throw new RuntimeException();
                    } catch (Exception e) {
                        throw new RuntimeException();
                    }
                    break;

                case 2:
                    appView.showExitMessage();
                    System.exit(0);
                    break;

                default:
                    appView.showInvalidOption();
                    break;
            }
        }
    }
}
