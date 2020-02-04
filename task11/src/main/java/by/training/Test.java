package by.training;

import by.training.dao.exception.DAOException;
import by.training.dao.factory.DAOFactory;
import by.training.entity.*;
import by.training.entity.composite.Component;
import by.training.entity.composite.Composite;
import by.training.entity.composite.Leaf;
import by.training.service.parser.*;
import by.training.service.parser.handler.ParagraphParser;
import by.training.service.parser.handler.SentenceParser;
import by.training.service.parser.handler.SymbolParser;
import by.training.service.parser.handler.WordParser;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) throws DAOException {
        String test = DAOFactory.getInstance().getDao().readData();
        //System.out.println(test);

        String t1 = "    Java hello gg work. Im Michal." +
                "    Its new par. And new Strok.";
        Component component = new Composite(Type.WORD);
        SymbolParser symbolParser = new SymbolParser();
        WordParser wordParser = new WordParser();
        SentenceParser sentenceParser = new SentenceParser();
        ParagraphParser paragraphParser = new ParagraphParser();
        //wordParser.parse(component, t1);
        //symbolParser.parse(component, t1);
        //sentenceParser.parse(component,t1);
        paragraphParser.parse(component, test);
        //System.out.println(t1);
        System.out.println(component.operation());


        //component.operation();
    }


}
