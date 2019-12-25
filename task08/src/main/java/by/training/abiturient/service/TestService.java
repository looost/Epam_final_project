package by.training.abiturient.service;

import by.training.abiturient.entity.AbiturientBean;

public class TestService {
    public static int sumGrade(AbiturientBean abiturientBean) {
        int sumGrade = 0;
        for (int grade : abiturientBean.getGrades()
        ) {
            sumGrade += grade;
        }
        return sumGrade;
    }
}
