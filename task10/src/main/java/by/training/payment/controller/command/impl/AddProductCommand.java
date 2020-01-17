package by.training.payment.controller.command.impl;

import by.training.payment.controller.command.Command;
import by.training.payment.entity.Market;
import by.training.payment.entity.Payment;
import by.training.payment.entity.Product;
import by.training.payment.service.MarketService;
import by.training.payment.service.exeception.ServiceException;
import by.training.payment.service.factory.ServiceFactory;

public class AddProductCommand implements Command {

    @Override
    public String execute(Market market, Payment payment, String command) {
        if (command.split(" ").length < 2) {
            return "Не выбран товар для добавления!";
        }
        MarketService marketService = ServiceFactory.getInstance().getMarketService();
        try {
            String productName = command.split(" ")[1];

            int length = command.split(" ").length;

            if (length == 2) {
                ServiceFactory.getInstance().
                        getPaymentService().
                        addProduct(market, payment, new Product(productName, marketService.getProductPrice(productName)), 1);
                return "OK";
            } else {
                int count = Integer.parseInt(command.split(" ")[2]);
                ServiceFactory.getInstance()
                        .getPaymentService()
                        .addProduct(market, payment, new Product(productName, marketService.getProductPrice(productName)), count);
                return "OK";
            }
        } catch (ServiceException e) {
            // logger
            return e.getMessage();
        } catch (NumberFormatException e) {
            // logger
            return "Введено не числовое значение для количества продуктов!";
        }
    }
}
