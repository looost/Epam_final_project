package by.training.dragon.view;

import by.training.dragon.controller.command.CommandName;
import by.training.dragon.entity.Treasure;

import java.util.Arrays;
import java.util.List;

class ViewConsole {
    void showCommand() {
        System.out.println("----------------Доступные команды---------------");
        Arrays.stream(CommandName.values())
                .filter(commandName -> !commandName.name().equals("WRONG_REQUEST"))
                .forEach(commandName -> System.out.println(commandName.getDescription()));
        System.out.println("------------------------------------------------");
    }

    void showMessage(String message) {
        System.out.println(message);
    }

    void showError(String message) {
        System.err.println(message);
    }

    void showTreasure(List<Treasure> treasures) {
            treasures.forEach(treasure -> System.out.println(treasure.getName() + " стоимостью - " + treasure.getPrice()));
    }
}
