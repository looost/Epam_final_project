package by.training.service.parser.factory;

import by.training.service.parser.handler.*;

public class HandlerFactory {
    private static final HandlerFactory instance = new HandlerFactory();
    private final Handler textHandler = new TextParser();
    private final Handler paragraphHandler = new ParagraphParser();
    private final Handler sentenceParser = new SentenceParser();
    private final Handler lexemeParser = new LexemeParser();
    private final Handler wordParser = new WordParser();
    private final Handler symbolParser = new SymbolParser();

    private HandlerFactory() {
    }


    public static HandlerFactory getInstance() {
        return instance;
    }

    public Handler getTextHandler() {
        return textHandler;
    }

    public Handler getParagraphHandler() {
        return paragraphHandler;
    }

    public Handler getSentenceParser() {
        return sentenceParser;
    }

    public Handler getLexemeParser() {
        return lexemeParser;
    }

    public Handler getWordParser() {
        return wordParser;
    }

    public Handler getSymbolParser() {
        return symbolParser;
    }
}
