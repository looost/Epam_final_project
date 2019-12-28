package by.training.abiturient.service;

import by.training.abiturient.entity.Abiturient;

public class TestService {
    public static int sumGrade(Abiturient abiturient) {
        int sumGrade = 0;
        for (int grade : abiturient.getGrades()
        ) {
            sumGrade += grade;
        }
        return sumGrade;
    }
}
