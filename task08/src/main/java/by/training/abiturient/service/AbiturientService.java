package by.training.abiturient.service;


import by.training.abiturient.entity.AbiturientBean;
import by.training.abiturient.entity.BaseOfAbiturient;

import java.util.*;
import java.util.stream.Stream;

public class AbiturientService {

    public Stream <AbiturientBean> abiturientsWithUnsatisfactoryGrades () {
       return BaseOfAbiturient.getInstance()
                .getBaseOfAbiturient()
                .stream()
                .filter(a -> a.getAverage() < 4);
    }
    public Stream <AbiturientBean> abiturientsWithHigherGrade (int sumGrade) {
        return BaseOfAbiturient.getInstance()
                .getBaseOfAbiturient()
                .stream()
                .filter(x -> (x.getFirst_grade() + x.getSecond_grade() + x.getThird_grade()) > sumGrade);
    }

    public void sortBySumGradeWorst() {
        BaseOfAbiturient.getInstance()
                .getBaseOfAbiturient()
                .sort(Comparator.comparing(AbiturientBean::getSumGrade).reversed());
    }

    public void sortBySumGradeBest() {
        BaseOfAbiturient.getInstance()
                .getBaseOfAbiturient()
                .sort(Comparator.comparing(AbiturientBean::getSumGrade));
    }
}
