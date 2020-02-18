package by.training.multithreading.dao.impl;

import by.training.multithreading.dao.AbstractDao;
import by.training.multithreading.dao.exception.DaoException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileDao implements AbstractDao {

    private final String FILE_PATH;

    public FileDao(String path) {
        this.FILE_PATH = path;
    }

    @Override
    public String readData() throws DaoException {
        try {
            return new String(Files.readAllBytes(Paths.get(FILE_PATH)));
        } catch (IOException e) {
            throw new DaoException("Файл не найдет!", e);
        }
    }
}
