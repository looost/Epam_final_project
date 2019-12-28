package by.training.abiturientV2.dao;

import by.training.abiturientV2.entity.Abiturient;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileAbiturientDAO implements AbiturientDAO {
    private static final String PATH = "src\\main\\resources\\AbiturientList.txt";
    Stream<String> streamFromFiles;
    List<Abiturient> listAbiturient = new ArrayList<>();

    {
        try {
            streamFromFiles = Files.lines(Paths.get(PATH));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public FileAbiturientDAO() {
        for (String str : streamFromFiles.collect(Collectors.toList())
        ) {
            String[] lines = str.split("; ");
            listAbiturient.add(new Abiturient(Integer.parseInt(lines[0]), lines[1],
                    lines[2], lines[3], lines[4],
                    lines[5], Integer.parseInt(lines[6]), Integer.parseInt(lines[7]), Integer.parseInt(lines[8])));
        }
    }

    @Override
    public Stream<Abiturient> abiturientsWithUnsatisfactoryGrades() {
        return listAbiturient
                .stream()
                .filter(a -> a.getFirstGrade() < 4 || a.getSecondGrade() < 4 || a.getThirdGrade() < 4);
    }

    @Override
    public Stream<Abiturient> abiturientsWithHigherGrade(int grade) {
        return listAbiturient
                .stream()
                .filter(x -> (x.getFirstGrade() + x.getSecondGrade() + x.getThirdGrade()) > grade);
    }

    @Override
    public Stream<Abiturient> sortBySumGradeWorst(int limit) {
        return listAbiturient
                .stream()
                .sorted(Comparator.comparing(Abiturient::getSumGrade))
                .limit(limit);
    }

    @Override
    public Stream<Abiturient> sortBySumGradeBest(int limit) {
        return listAbiturient
                .stream()
                .sorted(Comparator.comparing(Abiturient::getSumGrade).reversed())
                .limit(limit);
    }

    @Override
    public Stream<Abiturient> sortByLastName() {
        return listAbiturient
                .stream()
                .sorted(Comparator.comparing(Abiturient::getLastName));
    }
}
