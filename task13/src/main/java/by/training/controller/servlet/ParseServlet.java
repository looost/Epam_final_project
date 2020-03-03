package by.training.controller.servlet;

import by.training.controller.Controller;
import by.training.entity.Serial;
import by.training.service.builder.BaseBuilder;
import by.training.service.builder.SerialStAXBuilder;
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/jsp/parse.jsp").forward(req, resp);
    }

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
                    File file = new File("D:\\Training\\task13\\src\\main\\resources\\data\\" + item.getName());
                    item.write(file);
                    filePath = file.getPath();
                }
            }

//                System.out.println(multiFiles.get(0).getName());
//                File file = new File("D:\\Training\\task13\\src\\main\\resources\\data\\" + multiFiles.get(0).getName());
//                multiFiles.get(0).write(file);
//                filePath = file.getPath();
//                System.out.println(file.getPath());

        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

        Controller controller = new Controller();


        Set<Serial> serials = controller.getCommand(name).getSerials(filePath);

        String path = filePath;


//        String path = "D:\\Training\\task13\\src\\main\\resources\\xml\\serials.xml";
//
//        BaseBuilder builder1 = new SerialStAXBuilder();
//        builder1.buildSetSerials(path);
//        Set<Serial> serials = builder1.getSerials();

        req.setAttribute("parser", name);
        req.setAttribute("serials", serials);

        req.getServletContext().getRequestDispatcher("/jsp/parse.jsp").forward(req, resp);
    }
}
