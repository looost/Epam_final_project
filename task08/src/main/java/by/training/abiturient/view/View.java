package by.training.abiturient.view;

import by.training.abiturient.entity.Abiturient;

import java.util.Scanner;
import java.util.stream.Stream;

public class View {
    Scanner scanner = new Scanner(System.in);

    public void showTerminal(Stream<Abiturient> stream) {
        stream.forEach(System.out::println);
    }
    public void showMessage(String message) {
        System.out.println(message);
    }
}
