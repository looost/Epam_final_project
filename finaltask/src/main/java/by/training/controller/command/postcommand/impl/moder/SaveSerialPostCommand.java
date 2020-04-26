package by.training.controller.command.postcommand.impl.moder;

import by.training.controller.command.Command;
import by.training.controller.command.CommandResponse;
import by.training.controller.command.CommandUtil;
import by.training.controller.command.RoutingType;
import by.training.model.Country;
import by.training.model.Genre;
import by.training.model.Serial;
import by.training.model.Studio;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.*;

import static by.training.utils.ConstantName.*;

public class SaveSerialPostCommand implements Command {

    private static final Logger logger = LogManager.getLogger(DEBUG_LOGGER);

//    @Override
//    public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        try {
//            ServletFileUpload fileUpload = new ServletFileUpload(new DiskFileItemFactory());
//            List<FileItem> multiFiles = fileUpload.parseRequest(req);
//            MultiFilesResponse multiFilesResponse = MultiFilesHandler.handler(multiFiles, req);
//            if (multiFilesResponse.isHaveProblem()) {
//                return new CommandResponse(RoutingType.REDIRECT, ROUTING_SERIAL_PAGE, req, resp);
//                //RoutingUtils.redirectToPage("/final/admin/serial.html", resp);
//            } else {
//                ServiceFactory.getInstance().getSerialService().create(multiFilesResponse.getSerial());
//                return new CommandResponse(RoutingType.REDIRECT, ROUTING_SERIAL_PAGE, req, resp);
//                // resp.sendRedirect(req.getHeader("referer"));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }


    @Override
    public CommandResponse execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String serialId = req.getParameter(PARAMETER_ID) != null ? req.getParameter(PARAMETER_ID) : String.valueOf(0);
            ServletFileUpload fileUpload = new ServletFileUpload(new DiskFileItemFactory());
            List<FileItem> multiFiles = fileUpload.parseRequest(req);
            Serial serial = new Serial();
            serial.setId(Integer.parseInt(serialId));
            serial.setGenres(new ArrayList<>());
            serial.setStudio(new Studio());
            serial.setCountry(new Country());
            Map<String, String> errors = new HashMap<>();
            for (FileItem item: multiFiles
                 ) {
                if (item.isFormField()) {
                    processFormField(item, serial, errors);
                } else {
                    processUploadedFile(item, serial, req);
                }
            }
            if (errors.isEmpty()) {
                ServiceFactory.getInstance().getSerialService().save(serial);
                return new CommandResponse(RoutingType.REDIRECT, req.getHeader(HEADER_REFERER), req, resp);
            } else {
                req.getSession().setAttribute(ATTRIBUTE_ERROR, errors);
                return new CommandResponse(RoutingType.REDIRECT, ROUTING_SERIAL_PAGE, req, resp);
            }
        } catch (ServiceException e) {
            logger.debug(e);
            if (e.getCode() == HttpServletResponse.SC_BAD_REQUEST) {
                return new CommandResponse(RoutingType.REDIRECT, req.getHeader(HEADER_REFERER), req, resp);
            } else {
                return CommandUtil.routingErrorPage(req, resp, e.getCode());
            }
        } catch (Exception e) {
            logger.debug("File cannot write", e);
            return CommandUtil.routingErrorPage(req, resp, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private void processFormField(FileItem item, Serial serial, Map<String, String> errors) throws UnsupportedEncodingException {
        switch (item.getFieldName()) {
            case PARAMETER_NAME:
                if (item.getString(ENCODING_UTF_8).equals("")) {
                    errors.put(ATTRIBUTE_NAME_PROBLEM, "Введите название!");
                    break;
                } else {
                    serial.setName(item.getString(ENCODING_UTF_8));
                    break;
                }
            case PARAMETER_DESCRIPTION:
                if (item.getString(ENCODING_UTF_8).equals("")) {
                    errors.put(ATTRIBUTE_DESCRIPTION_PROBLEM, "Введите описание!");
                    break;
                } else {
                    serial.setDescription(item.getString(ENCODING_UTF_8));
                    break;
                }
            case PARAMETER_RELEASE_DATE:
                try {
                    serial.setReleaseDate(Date.valueOf(item.getString()));
                    break;
                } catch (IllegalArgumentException e) {
                    errors.put(ATTRIBUTE_RELEASE_DATE_PROBLEM, "Неверно введена дата!");
                    break;
                }
            case PARAMETER_GENRE:
                Genre genre = new Genre();
                genre.setId(Integer.parseInt(item.getString()));
                serial.getGenres().add(genre);
                break;
            case PARAMETER_COUNTRY:
                serial.getCountry().setId(Integer.parseInt(item.getString()));
                break;
            case PARAMETER_STUDIO:
                serial.getStudio().setId(Integer.parseInt(item.getString()));
                break;
            default:
                logger.debug("Not found field");
                break;
        }
    }

    private void processUploadedFile(FileItem item, Serial serial, HttpServletRequest req) throws Exception {
        switch (item.getFieldName()) {
            case PARAMETER_LOGO:
                if (item.getName().equals("")) {
                    serial.setLogo(PATH_TO_DEFAULT_IMG);
                    break;
                } else {
                    String fileName = UUID.randomUUID().toString() + item.getName();
                    String filePath = req.getServletContext().getInitParameter(PATH_TO_UPLOAD_FILE_DIR)
                            + fileName;
                    File logo = new File(filePath);
                    item.write(logo);
                    serial.setLogo("img\\" + logo.getName());
                    break;
                }
            case PARAMETER_FULL_LOGO:
                if (item.getName().equals("")) {
                    serial.setFullLogo(PATH_TO_DEFAULT_IMG);
                    break;
                } else {
                    String filePath = req.getServletContext().getInitParameter(PATH_TO_UPLOAD_FILE_DIR)
                            + UUID.randomUUID().toString() + item.getName();
                    File fullLogo = new File(filePath);
                    item.write(fullLogo);
                    serial.setFullLogo("img\\" + fullLogo.getName());
                    break;
                }
            default:
                logger.debug("Not found field");
                break;
        }
    }
}
