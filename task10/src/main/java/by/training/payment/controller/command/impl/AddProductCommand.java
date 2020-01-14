package by.training.payment.controller.command.impl;

import by.training.payment.controller.command.Command;
import by.training.payment.entity.Payment;
import by.training.payment.servise.Parser;
import by.training.payment.servise.exeception.ServiceException;

import java.util.stream.Collectors;

public class AddProductCommand implements Command {

    private final Parser parser = new Parser();

    @Override
    public boolean execute(Payment payment, String command) {
        if (command.split(" ").length < 2) {
            System.err.println("Не выбран товар для добавления!");
            return false;
        }
        try {
            if (parser.parseFromFile().getProducts().stream().noneMatch(product -> product.getProductName().equalsIgnoreCase(command.split(" ")[1]))) {
                System.err.println("Такого товара нету!");
                return false;
            }
            double price = parser.parseFromFile()
                    .getProducts()
                    .stream()
                    .filter(product -> product.getProductName().equalsIgnoreCase(command.split(" ")[1]))
                    .collect(Collectors.toList())
                    .get(0).getPrice();
            payment.addProduct(command.split(" ")[1], price);
            return true;
        } catch (ServiceException e) {
            return false;
        }
    }
}
