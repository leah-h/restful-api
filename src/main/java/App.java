import io.javalin.Javalin;

import java.sql.*;

public class App {

    public static void main(String[] args) throws SQLException {
        Javalin app = Javalin.create().start(7000);
        app.get("/", ctx -> ctx.result("Welcome to RESTful API"));

        //create connection for a server installed in localhost, with a user "root" with no password
        try (Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost/", "root", "leahh")) {
            // create a Statement
            try (Statement stmt = conn.createStatement()) {
                //execute query
                try (ResultSet rs = stmt.executeQuery("SELECT 'Connected to mariadb!'")) {
                    //position result to first
                    rs.first();
                    System.out.println(rs.getString(1)); //result is "Hello World!"
                }
            }
            }
    }


}
