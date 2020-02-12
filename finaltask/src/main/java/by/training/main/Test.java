package by.training.main;

import by.training.dao.exception.DaoException;
import by.training.dao.show.ShowDao;
import by.training.dao.show.impl.ShowDaoImpl;
import by.training.entity.Show;

import java.sql.SQLException;

public class Test {
    public static void main(String[] args) throws SQLException, DaoException {
        ShowDao showDao = new ShowDaoImpl();
        Show entity = new Show(10, "Test_name2", 1.9);
        showDao.update(entity);
    }
}
