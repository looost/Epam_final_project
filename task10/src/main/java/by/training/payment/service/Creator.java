package by.training.payment.service;

import by.training.payment.entity.Market;
import by.training.payment.entity.Product;

public class Creator {

    public boolean createMarket(Market market, String productName, double price) {
        return market.addProduct(new Product(productName, price));
    }
}
