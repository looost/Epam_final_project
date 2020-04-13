package by.training.controller.command.getcommand.impl;

import by.training.controller.command.Command;
import by.training.model.Country;
import by.training.model.Genre;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;
import by.training.utils.Constant;
import by.training.utils.RoutingUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class EditCountryGetCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int page = 1;
            if (req.getParameter("page") != null) {
                page = Integer.parseInt(req.getParameter("page"));
            }
            int countAllCountry = ServiceFactory.getInstance().getCountryService().countAllCountry();
            List<Country> countries = ServiceFactory.getInstance()
                    .getCountryService().findCountryPageByPage(page, Constant.COUNT_COUNTY_IN_ADMIN_PAGE);
            req.setAttribute("country", countries);
            req.setAttribute("countAllCountry", countAllCountry);
            req.setAttribute("itemsOnPage", Constant.COUNT_COUNTY_IN_ADMIN_PAGE);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        RoutingUtils.forwardToPage("/admin/country.jsp", req, resp);
    }
}
