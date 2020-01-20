package by.training.dragon.view;

import by.training.dragon.controller.command.CommandName;
import by.training.dragon.entity.Treasure;

import java.util.Arrays;
import java.util.List;

public class ViewConsole {
    public void showCommand() {
        System.out.println("----------------Доступные команды---------------");
        Arrays.stream(CommandName.values())
                .filter(commandName -> !commandName.name().equals("WRONG_REQUEST"))
                .forEach(commandName -> System.out.println(commandName.getDescription()));
        System.out.println("------------------------------------------------");
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showError(String message) {
        System.err.println(message);
    }

    public void showTreasure(List<Treasure> treasures) {
            treasures.forEach(treasure -> System.out.println(treasure.getName() + " стоимостью - " + treasure.getPrice()));
    }
}
