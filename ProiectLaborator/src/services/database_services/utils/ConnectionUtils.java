package services.database_services.utils;

import services.database_services.properties.ReadProperties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;

public class ConnectionUtils {
    private static ConnectionUtils ourInstance = new ConnectionUtils();

    public static ConnectionUtils getInstance() {
        return ourInstance;
    }

    private ConnectionUtils() {
    }

    private String DB_URL;
    private String DB_USER;
    private String DB_PASS;

    {
        Vector<String> dbProperties = ReadProperties.readProperties();
        DB_URL = dbProperties.elementAt(0);
        DB_USER = dbProperties.elementAt(1);
        DB_PASS = dbProperties.elementAt(2);
    }

    public Connection getDBConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void closeDBConnection(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
