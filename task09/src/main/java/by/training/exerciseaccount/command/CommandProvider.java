package by.training.exerciseaccount.command;

import by.training.exerciseaccount.command.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private final Map<CommandName, Command> repository = new HashMap<>();

    public CommandProvider() {
        repository.put(CommandName.INFO_BALANCE_ALL_ACCOUNT, new InfoBalanceAllAccountCommand());
        repository.put(CommandName.BALANCE_AMOUNT, new BalanceAmountCommand());
        repository.put(CommandName.TOTAL_AMOUNT_WITH_POSITIVE_BALANCE, new TotalAmountWithPositiveBalanceCommand());
        repository.put(CommandName.TOTAL_AMOUNT_WITH_NEGATIVE_BALANCE, new TotalAmountWithNegativeBalanceCommand());
        repository.put(CommandName.FIND_BY_MIN_BALANCE, new FindByMinBalanceCommand());
        repository.put(CommandName.SORT_BY_BALANCE, new SortByBalanceCommand());
        repository.put(CommandName.WRONG_REQUEST, new WrongRequest());
    }

    public Command getCommand(int value) {
        for (CommandName c : CommandName.values()
        ) {
            if (c.ordinal() + 1 == value) {
                return repository.get(c);
            }
        }
        return repository.get(CommandName.WRONG_REQUEST);
    }

}
