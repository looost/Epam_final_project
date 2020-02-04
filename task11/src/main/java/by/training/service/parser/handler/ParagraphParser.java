package by.training.service.parser.handler;

import by.training.entity.Type;
import by.training.entity.composite.Component;
import by.training.entity.composite.Composite;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphParser implements Handler {

    private static final String REGEX_PARAGRAPH = "(?m)^[^\\S\\r\\n]{2,}\\S.*(?:\\r?\\n|$)(?:^\\S.*(?:\\r?\\n|$))*";

    @Override
    public void parse(Component fullText, String text) {
        Component paragraph;
        String paragraphString;
        Pattern paragraphPattern = Pattern.compile(REGEX_PARAGRAPH);
        Matcher paragraphMatcher = paragraphPattern.matcher(text);
        SentenceParser sentenceParser = new SentenceParser();


        while (paragraphMatcher.find()) {
            paragraphString = paragraphMatcher.group();
            paragraph = new Composite(Type.PARAGRAPH);
            sentenceParser.parse(paragraph, paragraphString);
            fullText.add(paragraph);
        }


    }
}
