package by.training.dragon.service.factory;

import by.training.dragon.service.CaveService;
import by.training.dragon.service.impl.CaveServiceImpl;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    private final CaveService caveService = new CaveServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public CaveService getCaveService() {
        return caveService;
    }
}
