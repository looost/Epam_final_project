package by.training.service.factory;

import by.training.service.comment.CommentService;
import by.training.service.comment.impl.CommentServiceImpl;
import by.training.service.serial.SerialService;
import by.training.service.serial.impl.SerialServiceImpl;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    private final SerialService serialService = new SerialServiceImpl();
    private final CommentService commentService = new CommentServiceImpl();

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
}
