package by.training.controller.servlet;

import by.training.controller.command.CommandResponse;
import by.training.controller.command.getcommand.GetCommandProvider;
import by.training.controller.command.postcommand.PostCommandProvider;
import by.training.utils.RoutingUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.training.utils.ConstantName.INFO_LOGGER;

/**
 * Application entry point.
 */
public class IndexServlet extends HttpServlet {

    private static final long serialVersionUID = -3508576924089672311L;
    /**
     * A Logger object is used to log messages for a application info.
     */
    private static final Logger logger = LogManager.getLogger(INFO_LOGGER);

    /**
     * Called by the servlet container to indicate to a servlet that the
     * servlet is being placed into service.
     *
     * @throws ServletException if an exception occurs that interrupts the servlet's normal operation
     */
    @Override
    public void init() throws ServletException {
        super.init();
        logger.info("Initializing servlet");
    }

    /**
     * The method of processing a search query in the form
     * that is presented in {@link by.training.controller.command.CommandName}.
     *
     * @param req an {@link HttpServletRequest} object that contains the request
     *            the client has made of the servlet
     * @return processed search string
     */
    private static String getRequestURI(final HttpServletRequest req) {
        return req.getServletPath()
                .substring(1, req.getServletPath().lastIndexOf(".")).replaceAll("/", "_");

    }

    /**
     * Called by the server to allow a servlet to handle a GET request.
     *
     * @param req an {@link HttpServletRequest} object that contains the request
     *           the client has made of the servlet
     * @param resp an {@link HttpServletResponse} object that contains the response
     *           the servlet sends to the client
     * @throws ServletException if the request for the GET could not be handled
     * @throws IOException if an input or output error is  detected when the servlet handles the GET request
     */
    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        String requestURI2 = getRequestURI(req);
        CommandResponse commandResponse = GetCommandProvider.getInstance().getCommand(requestURI2).execute(req, resp);
        RoutingUtils.routing(commandResponse);
    }

    /**
     * Called by the server to allow a servlet to handle a GET request.
     *
     * @param req an {@link HttpServletRequest} object that contains the request
     *           the client has made of the servlet
     * @param resp an {@link HttpServletResponse} object that contains the response
     *           the servlet sends to the client
     * @throws ServletException if the request for the POST could not be handled
     * @throws IOException if an input or output error is detected when the servlet handles the request
     */
    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        String requestURI2 = getRequestURI(req);
        CommandResponse commandResponse = PostCommandProvider.getInstance().getCommand(requestURI2).execute(req, resp);
        RoutingUtils.routing(commandResponse);
    }

    /**
     * Called by the servlet container to indicate to a servlet that the
     * servlet is being taken out of service.
     */
    @Override
    public void destroy() {
        super.destroy();
        logger.info("Destroying servlet");
    }
}
