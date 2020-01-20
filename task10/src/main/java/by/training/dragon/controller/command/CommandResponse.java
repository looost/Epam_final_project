package by.training.dragon.controller.command;

import by.training.dragon.entity.Treasure;

import java.util.List;

public class CommandResponse {
    private final List<Treasure> treasureList;
    private final String status;

    public CommandResponse(List<Treasure> treasureList, String status) {
        this.treasureList = treasureList;
        this.status = status;
    }

    public List<Treasure> getTreasureList() {
        return treasureList;
    }

    public String getStatus() {
        return status;
    }
}
