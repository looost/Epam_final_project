package by.training.service.factory;

import by.training.service.*;
import by.training.service.impl.*;

/**
 * Class for accessing a specific Service.
 * Represents an implementation of the Singleton and Factory design pattern.
 */
public final class ServiceFactory {
    private static final ServiceFactory INSTANCE = new ServiceFactory();
    private final CommentService commentService = new CommentServiceImpl();
    private final UserService userService = new UserServiceImpl();
    private final CountryService countryService = new CountryServiceImpl();
    private final StudioService studioService = new StudioServiceImpl();
    private final SerialService serialService = new SerialServiceImpl();
    private final GenreService genreService = new GenreServiceImpl();
    private final SecurityService securityService = new SecurityServiceImpl();


    private ServiceFactory() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static ServiceFactory getInstance() {
        return INSTANCE;
    }

    /**
     * Gets serial service.
     *
     * @return the serial service
     */
    public SerialService getSerialService() {
        return serialService;
    }

    /**
     * Gets comment service.
     *
     * @return the comment service
     */
    public CommentService getCommentService() {
        return commentService;
    }

    /**
     * Gets genre service.
     *
     * @return the genre service
     */
    public GenreService getGenreService() {
        return genreService;
    }

    /**
     * Gets user service.
     *
     * @return the user service
     */
    public UserService getUserService() {
        return userService;
    }

    /**
     * Gets country service.
     *
     * @return the country service
     */
    public CountryService getCountryService() {
        return countryService;
    }

    /**
     * Gets studio service.
     *
     * @return the studio service
     */
    public StudioService getStudioService() {
        return studioService;
    }

    /**
     * Gets security service.
     *
     * @return the security service
     */
    public SecurityService getSecurityService() {
        return securityService;
    }
}
