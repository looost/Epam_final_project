package by.training.dragon.controller.command;

import by.training.dragon.controller.command.impl.AllTreasureCommand;
import by.training.dragon.controller.command.impl.MostValuableCommand;
import by.training.dragon.controller.command.impl.TreasureForAGivenAmount;
import by.training.dragon.controller.command.impl.WrongRequestCommand;

import static by.training.dragon.controller.command.CommandName.*;

import java.util.EnumMap;
import java.util.Map;

public class CommandProvider {
    private final Map<CommandName, Command> repository = new EnumMap<>(CommandName.class);

    public CommandProvider() {
        repository.put(ALL, new AllTreasureCommand());
        repository.put(MOST_VALUABLE, new MostValuableCommand());
        repository.put(TREASURE, new TreasureForAGivenAmount());
        repository.put(WRONG_REQUEST, new WrongRequestCommand());
    }


    public Command getCommand(String request) {
        try {
            return repository.get(CommandName.valueOf(request.split(" ")[0].toUpperCase()));
        } catch (IllegalArgumentException e) {
            System.err.println("Неверная команда");
            return repository.get(WRONG_REQUEST);
        }
    }
}
