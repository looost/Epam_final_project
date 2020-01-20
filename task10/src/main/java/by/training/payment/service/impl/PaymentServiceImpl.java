package by.training.payment.service.impl;

import by.training.payment.entity.Market;
import by.training.payment.entity.Payment;
import by.training.payment.entity.Product;
import by.training.payment.service.PaymentService;
import by.training.payment.service.Validation;
import by.training.payment.service.exeception.ServiceException;

public class PaymentServiceImpl implements PaymentService {
    private static final Validation validation = new Validation();

    @Override
    public boolean addProduct(Market market, Payment payment, Product product, int countOfProduct) throws ServiceException {
        if (!validation.productInMarket(market, product.getProductName())) {
            throw new ServiceException("В магазине такого товара нет!");
        }
        return payment.addProduct(product, countOfProduct);
    }

    @Override
    public boolean removeProduct(Payment payment, String productName, int countOfProduct) throws ServiceException {
        if (!validation.productInPayment(payment, productName)) {
            throw new ServiceException("В корзине такого товара нет!");
        }
        return payment.removeProduct(productName, countOfProduct);
    }
}
