package by.training.service;

import by.training.entity.Type;
import by.training.entity.composite.Component;

import java.util.Comparator;

public class Service {

    public Component sortParagraphByCountOfSentence(Component text) {

        text.getComponents().sort(Comparator.comparing(component1 -> count(component1, Type.SENTENCE)));
        return text;
    }

    public Component sortSentenceByCountOfLexeme(Component text) {
        for (Component sentence : text.getComponents() // Предложение
        ) {
            sentence.getComponents().sort(Comparator.comparing(component1 -> count(component1, Type.LEXEME))); // Лексема
        }
        return text;
    }


    public Component sortWordInSentenceByLength(Component text) {
        for (Component paragraph : text.getComponents()  // Предложение
        ) {
            for (Component sentences : paragraph.getComponents()  // Лексема
            ) {
                sentences.getComponents().sort(Comparator.comparing(component1 -> component1.operation().length()));  //Слово
            }
        }
        return text;
    }

    public Component sortLexemeInSentenceByChar(Component text, char symbol) {

        for (Component paragraph : text.getComponents()  // Абзац
        ) {
            for (Component sentences : paragraph.getComponents()  // Предложение
            ) {
                sentences.getComponents()
                        .sort(Comparator.comparing(o -> ((Component) o).operation().chars().filter(ch -> ch == symbol).count()).reversed()
                                .thenComparing(o -> ((Component) o).operation()));  // Лексема
            }
        }
        return text;
    }

    private int count(Component component, Type type) {
        int count = 0;
        for (Component c : component.getComponents()
        ) {
            if (c.getType().equals(type)) {
                count++;
            }
        }
        return count;
    }
}
