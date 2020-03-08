package by.training.service.factory;

import by.training.service.comment.CommentService;
import by.training.service.comment.impl.CommentServiceImpl;
import by.training.service.genre.GenreService;
import by.training.service.genre.impl.GenreServiceImpl;
import by.training.service.serial.SerialService;
import by.training.service.serial.impl.SerialServiceImpl;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    private final SerialService serialService = new SerialServiceImpl();
    private final CommentService commentService = new CommentServiceImpl();
    private final GenreService genreService = new GenreServiceImpl();


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
}
