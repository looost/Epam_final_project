package by.training.dragon.dao.impl;

import by.training.dragon.dao.Dao;
import by.training.dragon.dao.exception.DAOException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileDao implements Dao {

    private static final String TREASURE_PATH = "src\\main\\resources\\treasure.txt";

    @Override
    public List<String> readData() throws DAOException {
        try (Stream<String> stream = Files.lines(Paths.get(TREASURE_PATH))) {
            return stream.collect(Collectors.toList());
        } catch (IOException e) {
            throw new DAOException("Файл не найдет", e);
        }
    }
}
