package by.training.controller;

import by.training.entity.Serial;
import by.training.view.UserUI;
import by.training.view.ViewConsole;

import java.util.Set;

public class Runner {
    public static void main(String[] args) {

        ViewConsole view = new ViewConsole();
        UserUI userUI = new UserUI();
        Controller controller = new Controller();
        String request;
        boolean flag = true;
        Set<Serial> serials;
        String filePath = "src\\main\\resources\\xml\\serials.xml";

        while (flag) {
            view.showMenu();
            request = userUI.enterString();
            serials = controller.getCommand(request).getSerials(filePath);
            view.showXml(serials);
            view.showMessage("Для выхода введите 0");
            request = userUI.enterString();
            if (request.equals("0")) {
                flag = false;
            }
        }
    }
}
