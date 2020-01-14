package by.training.dragon.service.impl;

import by.training.dragon.dao.exception.DAOException;
import by.training.dragon.entity.Treasure;
import by.training.dragon.service.CaveService;
import by.training.dragon.service.Parser;
import by.training.dragon.service.exception.ServiceException;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CaveServiceImpl implements CaveService {

    private final Parser parser = new Parser();

    @Override
    public List<Treasure> getAllTreasure() throws ServiceException {
        if (parser.parseFromFile().getTreasureList().isEmpty()) {
            throw new ServiceException("Список сокровищ пустой :(");
        }
        return parser.parseFromFile().getTreasureList();

    }

    @Override
    public Treasure getMostValuableTreasure() throws ServiceException {
        if (parser.parseFromFile().getTreasureList().isEmpty()) {
            throw new ServiceException("Список сокровищ пустой :(");
        }
        return parser.parseFromFile()
                    .getTreasureList()
                    .stream()
                    .max(Comparator.comparingInt(Treasure::getPrice))
                    .orElse(null);
    }

    @Override
    public List<Treasure> getTreasureGivenAmount(int amount) throws ServiceException {
        if (parser.parseFromFile().getTreasureList().isEmpty()) {
            throw new ServiceException("Список сокровищ пустой :(");
        }
        return parser.parseFromFile()
                    .getTreasureList()
                    .stream()
                    .filter(treasure -> treasure.getPrice() > amount)
                    .collect(Collectors.toList());
    }
}
