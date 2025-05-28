package Controller;

import Constants.Message;
import Util.Scanner_Singleton;
import java.sql.SQLException;
import java.util.Scanner;

public class ApplicationController {
    Scanner inputscanner = Scanner_Singleton.getInstance().getScanner();

    public void run() throws SQLException {
        while (true) {
            System.out.println();
            System.out.println(Message.WELCOME);
            System.out.println(Message.MENU);
            System.out.print(Message.SELECT_OPTION);

            try {
                int inputUser = Integer.parseInt(inputscanner.nextLine());

                switch (inputUser) {
                    case 1:
                        CompanyController.startCompanySelection(inputscanner);
                        break;

                    case 2:
                        System.out.println(Message.EXIT_MESSAGE);
                        inputscanner.close();
                        System.exit(0);
                        break;

                    default:
                        System.out.println(Message.INVALID_INPUT);
                        break;
                }

            } catch (NumberFormatException e) {
                System.out.println(Message.INVALID_INPUT);
            }
        }
    }
}
