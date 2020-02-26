package by.training.dao.serial.impl;

import by.training.dao.exception.DaoException;
import by.training.dao.serial.SerialDao;
import by.training.model.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class SerialDaoImpl implements SerialDao {

    private static final String PATH_TO_PROPERTIES = "src/main/resources/sqlSerial.properties";

    private Connection connection;

    public SerialDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Serial findSerialByName(String name) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(getProperties().getProperty("findSerialByName"));
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            Set<Genre> genreSet = new HashSet<>();
            Serial serial = null;
            if (resultSet.next()) {
                genreSet.add(new Genre(resultSet.getInt(getProperties().getProperty("genreId"))));
                serial = new Serial(resultSet.getInt(getProperties().getProperty("serialId")),
                        resultSet.getString(getProperties().getProperty("serialName")), resultSet.getString(getProperties().getProperty("serialDescription")),
                        resultSet.getString(getProperties().getProperty("serialLogo")), resultSet.getString(getProperties().getProperty("serialFullLogo")),
                        resultSet.getDate(getProperties().getProperty("serialReleaseDate")), resultSet.getInt(getProperties().getProperty("serialCountLike")),
                        new Country(resultSet.getInt(getProperties().getProperty("countryId"))), new Studio(resultSet.getInt(getProperties().getProperty("studioId"))),
                        genreSet);
                while (resultSet.next()) {
                    genreSet.add(new Genre(resultSet.getInt(getProperties().getProperty("genreId"))));
                }
            }
            return serial;
        } catch (SQLException e) {
            throw new DaoException("SQLException", e);
        } finally {
            close(resultSet);
            close(statement);
        }
    }

    @Override
    public List<Serial> findAll() throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(getProperties().getProperty("findAllSerial"));
            resultSet = statement.executeQuery();
            String[] genres;
            Genre genre;
            Set<Genre> genreSet;
            Serial serial;
            List<Serial> serialList = new ArrayList<>();
            while (resultSet.next()) {
                genreSet = new HashSet<>();
                genres = resultSet.getString(getProperties().getProperty("genres")).split(",");
                for (String s : genres) {
                    genre = new Genre(Integer.parseInt(s));
                    genreSet.add(genre);
                }
                serial = new Serial(resultSet.getInt(getProperties().getProperty("serialId")),
                        resultSet.getString(getProperties().getProperty("serialName")), resultSet.getString(getProperties().getProperty("serialDescription")),
                        resultSet.getString(getProperties().getProperty("serialLogo")), resultSet.getString(getProperties().getProperty("serialFullLogo")),
                        resultSet.getDate(getProperties().getProperty("serialReleaseDate")), resultSet.getInt(getProperties().getProperty("serialCountLike")),
                        new Country(resultSet.getInt(getProperties().getProperty("countryId"))), new Studio(resultSet.getInt(getProperties().getProperty("studioId"))),
                        genreSet);
                serialList.add(serial);
            }
            return serialList;
        } catch (SQLException e) {
            throw new DaoException("SQLException", e);
        } finally {
            close(resultSet);
            close(statement);
        }
    }

    @Override
    public Serial findById(String id) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(getProperties().getProperty("findSerialById"));
            statement.setString(1, id);
            resultSet = statement.executeQuery();
            Set<Genre> genreSet = new HashSet<>();
            Serial serial = null;
            if (resultSet.next()) {
                genreSet.add(new Genre(resultSet.getInt(getProperties().getProperty("genreId"))));
                serial = new Serial(resultSet.getInt(getProperties().getProperty("serialId")),
                        resultSet.getString(getProperties().getProperty("serialName")), resultSet.getString(getProperties().getProperty("serialDescription")),
                        resultSet.getString(getProperties().getProperty("serialLogo")), resultSet.getString(getProperties().getProperty("serialFullLogo")),
                        resultSet.getDate(getProperties().getProperty("serialReleaseDate")), resultSet.getInt(getProperties().getProperty("serialCountLike")),
                        new Country(resultSet.getInt(getProperties().getProperty("countryId"))), new Studio(resultSet.getInt(getProperties().getProperty("studioId"))),
                        genreSet);
                while (resultSet.next()) {
                    genreSet.add(new Genre(resultSet.getInt(getProperties().getProperty("genreId"))));
                }
            }
            return serial;
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
            statement = connection.prepareStatement(getProperties().getProperty("deleteSerialById"));
            statement.setString(1, id);
            return statement.execute();
        } catch (SQLException e) {
            throw new DaoException("SQLException", e);
        } finally {
            close(statement);
        }
    }

    @Override
    public boolean create(Serial entity) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(getProperties().getProperty("createSerial"));
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getDescription());
            statement.setString(3, entity.getLogo());
            statement.setString(4, entity.getFullLogo());
            return statement.execute();
        } catch (SQLException e) {
            throw new DaoException("SQLException", e);
        } finally {
            close(statement);
        }
    }

    @Override
    public boolean update(Serial entity) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(getProperties().getProperty("updateSerial"));
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getDescription());
            statement.setString(3, entity.getLogo());
            statement.setString(3, entity.getFullLogo());
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
