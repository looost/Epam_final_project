package by.training.payment.service.factory;

import by.training.payment.service.impl.MarketServiceImpl;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    private final MarketServiceImpl marketService = new MarketServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public MarketServiceImpl getMarketService() {
        return marketService;
    }
}
