package by.training.service.parser.handler;

import by.training.entity.Type;
import by.training.entity.composite.Component;
import by.training.entity.composite.Composite;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphParser extends Handler {

    private static final String REGEX_PARAGRAPH = "(?m)^[^\\S\\r\\n]{2,}\\S.*(?:\\r?\\n|$)(?:^\\S.*(?:\\r?\\n|$))*";

    @Override
    public void parse(Component fullTextComponent, String fullText) {
        if (next != null) {
            Component paragraph;
            String paragraphString;
            Pattern paragraphPattern = Pattern.compile(REGEX_PARAGRAPH);
            Matcher paragraphMatcher = paragraphPattern.matcher(fullText);
            while (paragraphMatcher.find()) {
                paragraphString = paragraphMatcher.group();
                paragraph = new Composite(Type.PARAGRAPH);
                next.parse(paragraph, paragraphString);
                fullTextComponent.add(paragraph);
            }
        }
    }
}
