package by.training.main;

import by.training.dao.AbstractDao;
import by.training.dao.ConnectionFactory;
import by.training.dao.exception.DaoException;
import by.training.dao.show.ShowDao;
import by.training.dao.show.impl.ShowDaoImpl;
import by.training.entity.Entity;
import by.training.entity.Show;

import java.sql.SQLException;

public class Test {
    public static void main(String[] args) throws SQLException, DaoException {
        ShowDao showDao = new ShowDaoImpl();
        Show entity = showDao.findById("1");
        System.out.println(entity);
    }
}
