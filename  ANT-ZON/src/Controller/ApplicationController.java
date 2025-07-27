package Controller;

import Constants.Message;
import Util.PrintUtil;
import Util.Scanner_Singleton;
import java.sql.SQLException;
import java.util.Scanner;

public class ApplicationController {
    Scanner inputscanner = Scanner_Singleton.getInstance().getScanner();

    public void run() throws SQLException {
        while (true) {
            PrintUtil.printMessages(
                Message.WELCOME,
                Message.MENU,
                Message.SELECT_OPTION
            );

            try {
                int inputUser = Integer.parseInt(inputscanner.nextLine());
                switch (inputUser) {
                    case 1:
                        CompanyController.startCompanySelection(inputscanner);
                        break;

                    case 2:
                        PrintUtil.printMessage(Message.EXIT_MESSAGE);
                        inputscanner.close();
                        System.exit(0);
                        break;

                    default:
                        PrintUtil.printMessage(Message.INVALID_INPUT);
                        break;
                }

            } catch (NumberFormatException e) {
                PrintUtil.printMessageWithException(Message.INVALID_INPUT, e);
            }
        }
    }
}
