package by.training.service.parser.handler;

import by.training.entity.Type;
import by.training.entity.composite.Component;
import by.training.entity.composite.Composite;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordParser implements Handler {
    private static final String REGEX_WORD = "([a-zA-Z]+-[a-zA-Z]+-[a-zA-Z]+)|([a-zA-Z]+-[a-zA-Z]+)|([^(),:'\\.!?*\\/\\-\\d\\s]+)|(([^\\s]*\\d+[+\\-*\\/]+[^\\s]*))";

    @Override
    public void parse(Component sentence, String text) {
        Component word;
        String wordString;
        Pattern patternWord = Pattern.compile(REGEX_WORD);
        Matcher matcherWord = patternWord.matcher(text);
        SymbolParser symbolParser = new SymbolParser();

        for (int i = 0; i < text.length(); i++) {
            while (matcherWord.find()) {
                wordString = matcherWord.group();
                word = new Composite(Type.WORD);
                symbolParser.parse(word, wordString);
                sentence.add(word);
            }
        }
    }
}
