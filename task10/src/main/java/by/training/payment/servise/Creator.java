package by.training.payment.servise;

import by.training.payment.entity.Market;
import by.training.payment.entity.Product;

class Creator {

    boolean createMarket(Market market, String productName, double price) {
        return market.addProduct(new Product(productName, price));
    }
}
