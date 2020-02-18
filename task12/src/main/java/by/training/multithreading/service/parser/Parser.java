package by.training.multithreading.service.parser;

public class Parser {
    public static String[] parsFile(String str, String regex) {
        return str.split(regex);
    }
}
