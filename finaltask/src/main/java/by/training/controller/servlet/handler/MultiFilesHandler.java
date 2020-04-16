package by.training.controller.servlet.handler;

import by.training.model.Country;
import by.training.model.Genre;
import by.training.model.Serial;
import by.training.model.Studio;
import org.apache.commons.fileupload.FileItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.sql.Date;
import java.util.*;

public class MultiFilesHandler {

    private static final Logger logger = LogManager.getLogger("debug");

    public static MultiFilesResponse handler(List<FileItem> multiFiles, HttpServletRequest req) throws Exception {
        Serial serial = new Serial();
        boolean status = false;
        serial.setGenres(new ArrayList<>());
        serial.setStudio(new Studio());
        serial.setCountry(new Country());
        Map<String, String> errors = new HashMap<>();
        for (FileItem f : multiFiles
        ) {
            switch (f.getFieldName()) {
                case "name":
                    if (f.getString("UTF-8").equals("")) {
                        status = true;
                        errors.put("nameProblem", "Введите название!");
                        break;
                    }
                    serial.setName(f.getString("UTF-8"));
                    break;
                case "description":
                    if (f.getString("UTF-8").equals("")) {
                        status = true;
                        errors.put("descriptionProblem", "Введите описание!");
                        break;
                    }
                    serial.setDescription(f.getString("UTF-8"));
                    break;
                case "logo":
                    if (f.getName().equals("")) {
                        status = true;
                        errors.put("logoProblem", "Загрузите файл логотипа!");
                        break;
                    }
                    File logo = new File("..\\webapps\\final\\img\\" + UUID.randomUUID().toString() + f.getName());
                    f.write(logo);
                    serial.setLogo("img\\" + logo.getName());
                    break;
                case "full_logo":
                    if (f.getName().equals("")) {
                        status = true;
                        errors.put("fullLogoProblem", "Загрузите файл полного логотипа!");
                        break;
                    }
                    File fullLogo = new File("..\\webapps\\final\\img\\" + UUID.randomUUID().toString() + f.getName());
                    f.write(fullLogo);
                    serial.setFullLogo("img\\" + fullLogo.getName());
                    break;
                case "release_date":
                    try {
                        serial.setReleaseDate(Date.valueOf(f.getString()));
                    } catch (IllegalArgumentException e) {
                        status = true;
                        errors.put("releaseDateProblem", "Неверно введена дата!");
                        break;
                    }
                    break;
                case "genre":
                    Genre genre = new Genre();
                    genre.setId(Integer.parseInt(f.getString()));
                    serial.getGenres().add(genre);
                    break;
                case "country":
                    serial.getCountry().setId(Integer.parseInt(f.getString()));
                    break;
                case "studio":
                    serial.getStudio().setId(Integer.parseInt(f.getString()));
                    break;
                default:
                    break;
            }
        }
        if (status) {
            req.getSession().setAttribute("error", errors);
        }
        return new MultiFilesResponse(status, serial);
    }

}
