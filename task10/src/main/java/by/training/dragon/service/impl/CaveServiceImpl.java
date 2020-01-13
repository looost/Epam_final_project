package by.training.dragon.service.impl;

import by.training.dragon.dao.exception.DAOException;
import by.training.dragon.dao.factory.DAOFactory;
import by.training.dragon.entity.Treasure;
import by.training.dragon.service.CaveService;
import by.training.dragon.service.exception.ServiceException;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CaveServiceImpl implements CaveService {

    @Override
    public List<Treasure> getAllTreasure() throws ServiceException {
        try {
            return DAOFactory.getInstance().getCaveDao().getCave().getTreasureList();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

    }

    @Override
    public Treasure getMostValuableTreasure() throws ServiceException {
        try {
            return DAOFactory.getInstance()
                    .getCaveDao()
                    .getCave()
                    .getTreasureList()
                    .stream()
                    .max(Comparator.comparingInt(Treasure::getPrice))
                    .orElse(null);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Treasure> getTreasureGivenAmount(int amount) throws ServiceException {
        try {
            return DAOFactory.getInstance()
                    .getCaveDao()
                    .getCave()
                    .getTreasureList()
                    .stream()
                    .filter(treasure -> treasure.getPrice() > amount)
                    .collect(Collectors.toList());
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

    }
}
