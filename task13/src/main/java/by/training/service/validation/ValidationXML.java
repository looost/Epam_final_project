package by.training.service.validation;

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
            System.out.println(fileName + " is valid.");
            return true;
        } catch (SAXException e) {
            System.err.print("validation " + fileName + " is not valid because "
                    + e.getMessage());
        } catch (IOException e) {
            System.err.print(fileName + " is not valid because "
                    + e.getMessage());
        }
        return false;
    }
}
