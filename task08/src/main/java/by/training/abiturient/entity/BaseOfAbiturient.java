package by.training.abiturient.entity;

import java.util.ArrayList;
import java.util.List;

public class BaseOfAbiturient {
    private static BaseOfAbiturient instance;
    private List <AbiturientBean> baseOfAbiturient = new ArrayList<>();

    private BaseOfAbiturient() {

    }

    public static BaseOfAbiturient getInstance() {
        if (instance == null) {
            instance = new BaseOfAbiturient();
        }
        return instance;
    }

    public List<AbiturientBean> getBaseOfAbiturient() {
        return baseOfAbiturient;
    }

    public void setBaseOfAbiturient(List<AbiturientBean> baseOfAbiturient) {
        this.baseOfAbiturient = baseOfAbiturient;
    }
}
