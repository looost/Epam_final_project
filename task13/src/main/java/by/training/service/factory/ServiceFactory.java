package by.training.service.factory;

import by.training.service.builder.BaseBuilder;
import by.training.service.builder.SerialDomBuilder;
import by.training.service.builder.SerialSAXBuilder;
import by.training.service.builder.SerialStAXBuilder;

public class ServiceFactory {

    private static final ServiceFactory instance = new ServiceFactory();
    private final BaseBuilder saxBuilder = new SerialSAXBuilder();
    private final BaseBuilder staxBuilder = new SerialStAXBuilder();
    private final BaseBuilder domBuilder = new SerialDomBuilder();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public BaseBuilder getSaxBuilder() {
        return saxBuilder;
    }

    public BaseBuilder getStaxBuilder() {
        return staxBuilder;
    }

    public BaseBuilder getDomBuilder() {
        return domBuilder;
    }
}
