package by.training.payment.service.factory;

import by.training.payment.service.MarketService;
import by.training.payment.service.PaymentService;
import by.training.payment.service.impl.MarketServiceImpl;
import by.training.payment.service.impl.PaymentServiceImpl;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    private final MarketService marketService = new MarketServiceImpl();
    private final PaymentService paymentService = new PaymentServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public MarketService getMarketService() {
        return marketService;
    }

    public PaymentService getPaymentService() {
        return paymentService;
    }
}
