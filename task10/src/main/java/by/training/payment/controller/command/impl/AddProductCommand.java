package by.training.payment.controller.command.impl;

import by.training.payment.controller.command.Command;
import by.training.payment.entity.Payment;
import by.training.payment.entity.Product;
import by.training.payment.service.exeception.ServiceException;
import by.training.payment.service.factory.ServiceFactory;

import java.util.stream.Collectors;

public class AddProductCommand implements Command {

    @Override
    public String execute(Payment payment, String command) {
        if (command.split(" ").length < 2) {
            return "Не выбран товар для добавления!";
        }
        try {
            String productName = command.split(" ")[1];
            if (ServiceFactory.getInstance()
                    .getMarketService()
                    .getMarket()
                    .getProductList()
                    .stream().noneMatch(product -> product.getProductName()
                            .equalsIgnoreCase(productName))) {
                return "Такого товара нету!";
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
                payment.addProduct(new Product(productName, price), 1);
                return "OK";
            } else {
                int count = Integer.parseInt(command.split(" ")[2]);
                payment.addProduct(new Product(productName, price), count);
                return "OK";
            }
        } catch (ServiceException e) {
            return e.getMessage();
        } catch (NumberFormatException e) {
            return "Введено не числовое значение для количества продуктов!";
        }
    }
}
