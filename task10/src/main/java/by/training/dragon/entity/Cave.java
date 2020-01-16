package by.training.dragon.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cave implements Serializable {

    private List<Treasure> treasureList;

    public Cave() {
        this.treasureList = new ArrayList<>();
    }

    public Cave(List<Treasure> treasureList) {
        this.treasureList = treasureList;
    }

    public List<Treasure> getTreasureList() {
        return treasureList;
    }

    public boolean addTreasure(Treasure treasure) {
        return this.treasureList.add(treasure);
    }

    public void removeTreasure(Treasure treasure) {
        this.treasureList.remove(treasure);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cave cave = (Cave) o;

        return treasureList != null ? treasureList.equals(cave.treasureList) : cave.treasureList == null;
    }

    @Override
    public int hashCode() {
        return treasureList != null ? treasureList.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Cave{" +
                "treasureList=" + treasureList +
                '}';
    }
}
