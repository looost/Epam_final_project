package by.training.service.factory;

import by.training.service.show.SerialService;
import by.training.service.show.impl.ShowServiceImpl;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    private final SerialService serialService = new ShowServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public SerialService getSerialService() {
        return serialService;
    }
}
