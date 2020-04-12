package by.training.controller.command.postcommand.impl.moder;

import by.training.controller.command.Command;
import by.training.controller.servlet.handler.MultiFilesHandler;
import by.training.model.Serial;
import by.training.service.factory.ServiceFactory;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ChangeSerialPostCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ServletFileUpload fileUpload = new ServletFileUpload(new DiskFileItemFactory());
            List<FileItem> multiFiles = fileUpload.parseRequest(req);

            Serial serial = MultiFilesHandler.Handler(multiFiles);
            serial.setId(Integer.parseInt(req.getParameter("id")));

            ServiceFactory.getInstance().getSerialService().update(serial);

        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.sendRedirect(req.getHeader("referer"));
    }
}
