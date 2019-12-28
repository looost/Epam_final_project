package by.training.abiturientV2.service;

import by.training.abiturientV2.entity.Abiturient;

import java.util.stream.Stream;

public interface AbiturientService {
    Stream<Abiturient> abiturientsWithUnsatisfactoryGrades();

    Stream<Abiturient> abiturientsWithHigherGrade(int grade);

    Stream<Abiturient> sortBySumGradeWorst(int limit);

    Stream<Abiturient> sortBySumGradeBest(int limit);

    Stream<Abiturient> sortByLastName();
}
