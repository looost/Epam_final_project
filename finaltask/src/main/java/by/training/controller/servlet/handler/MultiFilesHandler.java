package by.training.controller.servlet.handler;

import by.training.model.Country;
import by.training.model.Genre;
import by.training.model.Serial;
import by.training.model.Studio;
import org.apache.commons.fileupload.FileItem;

import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class MultiFilesHandler {

    public static Serial Handler(List<FileItem> multiFiles) throws Exception {
        Serial serial = new Serial();
        serial.setGenres(new ArrayList<>());
        serial.setStudio(new Studio());
        serial.setCountry(new Country());
        for (FileItem f : multiFiles
        ) {
            switch (f.getFieldName()) {
                case "name":
                    serial.setName(f.getString("UTF-8"));
                    break;
                case "description":
                    serial.setDescription(f.getString("UTF-8"));
                    break;
                case "logo":
                    File logo = new File("..\\webapps\\final\\img\\" + f.getName());
                    f.write(logo);
                    serial.setLogo("img\\" + logo.getName());
                    break;
                case "full_logo":
                    File fullLogo = new File("..\\webapps\\final\\img\\" + f.getName());
                    f.write(fullLogo);
                    serial.setFullLogo("img\\" + fullLogo.getName());
                    break;
                case "release_date":
                    serial.setReleaseDate(Date.valueOf(f.getString()));
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
        return serial;
    }


}
