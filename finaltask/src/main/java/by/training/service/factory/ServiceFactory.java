package by.training.service.factory;

import by.training.service.*;
import by.training.service.impl.*;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    private final SerialService serialService = new SerialServiceImpl();
    private final CommentService commentService = new CommentServiceImpl();
    private final GenreService genreService = new GenreServiceImpl();
    private final UserService userService = new UserServiceImpl();
    private final CountryService countryService = new CountryServiceImpl();


    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public SerialService getSerialService() {
        return serialService;
    }

    public CommentService getCommentService() {
        return commentService;
    }

    public GenreService getGenreService() {
        return genreService;
    }

    public UserService getUserService() {
        return userService;
    }

    public CountryService getCountryService() {
        return countryService;
    }
}
