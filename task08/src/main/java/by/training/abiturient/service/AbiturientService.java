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
               .filter(a -> a.getFirst_grade() < 4 || a.getSecond_grade() < 4 || a.getThird_grade() < 4);
    }
    public Stream <AbiturientBean> abiturientsWithHigherGrade (int sumGrade) {
        return BaseOfAbiturient.getInstance()
                .getBaseOfAbiturient()
                .stream()
                //       .filter(x -> TestService.sumGrade(x) > sumGrade);
                .filter(x -> (x.getFirst_grade() + x.getSecond_grade() + x.getThird_grade()) > sumGrade);
    }

    public void sortBySumGradeWorst() {
        BaseOfAbiturient.getInstance()
                .getBaseOfAbiturient()
                //       .sort(Comparator.comparing(abiturientBean -> TestService.sumGrade(abiturientBean)));
                .sort(Comparator.comparing(AbiturientBean::getSumGrade).reversed());
    }

    public void sortBySumGradeBest() {
        BaseOfAbiturient.getInstance()
                .getBaseOfAbiturient()
                //         .sort((abiturientBean, t1) -> TestService.sumGrade(abiturientBean));
                .sort(Comparator.comparing(AbiturientBean::getSumGrade));
    }
}
