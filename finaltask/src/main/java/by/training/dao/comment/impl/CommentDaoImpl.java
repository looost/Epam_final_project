package by.training.dao.comment.impl;

import by.training.dao.comment.CommentDao;
import by.training.dao.exception.DaoException;
import by.training.dao.jdbc.JDBCUtil;
import by.training.dao.jdbc.ResultSetHandler;
import by.training.dao.jdbc.ResultSetHandlerFactory;
import by.training.model.Comment;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Properties;

public class CommentDaoImpl implements CommentDao {

    private static final String PATH_TO_PROPERTIES = "..\\webapps\\final\\WEB-INF\\classes\\sqlComment.properties";

    private Connection connection;
    private static final ResultSetHandler<Comment> COMMENT_RESULT_SET_HANDLER =
            ResultSetHandlerFactory.COMMENT_RESULT_SET_HANDLER;

    public CommentDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Comment> findAllCommentForSerial(String serialId) throws DaoException {
        return JDBCUtil.select(connection, getProperties().getProperty("findAllCommentForSerial"),
                ResultSetHandlerFactory.getListResultSetHandler(COMMENT_RESULT_SET_HANDLER), serialId);
    }

    @Override
    public Comment findById(String id) throws DaoException {
        return JDBCUtil.select(connection, getProperties().getProperty("findCommentById"),
                ResultSetHandlerFactory.getSingleResultSetHandler(COMMENT_RESULT_SET_HANDLER), id);
    }

    @Override
    public boolean delete(String id) throws DaoException {
        return JDBCUtil.delete(connection, getProperties().getProperty("deleteCommentById"), id);
    }

    @Override
    public boolean create(Comment entity) throws DaoException {
        return JDBCUtil.create(connection, getProperties().getProperty("createComment"),
                String.valueOf(entity.getUser().getId()), String.valueOf(entity.getSerial().getId()), entity.getComment());
    }

    @Override
    public boolean update(Comment entity) throws DaoException {
        return JDBCUtil.update(connection, getProperties().getProperty("updateComment"), entity.getComment());
    }

    private Properties getProperties() throws DaoException {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(PATH_TO_PROPERTIES));
            return properties;
        } catch (IOException e) {
            throw new DaoException("Not found properties file", e);
        }
    }
}
