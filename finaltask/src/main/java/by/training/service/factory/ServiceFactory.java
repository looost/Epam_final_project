package by.training.service.factory;

import by.training.service.CommentService;
import by.training.service.UserService;
import by.training.service.impl.CommentServiceImpl;
import by.training.service.GenreService;
import by.training.service.impl.GenreServiceImpl;
import by.training.service.SerialService;
import by.training.service.impl.SerialServiceImpl;
import by.training.service.impl.UserServiceImpl;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    private final SerialService serialService = new SerialServiceImpl();
    private final CommentService commentService = new CommentServiceImpl();
    private final GenreService genreService = new GenreServiceImpl();
    private final UserService userService = new UserServiceImpl();


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
}
