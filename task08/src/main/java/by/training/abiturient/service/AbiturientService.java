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
               .filter(a -> a.getFirstGrade() < 4 || a.getSecondGrade() < 4 || a.getThirdGrade() < 4);
    }
    public Stream <AbiturientBean> abiturientsWithHigherGrade (int sumGrade) {
        return BaseOfAbiturient.getInstance()
                .getBaseOfAbiturient()
                .stream()
                //       .filter(x -> TestService.sumGrade(x) > sumGrade);
                .filter(x -> (x.getFirstGrade() + x.getSecondGrade() + x.getThirdGrade()) > sumGrade);
    }

    public Stream<AbiturientBean> sortBySumGradeWorst(int limit) {
        return BaseOfAbiturient.getInstance()
                .getBaseOfAbiturient()
                .stream()
                .sorted(Comparator.comparing(AbiturientBean::getSumGrade))
                .limit(limit);
    }


    public Stream<AbiturientBean> sortBySumGradeBest(int limit) {
        return BaseOfAbiturient.getInstance()
                .getBaseOfAbiturient()
                .stream()
                .sorted(Comparator.comparing(AbiturientBean::getSumGrade).reversed())
                .limit(limit);
    }


    public Stream<AbiturientBean> sortByLastName() {
        return BaseOfAbiturient.getInstance()
                .getBaseOfAbiturient()
                .stream()
                .sorted(Comparator.comparing(AbiturientBean::getLastName));
    }
}
