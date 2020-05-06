package by.training.controller.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import static by.training.utils.ConstantName.INFO_LOGGER;

/**
 * Session attribute listener. Implementation of {@link HttpSessionAttributeListener}.
 */
@WebListener
public class SessionListenerImpl implements HttpSessionAttributeListener {

    private static final Logger logger = LogManager.getLogger(INFO_LOGGER);

    /**
     * Receives notification that an attribute has been added to a
     * session.
     *
     * @param event the HttpSessionBindingEvent containing the session
     *              and the name and value of the attribute that was added
     */
    @Override
    public void attributeAdded(final HttpSessionBindingEvent event) {
        StringBuilder sb = new StringBuilder();
        sb.append("Session attribute added: ").append(event.getClass().getSimpleName()).append(" : ").append(event.getName())
                .append(" : ").append(event.getValue());
        logger.info(sb);
    }

    /**
     * Receives notification that an attribute has been removed from a
     * session.
     *
     * @param event the HttpSessionBindingEvent containing the session
     * and the name and value of the attribute that was removed
     */
    @Override
    public void attributeRemoved(final HttpSessionBindingEvent event) {
        StringBuilder sb = new StringBuilder();
        sb.append("Session attribute removed: ").append(event.getClass().getSimpleName()).append(" : ").append(event.getName()).
                append(" : ").append(event.getValue());
        logger.info(sb);
    }

}
