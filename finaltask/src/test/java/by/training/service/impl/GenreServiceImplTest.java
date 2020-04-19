package by.training.service.impl;

import by.training.dao.GenreDao;
import by.training.dao.Transaction;
import by.training.dao.exception.DaoException;
import by.training.dao.factory.DaoFactory;
import by.training.dao.impl.GenreDaoImpl;
import by.training.model.Genre;
import by.training.service.exception.ServiceException;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.testng.Assert.*;

public class GenreServiceImplTest {

//    @Mock
//    private GenreDaoImpl dao;
//
//    @Mock
//    private Transaction transaction;
//
//    public GenreServiceImplTest() {
//        MockitoAnnotations.initMocks(this);
//        dao = new GenreDaoImpl(transaction);
//    }

    @Test
    public void testFindByName() throws ServiceException, DaoException {
        Transaction transaction = mock(Transaction.class);

        when(transaction.getConnection()).thenReturn(getConnection());
        when(DaoFactory.getInstance().getGenreDao(transaction).findById("1"))
                .thenReturn(new Genre(1, "Драма"));

        GenreServiceImpl service = new GenreServiceImpl();
        Genre genre = service.findById("1");
        assertEquals(genre, new Genre(1, "Драма"));
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

}