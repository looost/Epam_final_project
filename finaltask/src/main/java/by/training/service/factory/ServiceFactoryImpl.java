package by.training.service.factory;

import by.training.dao.EntityEnum;
import by.training.service.AbstractService;
import by.training.service.Service;
import by.training.service.exception.ServiceException;
import by.training.service.impl.*;
import by.training.service.transaction.Transaction;

import java.util.EnumMap;
import java.util.Map;

public class ServiceFactoryImpl {
    private static final Map<EntityEnum, AbstractService> repository = new EnumMap<>(EntityEnum.class);

    public ServiceFactoryImpl() {
        repository.put(EntityEnum.SERIAL, new SerialServiceImpl());
        repository.put(EntityEnum.USER, new UserServiceImpl());
        repository.put(EntityEnum.COMMENT, new CommentServiceImpl());
        repository.put(EntityEnum.GENRE, new GenreServiceImpl());
        repository.put(EntityEnum.COUNTRY, new CountryServiceImpl());
    }

    public static <T extends AbstractService> T getService(EntityEnum type) throws ServiceException {
//        AbstractService service = repository.get(type);
        Service service1 = (Service) repository.get(type);
        Transaction transaction = new TransactionFactory().createTransaction();
        service1.setTransaction(transaction);
        return (T) repository.get(type);
    }
}
