package by.training;

import java.sql.*;

public class ConnectionTest {
    public static void get() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/serials_db", "serials_user", "admin");
             Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery("SELECT * from shows");
            while (result.next()) {
                System.out.println("Сериал - " + result.getString("name") + " рейтинг - " + result.getDouble("rating"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
