package by.training.dragon.dao.impl;

import by.training.dragon.dao.CaveDao;
import by.training.dragon.dao.exception.DAOException;
import by.training.dragon.entity.Cave;
import by.training.dragon.entity.Treasure;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileCaveDao implements CaveDao {

    private static final String TREASURE_PATH = "src\\main\\resources\\treasure.txt";

    @Override
    public Cave getCave() throws DAOException {
        try (Stream<String> stream = Files.lines(Paths.get(TREASURE_PATH))) {
            Cave cave = new Cave();
            String[] lines;
            for (String str : stream.collect(Collectors.toList())
            ) {
                lines = str.split("; ");
                cave.addTreasure(new Treasure((lines[0]), Integer.parseInt(lines[1])));
            }
            return cave;
        } catch (IOException e) {
            throw new DAOException("Файл не найдет", e);
        }
    }
}
