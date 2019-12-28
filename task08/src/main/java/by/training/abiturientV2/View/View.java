package by.training.abiturientV2.View;

import by.training.abiturientV2.entity.Abiturient;

import java.util.stream.Stream;

public class View {
    public void showTerminal(Stream<Abiturient> stream) {
        stream.forEach(System.out::println);
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showMenu() {
        System.out.println("----------------------------Меню-------------------------------");
        System.out.println("1 - Полный список абитуриентов в алфавитном порядке");
        System.out.println("2 - Список абитуриентов, имеющих неудовлетворительные оценки");
        System.out.println("3 - Список абитуриентов, у которых сумма баллов выше заданной");
        System.out.println("4 - Выбрать заданное число n абитуриентов, имеющих самую высокую\n" +
                "сумму баллов");
        System.out.println("5 - Выбрать заданное число n абитуриентов, имеющих самую низкую\n" +
                "сумму баллов");
        System.out.println("0 - Выход");
    }
}
