package by.training.entity.composite;

import by.training.entity.Type;

import java.util.List;

public abstract class Component {

    public abstract String operation();

    public abstract void add(Component c);

    public abstract void remove(Component c);

    public abstract Component getChild(int index);

    public abstract List<Component> getComponents();

    public abstract Type getType();
}
