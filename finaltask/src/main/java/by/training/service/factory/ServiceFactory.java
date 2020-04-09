package by.training.service.factory;

import by.training.service.*;
import by.training.service.exception.ServiceException;
import by.training.service.impl.*;
import by.training.dao.Transaction;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    //    private final SerialService serialService = new SerialServiceImpl();
    private final CommentService commentService = new CommentServiceImpl();
    //    private final GenreService genreService = new GenreServiceImpl();
    private final UserService userService = new UserServiceImpl();
    private final CountryService countryService = new CountryServiceImpl();
    private final StudioService studioService = new StudioServiceImpl();


    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public SerialService getSerialService() throws ServiceException {
//        Transaction transaction = new TransactionFactory().createTransaction();
//        ((Service) serialService).setTransaction(transaction);
        return new SerialServiceImpl();
    }

    public CommentService getCommentService() {
        return commentService;
    }

    public GenreService getGenreService() {
        return new GenreServiceImpl();
    }

    public UserService getUserService() {
        return userService;
    }

    public CountryService getCountryService() {
        return countryService;
    }

    public StudioService getStudioService() {
        return studioService;
    }
}
