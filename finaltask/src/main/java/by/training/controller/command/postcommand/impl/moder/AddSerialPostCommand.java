package by.training.controller.command.postcommand.impl.moder;

import by.training.controller.command.Command;
import by.training.controller.servlet.handler.MultiFilesHandler;
import by.training.controller.servlet.handler.MultiFilesResponse;
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

public class AddSerialPostCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ServletFileUpload fileUpload = new ServletFileUpload(new DiskFileItemFactory());
            List<FileItem> multiFiles = fileUpload.parseRequest(req);
            MultiFilesResponse multiFilesResponse = MultiFilesHandler.handler(multiFiles, req);
            //Serial serial = MultiFilesHandler.handler(multiFiles);
            //System.out.println(serial);
            if (multiFilesResponse.isHaveProblem()) {
                RoutingUtils.redirectToPage("/final/admin/serial.html", resp);
            } else {
                ServiceFactory.getInstance().getSerialService().create(multiFilesResponse.getSerial());
                resp.sendRedirect(req.getHeader("referer"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
