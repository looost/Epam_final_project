package by.training.payment.service;

import by.training.payment.entity.Market;
import by.training.payment.entity.Product;
import by.training.payment.service.exeception.ServiceException;

public interface MarketService {
    Market getMarket() throws ServiceException;

    Product getProduct(String productName) throws ServiceException;

    double getProductPrice(String productName) throws ServiceException;
}
