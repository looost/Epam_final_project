package by.training.view;

import by.training.entity.composite.Component;

public class ViewConsole {
    public void showMenu() {
        System.out.println("----------------Доступные команды---------------");
        System.out.println("1 - Отсортировать абзацы по количеству предложений.");
        System.out.println("2 - Отсортировать слова в предложении по длине.");
        System.out.println("0 - Выход");
    }

    public void showComponent(Component component) {
        System.out.println(component.operation());
    }

    public void showMessage(String str) {
        System.out.println(str);
    }
}
