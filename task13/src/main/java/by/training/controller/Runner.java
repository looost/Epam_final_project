package by.training.controller;

import by.training.controller.parsercommand.CommandResponse;
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
        CommandResponse commandResponse;

        while (flag) {
            view.showMenu();
            request = userUI.enterString();
            commandResponse = controller.getCommand(request).getSerials(filePath);
            if (commandResponse.getStatus().equals("OK")) {
                serials = commandResponse.getValue();
                view.showXml(serials);
            } else {
                view.showError(commandResponse.getStatus());
            }
            view.showMessage("Для выхода введите 0");
            request = userUI.enterString();
            if (request.equals("0")) {
                flag = false;
            }
        }
    }
}
