package by.training.service.factory;

import by.training.service.Service;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    private final Service service = new Service();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public Service getService() {
        return service;
    }
}
