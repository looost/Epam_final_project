package by.training.service.parser.handler;

import by.training.entity.composite.Component;


public class TextParser extends Handler {

    @Override
    public void parse(Component fullTextComponent, String text) {
        if (next != null) {
            next.parse(fullTextComponent, text);
        }
    }
}
