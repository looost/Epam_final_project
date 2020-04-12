package by.training.controller.command.getcommand.impl;

import by.training.controller.command.Command;
import by.training.model.Studio;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;
import by.training.utils.Constant;
import by.training.utils.RoutingUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class EditStudioGetCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int page = 1;
            if (req.getParameter("page") != null) {
                page = Integer.parseInt(req.getParameter("page"));
            }
            int countAllStudio = ServiceFactory.getInstance().getStudioService().countAllStudio();
            List<Studio> studios = ServiceFactory.getInstance()
                    .getStudioService().findStudioPageByPage(page, Constant.COUNT_STUDIO_IN_ADMIN_PAGE);
            req.setAttribute("studio", studios);
            req.setAttribute("countAllStudio", countAllStudio);
            req.setAttribute("itemsOnPage", Constant.COUNT_STUDIO_IN_ADMIN_PAGE);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        RoutingUtils.forwardToPage("/admin/studio.jsp", req, resp);
    }
}

