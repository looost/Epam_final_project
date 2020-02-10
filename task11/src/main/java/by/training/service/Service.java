package by.training.service;

import by.training.entity.Type;
import by.training.entity.composite.Component;
import by.training.entity.composite.Composite;

import java.util.Arrays;
import java.util.Collections;
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
        for (Component paragraph : component.getComponents()  // Предложение
        ) {
            for (Component sentences : ((Composite) paragraph).getComponents()  // Лексема
            ) {
                ((Composite) sentences).getComponents().sort(Comparator.comparing(component1 -> component1.operation().length()));  //Слово
            }
        }
        return component;
    }

    public Component sortLexemeInSentenceByChar(Component text, char symbol) {
        component = new Composite(Type.TEXT);
        for (int i = 0; i < ((Composite) text).getSize(); i++) {
            component.add(text.getChild(i));
        }
        for (Component paragraph : component.getComponents()  // Абзац
        ) {
            for (Component sentences : ((Composite) paragraph).getComponents()  // Предложение
            ) {
                ((Composite) sentences).getComponents()
                        .sort(Comparator.comparing(o -> ((Component) o).operation().chars().filter(ch -> ch == symbol).count()).reversed());  // Лексема
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
