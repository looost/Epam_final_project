package by.training.abiturient.view;

import by.training.abiturient.entity.AbiturientBean;

import java.util.stream.Stream;

public class View {
    public void showTerminal(Stream <AbiturientBean> stream) {
        stream.forEach(System.out::println);
    }
    public void showMessage(String message) {
        System.out.println(message);
    }
}
