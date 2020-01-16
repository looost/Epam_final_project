package by.training.dragon.service;

import by.training.dragon.entity.Treasure;
import by.training.dragon.service.exception.ServiceException;

import java.util.List;

public interface CaveService {
    List<Treasure> getAllTreasure() throws ServiceException;

    List<Treasure> getMostValuableTreasure() throws ServiceException;

    List<Treasure> getTreasureGivenAmount(int amount) throws ServiceException;

    double totalAmount(List<Treasure> treasures);
}
