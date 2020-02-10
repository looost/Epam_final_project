package by.training.controller;

import by.training.controller.command.CommandResponse;
import by.training.dao.exception.DAOException;
import by.training.dao.factory.DAOFactory;
import by.training.entity.*;
import by.training.entity.composite.Component;
import by.training.entity.composite.Composite;
import by.training.service.parser.factory.HandlerFactory;
import by.training.service.parser.handler.*;
import by.training.view.UserUI;
import by.training.view.ViewConsole;


public class Runner {
    public static void main(String[] args) throws DAOException {
        ViewConsole viewConsole = new ViewConsole();
        UserUI userUI = new UserUI();
        Controller controller = new Controller();


        String test = DAOFactory.getInstance().getDao().readData();




        Handler textParser = HandlerFactory.getInstance().getTextHandler();
        Handler paragraphParser = HandlerFactory.getInstance().getParagraphHandler();
        Handler sentenceParser = HandlerFactory.getInstance().getSentenceParser();
        Handler lexemeParser = HandlerFactory.getInstance().getLexemeParser();
        Handler wordParser = HandlerFactory.getInstance().getWordParser();
        Handler symbolParser = HandlerFactory.getInstance().getSymbolParser();

        wordParser.setNext(symbolParser);
        lexemeParser.setNext(symbolParser);
        sentenceParser.setNext(lexemeParser);
        paragraphParser.setNext(sentenceParser);
        textParser.setNext(paragraphParser);


        Component component;
        boolean flag = true;
        String request;
        CommandResponse commandResponse;

        while (flag) {
            component = new Composite(Type.TEXT);
            textParser.parse(component, test);

            viewConsole.showMenu();
            request = userUI.enterString();
            commandResponse = controller.getCommand(request).execute(component, request);

            if (commandResponse.getMessage().equalsIgnoreCase("ok")) {
                viewConsole.showComponent(commandResponse.getComponent());
            } else {
                viewConsole.showError(commandResponse.getMessage());
            }

            viewConsole.showMessage("Введите что угодно что бы продолжить, 0 - для выхода");
            request = userUI.enterString();
            if (request.equals("0")) {
                flag = false;
            }
        }


    }


}
