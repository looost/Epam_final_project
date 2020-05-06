package by.training.controller.command.postcommand.impl.moder;

import by.training.controller.command.Command;
import by.training.controller.command.CommandResponse;
import by.training.controller.command.RoutingType;
import by.training.model.Country;
import by.training.model.Genre;
import by.training.model.Serial;
import by.training.model.Studio;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;
import by.training.utils.RoutingUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
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

/**
 * Command to save {@link by.training.model.Serial}.
 *
 * @see Command
 * @see by.training.controller.command.postcommand.PostCommandProvider
 */
public class SaveSerialPostCommand implements Command {

    /**
     * A Logger object is used to log messages for a application error.
     */
    private static final Logger logger = LogManager.getLogger(ERROR_LOGGER);

    /**
     * Command to save {@link by.training.model.Serial}. Handles both change and creation requests.
     * To work with downloading files use {@link org.apache.commons.fileupload.FileUpload}.
     * A folder for saving files declared in context.
     * If the file was not transferred, the default image is set if it is not present.
     * @param req  the HttpServletRequest
     * @param resp the HttpServletResponse
     * @return the {@link CommandResponse}
     * @throws ServletException if the request for the POST could not be handled
     * @throws IOException if an input or output error is  detected when the servlet handles the POST request
     * @see RoutingUtils
     */
    @Override
    public CommandResponse execute(final HttpServletRequest req,
                                   final HttpServletResponse resp) throws ServletException, IOException {
        try {
            String serialId = req.getParameter(PARAMETER_ID) != null ? req.getParameter(PARAMETER_ID) : String.valueOf(0);
            ServletFileUpload fileUpload = new ServletFileUpload(new DiskFileItemFactory());
            List<FileItem> multiFiles = fileUpload.parseRequest(req);
            Serial serial = new Serial.Builder()
                    .withId(Integer.parseInt(serialId))
                    .withGenres(new ArrayList<>())
                    .withCountry(new Country())
                    .withStudio(new Studio())
                    .build();

            if (!serialId.equals("0")) {
                serial = ServiceFactory.getInstance().getSerialService().findById(serialId);
                serial.setGenres(new ArrayList<>());
            }

            for (FileItem item : multiFiles
            ) {
                if (item.isFormField()) {
                    processFormField(item, serial);
                } else {
                    processUploadedFile(item, serial, req);
                }
            }
            ServiceFactory.getInstance().getSerialService().save(serial);
            return new CommandResponse(RoutingType.REDIRECT, req.getHeader(HEADER_REFERER), req, resp);
        } catch (ServiceException e) {
            if (e.getCode() == HttpServletResponse.SC_BAD_REQUEST) {
                req.getSession().setAttribute(ATTRIBUTE_SERIAL_PROBLEM, true);
                return new CommandResponse(RoutingType.REDIRECT, req.getHeader(HEADER_REFERER), req, resp);
            } else {
                logger.error(e);
                return RoutingUtils.routingErrorPage(req, resp, e.getCode());
            }
        } catch (Exception e) {
            logger.error("File cannot write", e);
            return RoutingUtils.routingErrorPage(req, resp, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Method for processing fields. For reading fields the encoding is used UTF-8.
     *
     * @param item   the {@link FileItem}
     * @param serial the {@link Serial}
     * @throws UnsupportedEncodingException if the requested character encoding is not available
     * @throws ServiceException             if the date is incorrect
     */
    private void processFormField(final FileItem item,
                                  final Serial serial) throws UnsupportedEncodingException, ServiceException {
        switch (item.getFieldName()) {
            case PARAMETER_NAME:
                serial.setName(item.getString(ENCODING_UTF_8));
                break;
            case PARAMETER_DESCRIPTION:
                serial.setDescription(item.getString(ENCODING_UTF_8));
                break;
            case PARAMETER_RELEASE_DATE:
                try {
                    serial.setReleaseDate(Date.valueOf(item.getString()));
                    break;
                } catch (IllegalArgumentException e) {
                    throw new ServiceException(e, HttpServletResponse.SC_BAD_REQUEST);
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
                logger.error("Not found field");
                break;
        }
    }

    /**
     * Method for processing files.
     * If the picture was not transferred and the series field is empty then the default picture is set.
     * If the picture was not transferred and the series field is not empty, processing is skipped.
     * In another case, the picture was transferred.
     * The file is saved in the folder for files and the name of the picture is written in the value of the series field.
     * A folder for saving files declared in context.
     *
     * @param item   the {@link FileItem}
     * @param serial the {@link Serial}
     * @param req    HttpServletRequest
     * @throws Exception if an error occurred while processing the file
     */
    private void processUploadedFile(final FileItem item, final Serial serial,
                                     final HttpServletRequest req) throws Exception {
        String fileName = UUID.randomUUID().toString() + item.getName();
        switch (item.getFieldName()) {
            case PARAMETER_LOGO:
                if (item.getName().equals("") && serial.getLogo() == null) {
                    serial.setLogo(DEFAULT_IMG_NAME);
                    break;
                } else if (item.getName().equals("") && serial.getLogo() != null) {
                    break;
                } else {
                    String filePath = req.getServletContext().getInitParameter(PATH_TO_UPLOAD_FILE_DIR)
                            + fileName;
                    File logo = new File(filePath);
                    item.write(logo);
                    File copyFile = new File("D:\\Training\\finaltask\\src\\main\\webapp\\img\\" + fileName);
                    FileUtils.copyFile(logo, copyFile);
                    serial.setLogo(logo.getName());
                    break;
                }
            case PARAMETER_FULL_LOGO:
                if (item.getName().equals("") && serial.getFullLogo() == null) {
                    serial.setFullLogo(DEFAULT_IMG_NAME);
                    break;
                } else if (item.getName().equals("") && serial.getFullLogo() != null) {
                    break;
                } else {
                    String filePath = req.getServletContext().getInitParameter(PATH_TO_UPLOAD_FILE_DIR)
                            + fileName;
                    File fullLogo = new File(filePath);
                    item.write(fullLogo);
                    File copyFile = new File("D:\\Training\\finaltask\\src\\main\\webapp\\img\\" + fileName);
                    FileUtils.copyFile(fullLogo, copyFile);
                    serial.setFullLogo(fullLogo.getName());
                    break;
                }
            default:
                logger.error("Not found field");
                break;
        }
    }
}
