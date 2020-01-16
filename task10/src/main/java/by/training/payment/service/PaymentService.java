package by.training.payment.service;

import by.training.payment.entity.Payment;
import by.training.payment.entity.Product;
import by.training.payment.service.exeception.ServiceException;

public interface PaymentService {
    boolean addProduct(Payment payment, Product product, int countOfProduct) throws ServiceException;

    boolean removeProduct(Payment payment, String productName, int countOfProduct);
}
