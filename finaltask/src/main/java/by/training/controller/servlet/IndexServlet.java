package by.training.controller.servlet;


import by.training.controller.command.CommandResponse;
import by.training.controller.command.getcommand.GetCommandProvider;
import by.training.controller.command.postcommand.PostCommandProvider;
import by.training.service.exception.ServiceException;
import by.training.utils.RoutingUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.training.utils.ConstantName.INFO_LOGGER;

public class IndexServlet extends HttpServlet {

    private static final long serialVersionUID = -3508576924089672311L;
    private static final Logger logger = LogManager.getLogger(INFO_LOGGER);

    @Override
    public void init() throws ServletException {
        super.init();
        logger.info("Initializing servlet");
    }

    private static String getRequestURI(HttpServletRequest req) {
        return req.getServletPath()
                .substring(1, req.getServletPath().lastIndexOf(".")).replaceAll("/", "_");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI2 = getRequestURI(req);
        CommandResponse commandResponse = GetCommandProvider.getInstance().getCommand(requestURI2).execute(req, resp);
        RoutingUtils.routing(commandResponse);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI2 = getRequestURI(req);
        CommandResponse commandResponse = PostCommandProvider.getInstance().getCommand(requestURI2).execute(req, resp);
        RoutingUtils.routing(commandResponse);
    }

    @Override
    public void destroy() {
        super.destroy();
        logger.info("Destroying servlet");
    }
}
