package by.training.controller.command.impl;

import by.training.controller.Controller;
import by.training.controller.parsercommand.CommandResponse;
import by.training.controller.command.CommandServlet;
import by.training.entity.Serial;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public class ParserCommand implements CommandServlet {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
                    File file = new File(req.getServletContext().getRealPath("") + "\\WEB-INF\\userdate\\" + item.getName());
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
            Set<Serial> serials = commandResponse.getValue();

            HttpSession session = req.getSession();

            session.setAttribute("parser", name);
            session.setAttribute("serials", serials);
            resp.sendRedirect("/task13/parserget");

//            req.setAttribute("parser", name);
//            req.setAttribute("serials", serials);
//            req.getServletContext().getRequestDispatcher("/jsp/parse.jsp").forward(req, resp);
        } else {
            req.setAttribute("error", commandResponse.getStatus());
            req.getServletContext().getRequestDispatcher("/jsp/error.jsp").forward(req, resp);
        }
    }
}
