package by.training.dao.impl;

import by.training.dao.Dao;
import by.training.dao.exception.DAOException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileDao implements Dao {

    private final String FILE_PATH;

    public FileDao(String path) {
        this.FILE_PATH = path;
    }

    @Override
    public String readData() throws DAOException {

//        try {
//            return Files.lines(Paths.get(TREASURE_PATH)).reduce("", String::concat);
//        } catch (IOException e) {
//            throw new DAOException("Файл не найдет!", e);
//        }

        try {
            return new String(Files.readAllBytes(Paths.get(FILE_PATH)));
        } catch (IOException e) {
            throw new DAOException("Файл не найдет!", e);
        }


//        try (FileInputStream fileInputStream = new FileInputStream(new File(TREASURE_PATH))) {
//            byte[] text = new byte[fileInputStream.available()];
//            fileInputStream.read(text);
//            return new String(text);
//        } catch (IOException e) {
//            throw new DAOException("Файл не найден!", e);
//        }

    }
}
