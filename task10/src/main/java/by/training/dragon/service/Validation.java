package by.training.dragon.service;

import by.training.dragon.entity.Cave;

public class Validation {
    public boolean inCaveHaveTreasure(Cave cave) {
        return cave.getTreasureList().isEmpty();
    }
}
