package by.training.dao.impl;

import by.training.dao.Dao;
import by.training.dao.exception.DAOException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileDao implements Dao {

    private final String TREASURE_PATH;

    public FileDao(String path) {
        this.TREASURE_PATH = path;
    }

    @Override
    public String readData() throws DAOException {
//        try {
//            return Files.lines(Paths.get(TREASURE_PATH)).reduce("", (s, s2) -> s + "    " + s2);
//        } catch (IOException e) {
//            throw new DAOException("Файл не найдет!", e);
//        }


        try (FileInputStream fileInputStream = new FileInputStream(new File(TREASURE_PATH))) {
            byte[] text = new byte[fileInputStream.available()];
            fileInputStream.read(text);
            return new String(text);
        } catch (IOException e) {
            throw new DAOException("Файл не найден!", e);
        }

    }
}
