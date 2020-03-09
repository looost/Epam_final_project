package by.training.controller.servlet;

import by.training.controller.Controller;
import by.training.controller.command.CommandResponse;
import by.training.entity.Serial;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;


@WebServlet("/xml/parser")
public class ParseServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = "";
        String filePath = "";

        try {
            ServletFileUpload fileUpload = new ServletFileUpload(new DiskFileItemFactory());
            List<FileItem> multiFiles = fileUpload.parseRequest(req);

            for (FileItem item : multiFiles
            ) {
                if (item.isFormField()) {
                    name = item.getString();
                } else {
                    File file = new File("E:\\Java-Training\\task13\\src\\main\\webapp\\WEB-INF\\userdate\\" + item.getName());
                        item.write(file);
                        filePath = file.getPath();
                }
            }

        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            req.getServletContext().getRequestDispatcher("/jsp/error.jsp").forward(req, resp);
        }

        Controller controller = new Controller();
        CommandResponse commandResponse = controller.getCommand(name).getSerials(filePath);

        if (commandResponse.getStatus().equals("OK")) {
            Set<Serial> serials = controller.getCommand(name).getSerials(filePath).getValue();
            req.setAttribute("parser", name);
            req.setAttribute("serials", serials);
            req.getServletContext().getRequestDispatcher("/jsp/parse.jsp").forward(req, resp);
        } else {
            req.setAttribute("error", commandResponse.getStatus());
            req.getServletContext().getRequestDispatcher("/jsp/error.jsp").forward(req, resp);
        }

    }
}
