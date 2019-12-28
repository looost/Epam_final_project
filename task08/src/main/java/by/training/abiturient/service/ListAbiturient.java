package by.training.abiturient.service;


import by.training.abiturient.entity.Abiturient;

import java.util.*;
import java.util.stream.Stream;

public class ListAbiturient {

    private List<Abiturient> abiturientList = new ArrayList<>();

    public List<Abiturient> getAbiturientList() {
        return abiturientList;
    }

    public void setAbiturientList(List<Abiturient> abiturientList) {
        this.abiturientList = abiturientList;
    }

    public Stream<Abiturient> abiturientsWithUnsatisfactoryGrades() {
        return abiturientList
                .stream()
                .filter(a -> a.getFirstGrade() < 4 || a.getSecondGrade() < 4 || a.getThirdGrade() < 4);
    }

    public Stream<Abiturient> abiturientsWithHigherGrade(int grade) {
        return abiturientList
                .stream()
                .filter(x -> (x.getFirstGrade() + x.getSecondGrade() + x.getThirdGrade()) > grade);
    }

    public Stream<Abiturient> sortBySumGradeWorst(int limit) {
        return abiturientList
                .stream()
                .sorted(Comparator.comparing(Abiturient::getSumGrade))
                .limit(limit);
    }

    public Stream<Abiturient> sortBySumGradeBest(int limit) {
        return abiturientList
                .stream()
                .sorted(Comparator.comparing(Abiturient::getSumGrade).reversed())
                .limit(limit);
    }

    public Stream<Abiturient> sortByLastName() {
        return abiturientList
                .stream()
                .sorted(Comparator.comparing(Abiturient::getLastName));
    }
}
