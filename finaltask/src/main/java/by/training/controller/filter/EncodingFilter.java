package by.training.controller.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;

import static by.training.utils.ConstantName.INFO_LOGGER;

public class EncodingFilter implements Filter {

    private static final Logger logger = LogManager.getLogger(INFO_LOGGER);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("Initializing encoding filter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("UTF-8");
        filterChain.doFilter(servletRequest, servletResponse);
    }


    @Override
    public void destroy() {
        logger.info("Destroying encoding filter");
    }
}
