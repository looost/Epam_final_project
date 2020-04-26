package by.training.controller.command.getcommand.impl;

import by.training.controller.command.Command;
import by.training.controller.command.CommandResponse;
import by.training.controller.command.RoutingType;
import by.training.model.Serial;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static by.training.utils.ConstantName.*;

public class LikedSerialGetCommand implements Command {
    @Override
    public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            try {
                String userId = req.getSession().getAttribute(ATTRIBUTE_USER_ID).toString();
                int page = 1;
                if (req.getParameter(PARAMETER_PAGE) != null) {
                    page = Integer.parseInt(req.getParameter(PARAMETER_PAGE));
                }
                int countSerial = ServiceFactory.getInstance().getSerialService().countAllSerialsThatILiked(userId);
                List<Serial> serialList = ServiceFactory.getInstance()
                        .getSerialService().findSerialsThatILiked(userId, page, COUNT_SERIAL_IN_LIKED_PAGE);
                req.setAttribute(ATTRIBUTE_SHOWS, serialList);
                req.setAttribute(PARAMETER_ITEM_ON_PAGE, COUNT_SERIAL_IN_LIKED_PAGE);
                req.setAttribute(PARAMETER_COUNT_ALL_SERIALS, countSerial);
                return new CommandResponse(RoutingType.FORWARD, ROUTING_LIKED_JSP, req, resp);
            } catch (ServiceException e) {
                return new CommandResponse(RoutingType.FORWARD, ROUTING_ERROR_PAGE, req, resp);
            }
    }
}
