package by.training.dragon.service;

import by.training.dragon.dao.exception.DAOException;
import by.training.dragon.dao.factory.DAOFactory;
import by.training.dragon.entity.Cave;
import by.training.dragon.entity.Treasure;
import by.training.dragon.service.exception.ServiceException;

public class Parser {
    public Cave parseFromFile() throws ServiceException {
        try {
            Cave cave = new Cave();
            String[] lines;
            for (String str : DAOFactory.getInstance().getDao().readData()
            ) {
                lines = str.split("; ");
                cave.addTreasure(new Treasure(lines[0], Integer.parseInt(lines[1])));
            }
            return cave;
        } catch (DAOException e) {
            throw new ServiceException("Файл не найдет!", e);
        }

    }
}
