package by.training.abiturient.service;

import by.training.abiturient.entity.AbiturientBean;
import by.training.abiturient.entity.BaseOfAbiturient;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AbiturientCreator {
    public void fillFromStream(BaseOfAbiturient baseOfAbiturient, ArrayList<String> arr) {
        for (String str : arr
        ) {
            String [] lines = str.split("; ");
            baseOfAbiturient.getBaseOfAbiturient().add(new AbiturientBean(Integer.parseInt(lines[0]), lines[1],
                    lines[2], lines[3], lines[4],
                    Integer.parseInt(lines[5]), Integer.parseInt(lines[6]),Integer.parseInt(lines[7]),Integer.parseInt(lines[8])));

//                ArrayList <Integer> arr = new ArrayList<>();
//                arr.add(Integer.parseInt(lines[6]));
//                arr.add(Integer.parseInt(lines[7]));
//                arr.add(Integer.parseInt(lines[8]));
//                baseOfAbiturient.getBaseOfAbiturient().add(new AbiturientBean(
//                    Integer.parseInt(lines[0]), lines[1], lines[2], lines[3], lines[4],
//                                     Integer.parseInt(lines[5]), arr));
        }
    }
}
