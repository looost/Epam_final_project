package by.training.payment.service;

import by.training.payment.entity.Market;
import by.training.payment.entity.Payment;

public class Validation {
    public boolean productInMarket(Market market, String productName) {
        return market.getProductList()
                .stream()
                .anyMatch(product -> product.getProductName().equalsIgnoreCase(productName));
    }

    public boolean productInPayment(Payment payment, String productName) {
        return payment.getPurchases()
                .stream()
                .anyMatch(purchase -> purchase.getProduct().getProductName().equalsIgnoreCase(productName));
    }
}
