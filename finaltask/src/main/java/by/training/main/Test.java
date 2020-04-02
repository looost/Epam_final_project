package by.training.main;

import by.training.model.Country;
import by.training.service.exception.ServiceException;
import by.training.service.factory.ServiceFactory;

import java.util.List;

public class Test {
    public static void main(String[] args) {


        try {

            Country country = new Country("TESTCOUTNRY");
            ServiceFactory.getInstance().getCountryService().create(country);


            List<Country> countryList = ServiceFactory.getInstance().getCountryService().findAll();
            countryList.forEach(System.out::println);
        } catch (ServiceException e) {
        }
    }
}
