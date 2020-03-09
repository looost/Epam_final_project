package by.training.controller.servlet;


import by.training.model.Genre;
import by.training.model.Serial;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;
import by.training.utils.RoutingUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {

    private static final long serialVersionUID = -3508576924089672311L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");


        try {
            List<Serial> serialList = ServiceFactory.getInstance().getSerialService().findAll();
            List<Genre> genres = ServiceFactory.getInstance().getGenreService().findAll();
            List <Serial> last =  serialList.stream().sorted(Comparator.comparing(Serial::getId).reversed()).limit(4).collect(Collectors.toList());
//            List <Serial> serialList = ServiceFactory.getInstance().getSerialService().findAllSerial2(1,4);
            req.setAttribute("shows", serialList);
            req.setAttribute("last", last);
            req.setAttribute("genres", genres);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        RoutingUtils.forwardToPage("index.jsp", req, resp);

    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        PrintWriter printWriter = resp.getWriter();
//        String name = req.getParameter("name");
//        String password = req.getParameter("password");
//        printWriter.write(name + password + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//        repository.save(new User(name,password));
//        resp.sendRedirect(req.getContextPath() + "/signUp");
//    }

//    public static void main(String[] args) {
//        List<Show> list = DaoFactory.getInstance().getDao().getAllShow();
//        System.out.println(list.get(4).getName()+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//    }
}
