package by.training.service.parser;

import by.training.entity.composite.Component;

import java.util.ArrayList;
import java.util.List;

public class WordParser {
    public static List<String> wordParser(String str) {
        List<String> list = new ArrayList<>();
        int length = str.split(",").length;
        for (int i = 0; i < length; i++) {
            list.add(str.split(",")[i]);
        }
        return list;
    }

}
