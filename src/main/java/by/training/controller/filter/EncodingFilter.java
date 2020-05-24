package by.training.controller.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.training.utils.ConstantName.ENCODING_UTF_8;
import static by.training.utils.ConstantName.INFO_LOGGER;

/**
 * Filter to encode for UTF-8. Implementation of {@link Filter}.
 */
public class EncodingFilter implements Filter {

    private static final Logger logger = LogManager.getLogger(INFO_LOGGER);

    /**
     * <p>Called by the web container
     * to indicate to a filter that it is being placed into service.</p>
     *
     * @param filterConfig a <code>FilterConfig</code> object containing the
     *                     filter's configuration and initialization parameters
     */
    @Override
    public void init(final FilterConfig filterConfig) {
        logger.info("Initializing encoding filter");
    }

    /**
     * Set character encoding UTF-8.
     *
     * @param servletRequest the <code>ServletRequest</code> object contains the client's request
     * @param servletResponse the <code>ServletResponse</code> object contains the filter's response
     * @param filterChain the <code>FilterChain</code> for invoking the next filter or the resource
     * @throws IOException if an I/O related error has occurred during the processing
     * @throws ServletException if an exception occurs that interferes with the
     *                          filter's normal operation
     *
     * @see UnavailableException
     */
    @Override
    public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse,
                         final FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(ENCODING_UTF_8);
        servletResponse.setCharacterEncoding(ENCODING_UTF_8);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    /**
     * Called by the web container to indicate to a filter that it is being
     * taken out of service.
     */
    @Override
    public void destroy() {
        logger.info("Destroying encoding filter");
    }
}
