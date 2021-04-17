package io.lhdev.restfulapi.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.mariadb.jdbc.Driver;

public class ConnectionUtil {

    private ConnectionUtil(){ }

    public static Connection getConnection() throws SQLException {

        Driver mariaDBDriver = new Driver();
        DriverManager.registerDriver(mariaDBDriver);

//        String username = System.getenv("DB_USERNAME");
//        String password = System.getenv("DB_PASSWORD");
//        String connectionString = System.getenv("DB_URL");

        String username = "root";
        String password = "leahh";
        String connectionString = "jdbc:mariadb://localhost/my_first_database";
        Connection conn = DriverManager.getConnection(connectionString, username, password);

//        return DriverManager.getConnection(connectionString, username, password);

            return conn;
    }

}
