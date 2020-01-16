package by.training.dragon.service.impl;

import by.training.dragon.dao.exception.DAOException;
import by.training.dragon.dao.factory.DAOFactory;
import by.training.dragon.entity.Cave;
import by.training.dragon.entity.Treasure;
import by.training.dragon.service.CaveService;
import by.training.dragon.service.Creator;
import by.training.dragon.service.Parser;
import by.training.dragon.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CaveServiceImpl implements CaveService {

    private final Parser parser = new Parser();
    private final Creator creator = new Creator();

    @Override
    public List<Treasure> getAllTreasure() throws ServiceException {
        try {
            Cave cave = new Cave();
            String[] lines;
            if (DAOFactory.getInstance().getDao().readData().isEmpty()) {
                throw new ServiceException("Список сокровищ пустой :(");
            }
            int size = DAOFactory.getInstance().getDao().readData().size();
            for (int i = 0; i < size; i++) {
                lines = parser.parsFile(i);
                creator.createCave(cave, lines[0], Integer.parseInt(lines[1]));
            }
            return cave.getTreasureList();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Treasure> getMostValuableTreasure() throws ServiceException {
        List<Treasure> list = new ArrayList<>();
        list.add(getAllTreasure()
                .stream()
                .max(Comparator.comparingInt(Treasure::getPrice))
                .orElse(null));
        return list;
    }

    @Override
    public List<Treasure> getTreasureGivenAmount(int amount) throws ServiceException {

        List<Treasure> list = new ArrayList<>();
        List<Treasure> treasureList = getAllTreasure();
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
