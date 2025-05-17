package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static DBConnection instance;
    private static Connection connection;
    
    private static final String url = "jdbc:mysql://localhost:3306/ecommerce";
    private static final String username = "root";
    private static final String password = "0000";

    static {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }   catch(ClassNotFoundException e){
            System.out.println("Failed to load Driver! " + e.getMessage());
        }
    }
    
    private DBConnection() {}
    
    public static DBConnection getInstance() {
        if (instance == null) {        
                instance = new DBConnection();
        }
        return instance;
    }
    

    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
           System.out.println("Failed to start connection " + e.getMessage());  // Print stack trace for debugging
            }
        }
        return connection;
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
    }
 
}
}
