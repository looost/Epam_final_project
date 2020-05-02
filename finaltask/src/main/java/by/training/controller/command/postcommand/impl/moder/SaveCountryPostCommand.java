package by.training.controller.command.postcommand.impl.moder;

import by.training.controller.command.Command;
import by.training.controller.command.CommandResponse;
import by.training.controller.command.CommandUtil;
import by.training.controller.command.RoutingType;
import by.training.model.Country;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;
import by.training.utils.ResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.training.utils.ConstantName.*;

public class SaveCountryPostCommand implements Command {

    private static final Logger logger = LogManager.getLogger(DEBUG_LOGGER);

    @Override
    public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String countryName = req.getParameter(PARAMETER_COUNTRY);
        String id = req.getParameter(PARAMETER_ID) != null ? req.getParameter(PARAMETER_ID) : String.valueOf(0);
        Country country = new Country(Integer.parseInt(id), countryName);
        try {
            ServiceFactory.getInstance().getCountryService().save(country);
            return new CommandResponse(RoutingType.REDIRECT, ROUTING_COUNTRY_PAGE, req, resp);
        } catch (ServiceException e) {
            if (e.getCode() == HttpServletResponse.SC_BAD_REQUEST) {
                req.getSession().setAttribute(ATTRIBUTE_COUNTRY_PROBLEM, true);
                return new CommandResponse(RoutingType.REDIRECT, ROUTING_COUNTRY_PAGE, req, resp);
            } else {
                logger.error(e);
                return CommandUtil.routingErrorPage(req, resp, e.getCode());
            }
        }
    }
}
