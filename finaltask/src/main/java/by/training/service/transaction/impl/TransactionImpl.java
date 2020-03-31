package by.training.service.transaction.impl;

import by.training.dao.AbstractDao;
import by.training.dao.EntityEnum;
import by.training.dao.impl.*;
import by.training.service.exception.ServiceException;
import by.training.service.transaction.Transaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.EnumMap;
import java.util.Map;

public class TransactionImpl implements Transaction {

    private Connection connection;
    private static Map<EntityEnum, AbstractDao> repository = new EnumMap<>(EntityEnum.class);

    public TransactionImpl(Connection connection) {
        this.connection = connection;

        repository.put(EntityEnum.COMMENT, new CommentDaoImpl(connection));
        repository.put(EntityEnum.COUNTRY, new CountryDaoImpl(connection));
        repository.put(EntityEnum.GENRE, new GenreDaoImpl(connection));
        repository.put(EntityEnum.SERIAL, new SerialDaoImpl(connection));
        repository.put(EntityEnum.STUDIO, new StudioDaoImpl(connection));
        repository.put(EntityEnum.USER, new UserDaoImpl(connection));
    }


    @Override
    public <T extends AbstractDao> T createDao(EntityEnum dao) throws ServiceException {
        AbstractDao abstractDao = repository.get(dao);
        if (abstractDao != null) {
            return (T) abstractDao;
        } else {
            throw new ServiceException();
        }
    }

    @Override
    public void commit() throws ServiceException {
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public void close() throws ServiceException {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }
}
