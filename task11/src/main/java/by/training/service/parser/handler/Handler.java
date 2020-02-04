package by.training.service.parser.handler;

import by.training.entity.Type;
import by.training.entity.composite.Component;

import java.awt.*;
import java.util.List;

public interface Handler {
    void parse(Component component, String text);
}
