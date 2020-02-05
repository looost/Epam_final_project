package by.training.controller.command;

import by.training.controller.command.impl.*;

import java.util.EnumMap;
import java.util.Map;

public class CommandProvider {
    private final Map<CommandName, Command> repository = new EnumMap<>(CommandName.class);

    public CommandProvider() {
        repository.put(CommandName.SORT_PARAGRAPH_BY_COUNT_OF_SENTENCE, new SortParagraphByCountOfSentenceCommand());
        repository.put(CommandName.SORT_WORD_IN_SENTENCE_BY_LENGTH, new SortWordInSentenceByLengthCommand());
        repository.put(CommandName.SORT_SENTENCE_BY_COUNT_OF_LEXEME, new SortSentenceByCountOfLexemeCommand());
        repository.put(CommandName.ORIGINAL_TEXT, new OriginalTextCommand());
        repository.put(CommandName.WRONG_REQUEST, new WrongRequestCommand());
    }

    public Command getCommand(String request) {
        try {
            return repository.get(CommandName.values()[Integer.parseInt(request) - 1]);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            return repository.get(CommandName.WRONG_REQUEST);
        }
    }
}
