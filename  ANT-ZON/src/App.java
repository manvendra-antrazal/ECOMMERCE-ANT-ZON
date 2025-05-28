import Controller.ApplicationController;
import java.sql.SQLException;


public class App {
    public static void main(String[] args) throws SQLException {

        ApplicationController applicationController = new ApplicationController();
        applicationController.run();
    }
}