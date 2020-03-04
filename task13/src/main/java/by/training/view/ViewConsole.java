package by.training.view;

import by.training.entity.Serial;

import java.util.Arrays;
import java.util.Set;

public class ViewConsole {
    public void showMenu() {
        System.out.println("----------------Доступные команды---------------");
        System.out.println("--> Введите название парсера (доступные: SAX StAX DOM): ");
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showError(String message) {
        System.err.println(message);
    }

    public void showXml(Set<Serial> serials) {
        serials.forEach(System.out::println);
    }
}
