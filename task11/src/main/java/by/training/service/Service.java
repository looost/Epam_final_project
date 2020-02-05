package by.training.service;

import by.training.entity.Type;
import by.training.entity.composite.Component;
import by.training.entity.composite.Composite;

import java.util.Comparator;

public class Service {
    private Composite component;

    public Component sortParagraphByCountOfSentence(Component text) {
        component = new Composite(Type.TEXT);
        for (int i = 0; i < ((Composite) text).getSize(); i++) {
            component.add(text.getChild(i));
        }
        component.getComponents().sort(Comparator.comparing(component1 -> count(component1, Type.SENTENCE)));
        return component;
    }

    public Component sortSentenceByCountOfLexeme(Component text) {
        component = new Composite(Type.TEXT);
        for (int i = 0; i < ((Composite) text).getSize(); i++) {
            component.add(text.getChild(i));
        }
        for (Component sentence : component.getComponents() // Предложение
        ) {
            ((Composite) sentence).getComponents().sort(Comparator.comparing(component1 -> count(component1, Type.LEXEME))); // Лексема
        }
        return component;
    }


    public Component sortWordInSentenceByLength(Component text) {
        component = new Composite(Type.TEXT);
        for (int i = 0; i < ((Composite) text).getSize(); i++) {
            component.add(text.getChild(i));
        }
        for (Component sentence : component.getComponents()  // Предложение
        ) {
            for (Component lexeme : ((Composite) sentence).getComponents()  // Лексема
            ) {
                ((Composite) lexeme).getComponents().sort(Comparator.comparing(component1 -> component1.operation().length()));  //Слово
            }
        }
        return component;
    }

    private int count(Component component, Type type) {
        int count = 0;
        for (int i = 0; i < ((Composite) component).getSize(); i++) {
            if (component.getChild(i).getType().equals(type)) {
                count++;
            }
        }
        return count;
    }
}
