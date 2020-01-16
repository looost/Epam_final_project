package by.training.payment.servise.factory;

import by.training.payment.servise.MarketService;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    private final MarketService marketService = new MarketService();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public MarketService getMarketService() {
        return marketService;
    }
}
