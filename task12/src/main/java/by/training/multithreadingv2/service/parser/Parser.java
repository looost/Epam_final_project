package by.training.multithreadingv2.service.parser;

public class Parser {
    public static String[] parsFile(String str, String regex) {
        return str.split(regex);
    }
}
