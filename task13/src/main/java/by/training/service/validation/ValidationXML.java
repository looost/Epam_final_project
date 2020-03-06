package by.training.service.validation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class ValidationXML {
    private static final Logger logger = LogManager.getLogger("logger");

    public static boolean xmlIsValid(String fileName, String schemaName) {
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory factory = SchemaFactory.newInstance(language);
        File schemaLocation = new File(schemaName);
        try {
            // создание схемы
            Schema schema = factory.newSchema(schemaLocation);
            // создание валидатора на основе схемы
            Validator validator = schema.newValidator();
            // проверка документа
            Source source = new StreamSource(fileName);
            validator.validate(source);
            logger.info(fileName + " is valid.");
            return true;
        } catch (SAXException e) {
            logger.error("validation " + fileName + " is not valid because " + e.getMessage());
        } catch (IOException e) {
            logger.error(fileName + " is not valid because " + e.getMessage());
        }
        return false;
    }

    public static boolean xmlResolutionIsValid(String filePath) {
        return filePath.substring(filePath.lastIndexOf(".") + 1).equalsIgnoreCase("xml");
    }
}
