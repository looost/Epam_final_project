package by.training.abiturient.entity;

import by.training.abiturient.service.ListAbiturient;

public class BaseOfAbiturient {

    private static BaseOfAbiturient instance;
    private ListAbiturient listOfAbiturient = new ListAbiturient();

    private BaseOfAbiturient() {
    }

    public static BaseOfAbiturient getInstance() {
        if (instance == null) {
            instance = new BaseOfAbiturient();
        }
        return instance;
    }

    public ListAbiturient getListOfAbiturient() {
        return listOfAbiturient;
    }

    public void setListOfAbiturient(ListAbiturient listOfAbiturient) {
        this.listOfAbiturient = listOfAbiturient;
    }
}
