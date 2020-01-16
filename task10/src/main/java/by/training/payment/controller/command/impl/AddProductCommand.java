package by.training.payment.controller.command.impl;

import by.training.payment.controller.command.Command;
import by.training.payment.entity.Payment;
import by.training.payment.entity.Product;
import by.training.payment.servise.exeception.ServiceException;
import by.training.payment.servise.factory.ServiceFactory;

import java.util.stream.Collectors;

public class AddProductCommand implements Command {

    @Override
    public boolean execute(Payment payment, String command) {
        if (command.split(" ").length < 2) {
            System.err.println("Не выбран товар для добавления!");
            return false;
        }
        try {
            String productName = command.split(" ")[1];
            if (ServiceFactory.getInstance()
                    .getMarketService()
                    .getMarket()
                    .getProductList()
                    .stream().noneMatch(product -> product.getProductName()
                            .equalsIgnoreCase(productName))) {
                System.err.println("Такого товара нету!");
                return false;
            }
            double price = ServiceFactory.getInstance()
                    .getMarketService()
                    .getMarket()
                    .getProductList()
                    .stream()
                    .filter(product -> product.getProductName().equalsIgnoreCase(productName))
                    .collect(Collectors.toList())
                    .get(0)
                    .getPrice();

            int length = command.split(" ").length;
            if (length == 2) {
                return payment.addProduct(new Product(productName, price), 1);
            } else if (length == 3) {
                int count = Integer.parseInt(command.split(" ")[2]);
                payment.addProduct(new Product(productName, price), count);
                return true;
            }
            return false;
        } catch (ServiceException e) {
            return false;
        }
    }
}
