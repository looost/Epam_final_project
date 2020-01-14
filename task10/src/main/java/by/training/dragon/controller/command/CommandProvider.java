package by.training.dragon.controller.command;

import by.training.dragon.controller.command.impl.AllTreasureCommand;
import by.training.dragon.controller.command.impl.MostValuableCommand;
import by.training.dragon.controller.command.impl.TreasureForAGivenAmount;
import by.training.dragon.controller.command.impl.WrongRequestCommand;

import java.util.EnumMap;
import java.util.Map;

public class CommandProvider {
    private final Map<CommandName, Command> repository = new EnumMap<>(CommandName.class);

    public CommandProvider() {
        repository.put(CommandName.ALL, new AllTreasureCommand());
        repository.put(CommandName.MOST_VALUABLE, new MostValuableCommand());
        repository.put(CommandName.TREASURE, new TreasureForAGivenAmount());
        repository.put(CommandName.WRONG_REQUEST, new WrongRequestCommand());
    }


    public Command getCommand(String request) {
        try {
            return repository.get(CommandName.valueOf(request.split(" ")[0].toUpperCase()));
        } catch (IllegalArgumentException e) {
            System.err.println("Неверная команда");
            return repository.get(CommandName.WRONG_REQUEST);
        }
    }
}
