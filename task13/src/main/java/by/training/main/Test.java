package by.training.main;

import by.training.service.SerialHandler;
import by.training.service.builder.BaseBuilder;
import by.training.service.builder.SerialDomBuilder;
import by.training.service.builder.SerialSAXBuilder;
import by.training.service.builder.SerialStAXBuilder;
import by.training.service.validation.ValidationXML;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class Test {
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            System.out.println("1 — английский /n 2 — белорусский \n любой — русский ");
            char j = 0;
            try {
                j = (char) System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String country = "";
            String language = "";
            switch (j) {
                case '1':
                    country = "US";
                    language = "EN";
                    break;
                case '2':
                    country = "BY";
                    language = "BY";
                    break;
            }
            Locale current = new Locale(language, country);
            ResourceBundle rb = ResourceBundle.getBundle("property.text", current);
            String s1 = rb.getString("str1");
            System.out.println(s1);
            String s2 = rb.getString("str2");
            System.out.println(s2);
        }
    }
}
