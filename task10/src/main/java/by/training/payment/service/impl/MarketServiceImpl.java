package by.training.payment.service.impl;

import by.training.payment.entity.Product;
import by.training.payment.service.Creator;
import by.training.payment.service.MarketService;
import by.training.payment.service.Parser;
import by.training.payment.dao.exception.DAOException;
import by.training.payment.dao.factory.DAOFactory;
import by.training.payment.entity.Market;
import by.training.payment.service.exeception.ServiceException;

import java.util.List;

public class MarketServiceImpl implements MarketService {
    private final Creator creator = new Creator();
    private final Parser parser = new Parser();

    @Override
    public Market getMarket() throws ServiceException {
        Market market = new Market();
        try {
            List<String> list = DAOFactory.getInstance().getDao().readData();
            String[] parseString;
            for (String str : list
            ) {
                parseString = parser.parsFile(str, "; ");
                creator.createMarket(market, parseString[0], Double.parseDouble(parseString[1]));
            }
            return market;
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Product getProduct(String productName) throws ServiceException {
        for (Product p : getMarket().getProductList()
        ) {
            if (p.getProductName().equalsIgnoreCase(productName)) {
                return p;
            }
        }
        throw new ServiceException("Такого товара нет в магазине!");
    }

    @Override
    public double getProductPrice(String productName) throws ServiceException {
        return getProduct(productName).getPrice();
    }
}