package by.training.abiturientV2.service;

public final class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    private final AbiturientService abiturientService = new AbiturientServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public AbiturientService getAbiturientService() {
        return abiturientService;
    }

}
