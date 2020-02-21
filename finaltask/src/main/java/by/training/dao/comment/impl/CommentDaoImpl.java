package by.training.dao.comment.impl;

import by.training.dao.comment.CommentDao;
import by.training.dao.exception.DaoException;
import by.training.model.Comment;
import by.training.model.Serial;
import by.training.model.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class CommentDaoImpl implements CommentDao {

    private static final String PATH_TO_PROPERTIES = "src/main/resources/sqlComment.properties";

    private Connection connection;

    public CommentDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Set<Comment> findAllCommentForSerial(String serialId) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(getProperties().getProperty("findAllCommentForSerial"));
            statement.setString(1, serialId);
            resultSet = statement.executeQuery();
            Set<Comment> commentSet = new HashSet<>();
            Comment comment;
            while (resultSet.next()) {
                comment = new Comment(resultSet.getInt(getProperties().getProperty("commentId")), new User(resultSet.getInt(getProperties().getProperty("commentUserId"))),
                        new Serial(resultSet.getInt(getProperties().getProperty("commentSerialId"))), resultSet.getString(getProperties().getProperty("comment"))
                        , resultSet.getDate(getProperties().getProperty("commentPublicationDate")));
                commentSet.add(comment);
            }
            return commentSet;
        } catch (SQLException e) {
            throw new DaoException("SQLException", e);
        } finally {
            close(resultSet);
            close(statement);
        }
    }

    @Override
    public Comment findById(String id) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(getProperties().getProperty("findCommentById"));
            statement.setString(1, id);
            resultSet = statement.executeQuery();
            Comment comment = null;
            while (resultSet.next()) {
                comment = new Comment(resultSet.getInt(getProperties().getProperty("commentId")), new User(resultSet.getInt(getProperties().getProperty("commentUserId"))),
                        new Serial(resultSet.getInt(getProperties().getProperty("commentSerialId"))), resultSet.getString(getProperties().getProperty("comment"))
                        , resultSet.getDate(getProperties().getProperty("commentPublicationDate")));
            }
            return comment;
        } catch (SQLException e) {
            throw new DaoException("SQLException", e);
        } finally {
            close(resultSet);
            close(statement);
        }
    }

    @Override
    public boolean delete(String id) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(getProperties().getProperty("deleteCommentById"));
            statement.setString(1, id);
            return statement.execute();
        } catch (SQLException e) {
            throw new DaoException("SQLException", e);
        } finally {
            close(statement);
        }
    }

    @Override
    public boolean create(Comment entity) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(getProperties().getProperty("createComment"));
            statement.setString(1, String.valueOf(entity.getUser().getId()));
            statement.setString(2, String.valueOf(entity.getSerial().getId()));
            statement.setString(3, entity.getComment());
            return statement.execute();
        } catch (SQLException e) {
            throw new DaoException("SQLException", e);
        } finally {
            close(statement);
        }
    }

    @Override
    public boolean update(Comment entity) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(getProperties().getProperty("updateComment"));
            statement.setString(1, entity.getComment());
            return statement.execute();
        } catch (SQLException e) {
            throw new DaoException("SQLException", e);
        } finally {
            close(statement);
        }
    }

    private void close(ResultSet resultSet) throws DaoException {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            throw new DaoException("Cannot close resultSet", e);
        }
    }

    private void close(Statement statement) throws DaoException {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            throw new DaoException("Cannot close statement", e);
        }
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
