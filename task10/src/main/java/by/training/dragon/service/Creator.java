package by.training.dragon.service;

import by.training.dragon.entity.Cave;
import by.training.dragon.entity.Treasure;

public class Creator {

    public boolean createCave(Cave cave, String treasureName, int price) {
        return cave.addTreasure(new Treasure(treasureName, price));
    }
}
