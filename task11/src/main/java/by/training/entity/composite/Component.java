package by.training.entity.composite;

import by.training.entity.Type;

public interface Component {
    String operation();

    void add(Component c);

    void remove(Component c);

    Component getChild(int index);

    Type getType();
}
