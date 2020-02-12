package by.training.view;

import by.training.entity.composite.Component;

public class ViewConsole {
    public void showMenu() {
        System.out.println("----------------Доступные команды---------------");
        System.out.println("1 - Отсортировать абзацы по количеству предложений.");
        System.out.println("2 - Отсортировать слова в предложении по длине.");
        System.out.println("3 - Отсортировать предложения по количеству лексем.");
        System.out.println("4 <char> - Отсортировать лексемы в тексте по убыванию количества вхождений заданного символа, а в случае равенства – по алфавиту.");
        System.out.println("5 - Вывести оригинальный текст.");
    }

    public void showComponent(Component component) {
        System.out.println(component.operation());
    }

    public void showMessage(String str) {
        System.out.println(str);
    }

    public void showError(String str) {
        System.err.println(str);
    }
}
