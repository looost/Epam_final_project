package by.training.controller.command.postcommand.impl.moder;

import by.training.controller.command.Command;
import by.training.controller.command.CommandResponse;
import by.training.controller.command.RoutingType;
import by.training.controller.servlet.handler.MultiFilesHandler;
import by.training.controller.servlet.handler.MultiFilesResponse;
import by.training.service.factory.ServiceFactory;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static by.training.utils.ConstantName.*;

public class ChangeSerialPostCommand implements Command {


    @Override
    public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ServletFileUpload fileUpload = new ServletFileUpload(new DiskFileItemFactory());
            List<FileItem> multiFiles = fileUpload.parseRequest(req);
            MultiFilesResponse multiFilesResponse = MultiFilesHandler.handler(multiFiles, req);

            if (multiFilesResponse.isHaveProblem()) {
                return new CommandResponse(RoutingType.REDIRECT, ROUTING_SHOW_PAGE + "?id=" + req.getParameter(PARAMETER_ID), req, resp);
                //RoutingUtils.redirectToPage("/final/admin/serial.html", resp);
            } else {
                multiFilesResponse.getSerial().setId(Integer.parseInt(req.getParameter(PARAMETER_ID)));
                ServiceFactory.getInstance().getSerialService().update(multiFilesResponse.getSerial());
                return new CommandResponse(RoutingType.REDIRECT, ROUTING_SHOW_PAGE + "?id=" + req.getParameter(PARAMETER_ID), req, resp);
                // resp.sendRedirect(req.getHeader("referer"));
            }


        } catch (Exception e) {
            e.printStackTrace();
            return new CommandResponse(RoutingType.REDIRECT, ROUTING_ERROR_JSP, req, resp);
        }
    }
}
