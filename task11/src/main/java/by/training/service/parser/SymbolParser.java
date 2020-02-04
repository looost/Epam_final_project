package by.training.service.parser;

import by.training.entity.composite.Component;
import by.training.entity.composite.Leaf;

import java.util.ArrayList;
import java.util.List;

public class SymbolParser {
    public static List<String> symbolParser(String str) {
        List<String> list = new ArrayList<>();
        int length = str.toCharArray().length;
        System.err.println(length);
        for (int i = 0; i < length; i++) {
            list.add(str.toCharArray()[i] + "");
        }
        return list;
    }
}
