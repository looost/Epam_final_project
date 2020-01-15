package by.training.dragon.service.impl;

import by.training.dragon.entity.Treasure;
import by.training.dragon.service.CaveService;
import by.training.dragon.service.Parser;
import by.training.dragon.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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

//        if (parser.parseFromFile().getTreasureList().isEmpty()) {
//            throw new ServiceException("Список сокровищ пустой :(");
//        }
//        return parser.parseFromFile()
//                    .getTreasureList()
//                    .stream()
//                    .filter(treasure -> treasure.getPrice() > amount)
//                    .collect(Collectors.toList());

        if (parser.parseFromFile().getTreasureList().isEmpty()) {
            throw new ServiceException("Список сокровищ пустой :(");
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
