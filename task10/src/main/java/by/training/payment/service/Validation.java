package by.training.payment.service;

import by.training.payment.entity.Market;

public class Validation {
    public boolean productInMarket(Market market, String productName) {
        return market.getProductList()
                .stream()
                .noneMatch(product -> product.getProductName().equalsIgnoreCase(productName));
    }
}
