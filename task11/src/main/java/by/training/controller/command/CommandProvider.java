package by.training.controller.command;

import by.training.controller.command.impl.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.EnumMap;
import java.util.Map;

public class CommandProvider {
    private final Map<CommandName, Command> repository = new EnumMap<>(CommandName.class);

    private static final Logger logger = LogManager.getLogger("wrongRequest");
    private static final Logger infoLogger = LogManager.getLogger("info");


    public CommandProvider() {
        repository.put(CommandName.SORT_PARAGRAPH_BY_COUNT_OF_SENTENCE, new SortParagraphByCountOfSentenceCommand());
        repository.put(CommandName.SORT_WORD_IN_SENTENCE_BY_LENGTH, new SortWordInSentenceByLengthCommand());
        repository.put(CommandName.SORT_SENTENCE_BY_COUNT_OF_LEXEME, new SortSentenceByCountOfLexemeCommand());
        repository.put(CommandName.SORT_LEXEME_IN_SENTENCE_BY_CHAR, new SortLexemeInSentenceByCharCommand());
        repository.put(CommandName.ORIGINAL_TEXT, new OriginalTextCommand());
        repository.put(CommandName.WRONG_REQUEST, new WrongRequestCommand());
    }

    public Command getCommand(String request) {
        try {
            infoLogger.info(request);
            return repository.get(CommandName.values()[Integer.parseInt(request.split(" ")[0]) - 1]);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            logger.error("Введена неверная команда", e);
            return repository.get(CommandName.WRONG_REQUEST);
        }
    }
}
