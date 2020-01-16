package by.training.payment.service;

import by.training.payment.entity.Market;
import by.training.payment.service.exeception.ServiceException;

public interface MarketService {
    Market getMarket() throws ServiceException;
}
