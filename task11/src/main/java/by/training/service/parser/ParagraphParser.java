package by.training.service.parser;

import by.training.entity.composite.Component;

import java.util.ArrayList;
import java.util.List;

public class ParagraphParser {
    public static List<String> paragraphPars(String str) {
        List<String> list = new ArrayList<>();
        int length = str.split("  ").length;
        for (int i = 0; i < length; i++) {
            if (!str.split("  ")[i].isEmpty()) {
                list.add(str.split("  ")[i]);
            }
        }
        return list;
    }
}
