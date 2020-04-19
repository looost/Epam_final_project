package by.training.dao.impl;

import by.training.dao.Transaction;
import by.training.dao.exception.DaoException;
import by.training.model.Genre;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.testng.Assert.*;

public class GenreDaoImplTest {

    @Mock
    private Transaction transaction;

    private GenreDaoImpl dao;

    public GenreDaoImplTest() {
        MockitoAnnotations.initMocks(this);
        dao = new GenreDaoImpl(transaction);
    }

    private Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/serials_db?serverTimezone=UTC",
                            "application", "admin");
        } catch (SQLException | ClassNotFoundException e) {
            //throw new DaoException("Cannot connection", e);
            return null;
        }
    }

    private Connection getFakeConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/serials_db?serverTimezone=UTC",
                            "application22", "admin");
        } catch (SQLException | ClassNotFoundException e) {
            //throw new DaoException("Cannot connection", e);
            return null;
        }
    }

    @Test(expectedExceptions = DaoException.class)
    public void testFindById() throws DaoException {
        // BDDMockito.given(transaction.getConnection()).willReturn(getConnection());
        when(transaction.getConnection()).thenReturn(getConnection());
        //when(transaction.getConnection()).thenReturn(getFakeConnection());
        when(dao.findById("1")).thenThrow(DaoException.class);
        Genre genre = dao.findById("1");
        //assertEquals(genre, new Genre(1, "Драма"));
    }
}