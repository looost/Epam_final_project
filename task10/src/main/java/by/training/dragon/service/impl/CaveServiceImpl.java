package by.training.dragon.service.impl;

import by.training.dragon.entity.Treasure;
import by.training.dragon.service.CaveService;
import by.training.dragon.service.Parser;
import by.training.dragon.service.Validation;
import by.training.dragon.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CaveServiceImpl implements CaveService {

    private Validation validation = new Validation();
    private final Parser parser = new Parser();
    private static final String EMPTY_CAVE = "Список сокровищ пустой :(";

    @Override
    public List<Treasure> getAllTreasure() throws ServiceException {
        if (validation.inCaveHaveTreasure(parser.parseFromFile())) {
            throw new ServiceException(EMPTY_CAVE);
        }
        return parser.parseFromFile().getTreasureList();

    }

    @Override
    public List<Treasure> getMostValuableTreasure() throws ServiceException {
        if (validation.inCaveHaveTreasure(parser.parseFromFile())) {
            throw new ServiceException(EMPTY_CAVE);
        }
        List<Treasure> list = new ArrayList<>();
        list.add(parser.parseFromFile()
                .getTreasureList()
                .stream()
                .max(Comparator.comparingInt(Treasure::getPrice))
                .orElse(null));
        return list;
    }

    @Override
    public List<Treasure> getTreasureGivenAmount(int amount) throws ServiceException {

        if (validation.inCaveHaveTreasure(parser.parseFromFile())) {
            throw new ServiceException(EMPTY_CAVE);
        }

        List<Treasure> list = new ArrayList<>();
        List<Treasure> treasureList = parser.parseFromFile().getTreasureList();
        treasureList.sort(Comparator.comparingInt(Treasure::getPrice).reversed());

        int currentAmount = 0;
        boolean flag = true;

        OUTER:
        while (flag) {
            for (int i = 0; i < treasureList.size(); i++) {
                currentAmount += treasureList.get(i).getPrice();
                if (currentAmount <= amount) {
                    list.add(treasureList.get(i));
                    treasureList.remove(i);
                    continue OUTER;
                } else {
                    currentAmount -= treasureList.get(i).getPrice();
                }
            }
            flag = false;
        }
        return list;
    }

    @Override
    public double totalAmount(List<Treasure> list) {
        double totalAmount = 0;
        for (Treasure t : list
        ) {
            totalAmount += t.getPrice();
        }
        return totalAmount;
    }
}
