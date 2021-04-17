package io.lhdev.restfulapi.util;

import org.mariadb.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

    private ConnectionUtil(){ }

    public static Connection getConnection() throws SQLException {

        Driver mariaDBDriver = new Driver();
        DriverManager.registerDriver(mariaDBDriver);

//        String username = System.getenv("DB_USERNAME");
//        String password = System.getenv("DB_PASSWORD");
//        String connectionString = System.getenv("DB_URL");

        String username = "root";
        String password = "leah";
        String connectionString = "jdbc:mariadb://localhost:3307/my_first_database";

        return DriverManager.getConnection(connectionString, username, password);
    }

}
