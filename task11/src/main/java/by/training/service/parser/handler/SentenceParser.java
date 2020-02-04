package by.training.service.parser.handler;

import by.training.entity.Type;
import by.training.entity.composite.Component;
import by.training.entity.composite.Composite;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser implements Handler {

    private static final String REGEX_SENTENCE = "([A-Z\\d]{1}[^.!?]*\\.{3})|([A-Z\\d]{1}[^.!?]*[.!?.]{1})";


    @Override
    public void parse(Component paragraph, String text) {
        Component sentence;
        String sentenceString;
        Pattern patternSentence = Pattern.compile(REGEX_SENTENCE);
        Matcher matcherSentence = patternSentence.matcher(text);
        WordParser wordParser = new WordParser();


        while (matcherSentence.find()) {
            sentenceString = matcherSentence.group();
            sentence = new Composite(Type.SENTENCE);
            wordParser.parse(sentence, sentenceString);
            paragraph.add(sentence);
        }

    }
}
