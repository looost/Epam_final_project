package by.training.controller.command.postcommand.impl.moder;

import by.training.controller.command.Command;
import by.training.controller.command.CommandResponse;
import by.training.controller.command.RoutingType;
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

    private static final String ROUTING_PAGE = "/final/admin/country.html";

    @Override
    public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ServletFileUpload fileUpload = new ServletFileUpload(new DiskFileItemFactory());
            List<FileItem> multiFiles = fileUpload.parseRequest(req);

            Serial serial = MultiFilesHandler.handler(multiFiles);
            serial.setId(Integer.parseInt(req.getParameter("id")));

            ServiceFactory.getInstance().getSerialService().update(serial);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new CommandResponse(RoutingType.REDIRECT, ROUTING_PAGE, req, resp);
        //resp.sendRedirect(req.getHeader("referer"));
    }
}
