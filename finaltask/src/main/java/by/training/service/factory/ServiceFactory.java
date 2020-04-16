package by.training.service.factory;

import by.training.service.*;
import by.training.service.impl.*;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    private final CommentService commentService = new CommentServiceImpl();
    private final UserService userService = new UserServiceImpl();
    private final CountryService countryService = new CountryServiceImpl();
    private final StudioService studioService = new StudioServiceImpl();


    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public SerialService getSerialService() {
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
