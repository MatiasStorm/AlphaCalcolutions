package easyon.alphacalcolutions.data;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBManager {
    private static String user;
    private static String password;
    private static String url;
    private static String host;
    private static String database;
    private static Connection connection = null;

    public static Connection getConnection(){
        if (connection != null) return connection;
        user = System.getenv("MYSQL_USER");
        password = System.getenv("MYSQL_ROOT_PASSWORD");
        host = System.getenv("MYSQL_HOST");
        database = System.getenv("MYSQL_DATABASE");
        url = String.format("jdbc:mysql://%s:3306/%s?serverTimezone=UTC", host, database);

        // try (InputStream input = new FileInputStream("src/main/resources/application.properties")) {
        //     Properties properties = new Properties();
        //     properties.load(input);
        //     url = properties.getProperty("url");
        //     user = properties.getProperty("user");
        //     password = properties.getProperty("password");
        // } catch (IOException ex) {
        //     ex.printStackTrace();
        //     System.err.println("Cannot find you application.properties file - Is it placed under 'templates'?");
        // }
        try {
            connection = DriverManager.getConnection(url,user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("\nYou have a problem with your applications.properties file - Are you sure you don't have a typo?");
        }
        return connection;
    }
}
