package by.training.payment.service;

import java.util.List;


public class Parser {
    public String[] parsFile(List<String> list, int i) {
        return list.get(i).split("; ");
    }
}