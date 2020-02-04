package by.training.service.parser.handler;

import by.training.entity.Type;
import by.training.entity.composite.Component;
import by.training.entity.composite.Composite;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeParser extends Handler {
    private static final String REGEX_LEXEME = "([^\\s]+)";

    @Override
    public void parse(Component sentenceComponent, String sentence) {
        if (next != null) {
            Component lexeme;
            String lexemeString;
            Pattern patternLexeme = Pattern.compile(REGEX_LEXEME);
            Matcher matcherLexeme = patternLexeme.matcher(sentence);
            while (matcherLexeme.find()) {
                lexemeString = matcherLexeme.group();

                lexeme = new Composite(Type.LEXEME);
                next.parse(lexeme, lexemeString);
                sentenceComponent.add(lexeme);
            }
        }
    }
}
