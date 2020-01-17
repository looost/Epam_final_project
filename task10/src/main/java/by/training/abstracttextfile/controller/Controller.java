package by.training.abstracttextfile.controller;

public class Controller {
    public Command getCommand(String request) {
        return CommandProvider.getInstance()
                .getCommand(request.split(" ")[0].toUpperCase());
    }
}
