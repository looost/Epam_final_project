package by.training.controller.servlet;


import by.training.controller.command.CommandProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class IndexServlet extends HttpServlet {

    private static final long serialVersionUID = -3508576924089672311L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        System.out.println(req.getRequestURI().substring(req.getRequestURI().lastIndexOf("/") + 1));
        String requestURI = req.getRequestURI().substring(req.getRequestURI().lastIndexOf("/") + 1);
        CommandProvider.getInstance().getCommand(requestURI).executeDoGet(req, resp);

//        resp.setCharacterEncoding("UTF-8");
//        try {
//            List<Serial> serialList = ServiceFactory.getInstance().getSerialService().findAll();
//            List<Genre> genres = ServiceFactory.getInstance().getGenreService().findAll();
//            List <Serial> last =  serialList.stream().sorted(Comparator.comparing(Serial::getId).reversed()).limit(4).collect(Collectors.toList());
//            req.setAttribute("shows", serialList);
//            req.setAttribute("last", last);
//            req.setAttribute("genres", genres);
//        } catch (ServiceException e) {
//            e.printStackTrace();
//        }
//        RoutingUtils.forwardToPage("index.jsp", req, resp);
//
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getRequestURI().substring(req.getRequestURI().lastIndexOf("/") + 1));
        String requestURI = req.getRequestURI().substring(req.getRequestURI().lastIndexOf("/") + 1);
        CommandProvider.getInstance().getCommand(requestURI).executeDoPost(req, resp);
    }
}
