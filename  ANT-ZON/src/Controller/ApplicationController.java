package Controller;

import Constants.Message;
import Util.Scanner_Singleton;
import java.util.Scanner;


public class ApplicationController {
    Scanner inputscanner = Scanner_Singleton.getInstance().getScanner();

    public void run() {
        while (true) {
            // Show main menu
            System.out.println();
            System.out.println(Message.WELCOME);
            System.out.println(Message.MENU);
            System.out.print(Message.SELECT_OPTION);

            int inputUser;
            try {
                inputUser = Integer.parseInt(inputscanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(Message.INVALID_INPUT);
                continue;
            }

            switch (inputUser) {
                case 1:
                    CompanyController.startCompanySelection(inputscanner);  // CopmanyController -> startCompanySelection
                    break;

                case 2:
                    System.out.println(Message.EXIT_MESSAGE);
                    inputscanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println(Message.INVALID_INPUT);
            }
        }
    }
}