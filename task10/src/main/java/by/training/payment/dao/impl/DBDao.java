package by.training.payment.dao.impl;

import by.training.payment.dao.Dao;
import by.training.payment.dao.exception.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBDao implements Dao {
    @Override
    public List<String> readData() throws DAOException {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useSSL=false", "root", "root");
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery("SELECT * FROM product")) {
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
