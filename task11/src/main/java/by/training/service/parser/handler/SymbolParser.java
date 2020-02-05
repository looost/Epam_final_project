package by.training.service.parser.handler;

import by.training.entity.composite.Component;
import by.training.entity.composite.Leaf;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SymbolParser extends Handler {


    private static final String REGEX_LEAF = "(\\w)|([^\\w\\s])";

    @Override
    public void parse(Component wordComponent, String word) {

        Leaf symbol;
        String leafString;
        Pattern patternLeaf = Pattern.compile(REGEX_LEAF);
        Matcher matcherSymbol = patternLeaf.matcher(word);
        for (int i = 0; i < word.length(); i++) {
            if (matcherSymbol.find()) {
                leafString = matcherSymbol.group();
                symbol = new Leaf(leafString.charAt(0));
                wordComponent.add(symbol);
            }
        }
    }
}
