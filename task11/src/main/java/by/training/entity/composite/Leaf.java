package by.training.entity.composite;

import by.training.entity.Type;

import java.util.ArrayList;

public class Leaf extends Component {

    private char symbol;
    private static final Type type = Type.SYMBOL;

    public Leaf(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public String operation() {
        return String.valueOf(symbol);
    }

    @Override
    public void add(Component c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(Component c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Component getChild(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public ArrayList<Component> getComponents() {
        throw new UnsupportedOperationException("Not have component");
    }
}
