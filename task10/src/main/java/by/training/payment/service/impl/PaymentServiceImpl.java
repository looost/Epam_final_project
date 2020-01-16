package by.training.payment.service.impl;

import by.training.payment.entity.Payment;
import by.training.payment.entity.Product;
import by.training.payment.service.PaymentService;
import by.training.payment.service.Validation;
import by.training.payment.service.exeception.ServiceException;
import by.training.payment.service.factory.ServiceFactory;

public class PaymentServiceImpl implements PaymentService {
    private static final Validation validation = new Validation();

    @Override
    public boolean addProduct(Payment payment, Product product, int countOfProduct) throws ServiceException {
        if (validation.productInMarket(ServiceFactory.getInstance().getMarketService().getMarket(), product.getProductName())) {
            throw new ServiceException("В магазине такого товара нет!");
        }
        return payment.addProduct(product, countOfProduct);
    }

    @Override
    public boolean removeProduct(Payment payment, String productName, int countOfProduct) {
        return payment.removeProduct(productName, countOfProduct);
    }
}
