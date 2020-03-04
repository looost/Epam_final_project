package by.training.multithreading.dao.impl;


import by.training.multithreading.dao.AbstractDao;
import by.training.multithreading.dao.exception.DaoException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileDao implements AbstractDao {

    @Override
    public String readData(String filePath) throws DaoException {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            throw new DaoException("Файл не найдет!", e);
        }
    }
}
