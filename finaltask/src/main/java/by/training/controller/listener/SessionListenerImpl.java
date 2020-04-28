package by.training.controller.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import static by.training.utils.ConstantName.INFO_LOGGER;

@WebListener
public class SessionListenerImpl implements HttpSessionAttributeListener {

    private static final Logger logger = LogManager.getLogger(INFO_LOGGER);

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        StringBuilder sb = new StringBuilder();
        sb.append("Session attribute added: ").append(event.getClass().getSimpleName()).append(" : ").append(event.getName())
                .append(" : ").append(event.getValue());
        logger.info(sb);
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        StringBuilder sb = new StringBuilder();
        sb.append("Session attribute removed: ").append(event.getClass().getSimpleName()).append(" : ").append(event.getName()).
                append(" : ").append(event.getValue());
        logger.info(sb);
    }

}
