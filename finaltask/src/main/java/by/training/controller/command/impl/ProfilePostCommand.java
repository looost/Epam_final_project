package by.training.controller.command.impl;

import by.training.controller.command.Command;
import by.training.controller.servlet.handler.MultiFilesHandler;
import by.training.model.Serial;
import by.training.service.factory.ServiceFactory;
import by.training.utils.RoutingUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ProfilePostCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        try {
            ServletFileUpload fileUpload = new ServletFileUpload(new DiskFileItemFactory());
            List<FileItem> multiFiles = fileUpload.parseRequest(req);

            Serial serial = MultiFilesHandler.Handler(multiFiles);

            ServiceFactory.getInstance().getSerialService().create(serial);

        } catch (Exception e) {
            e.printStackTrace();
        }
        RoutingUtils.redirectToPage("/final/profile.html", resp);
    }
}
