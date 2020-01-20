package by.training.payment.service;


public class Parser {
    public String[] parsFile(String str, String regex) {
        return str.split(regex);
    }
}