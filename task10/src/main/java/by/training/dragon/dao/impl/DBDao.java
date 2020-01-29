package by.training.dragon.dao.impl;

import by.training.dragon.dao.Dao;
import by.training.dragon.dao.exception.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBDao implements Dao {
    @Override
    public List<String> readData() throws DAOException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new DAOException(e);
        }
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?serverTimezone=UTC&useSSL=false", "root", "root");
             Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery("SELECT * FROM treasure");
            List<String> list = new ArrayList<>();
            while (result.next()) {
                list.add(result.getString("name") + "; " + result.getInt("price"));
            }
            return list;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
}
