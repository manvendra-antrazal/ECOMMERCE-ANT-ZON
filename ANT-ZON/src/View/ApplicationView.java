package View;

import Constants.Message;

public class ApplicationView {

    public void showMainMenu() {
        System.out.println(Message.WELCOME);
        System.out.println(Message.EXISTING_COMAPNY);
        System.out.println(Message.CHOOSE_OPTION);
    }

    public void showExitMessage() {
        System.out.println(Message.EXIT_MESSAGE);
    }

    public void showInvalidOption() {
        System.out.println(Message.INVALID_OPTION);
    }

    public void showInvalidInputFormat() {
        System.out.println(Message.NUMBER);
    }
}
