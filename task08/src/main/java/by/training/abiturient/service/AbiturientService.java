package by.training.abiturient.service;


import by.training.abiturient.entity.AbiturientBean;
import by.training.abiturient.entity.BaseOfAbiturient;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AbiturientService {

    Scanner scanner = new Scanner(System.in);

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

    public int whatShow() {
        System.out.println("Для списка абитуриентов, имеющих неудовлетворительные оценки, нажмите 1");
        System.out.println("Для списка абитуриентов, у которых сумма баллов выше заданной, нажмите 2");

        while (!scanner.hasNextInt()) {
            System.out.println("Введите верное значение: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    public void sortBySumGrade() {
        BaseOfAbiturient.getInstance()
                .getBaseOfAbiturient()
                .sort(Comparator.comparing(AbiturientBean::getSumGrade).reversed());
    }
}
