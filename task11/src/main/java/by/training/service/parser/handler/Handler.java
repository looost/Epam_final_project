package by.training.service.parser.handler;

import by.training.entity.Type;
import by.training.entity.composite.Component;

import java.awt.*;
import java.util.List;

public abstract class Handler {
    protected Handler next;

    public abstract void parse(Component component, String text);

    public void setNext(Handler next) {
        this.next = next;
    }
}
