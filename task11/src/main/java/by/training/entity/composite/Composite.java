package by.training.entity.composite;

import by.training.entity.Type;

import java.util.ArrayList;

public class Composite implements Component {
    private Type type;
    private ArrayList<Component> components = new ArrayList<>();

    public Composite(Type type) {
        this.type = type;
    }

    @Override
    public String operation() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Component c : components
        ) {

            if (c.getType().equals(Type.PARAGRAPH)) {
                stringBuilder.append("\t");
            }

            stringBuilder.append(c.operation());

            if (c.getType().equals(Type.WORD)) {
                stringBuilder.append(" ");
            }
            if (c.getType().equals(Type.SENTENCE)) {
                stringBuilder.append(". ");
            }
            if (c.getType().equals(Type.PARAGRAPH)) {
                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }

    @Override
    public void add(Component component) {
        components.add(component);
    }

    @Override
    public void remove(Component component) {
        components.remove(component);
    }

    @Override
    public Object getChild(int index) {
        return components.get(index);
    }

    @Override
    public Type getType() {
        return type;
    }
}
