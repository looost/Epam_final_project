package by.training.service.parser;

import by.training.entity.composite.Component;

import java.util.ArrayList;
import java.util.List;

public class SentenceParser {
    public static List<String> sentenceParser(String str) {
        List<String> list = new ArrayList<>();
        int length = str.split("[.!?]\\s*").length;
        //System.err.println(length);
        for (int i = 0; i < length; i++) {
            list.add(str.split("[.!?]\\s*")[i]);
        }
        return list;
    }
}
