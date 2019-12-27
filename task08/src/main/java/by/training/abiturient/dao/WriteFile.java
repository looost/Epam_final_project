package by.training.abiturient.dao;

import by.training.abiturient.entity.AbiturientBean;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WriteFile {
    public void writeToFile(String path, Stream<AbiturientBean> stream) throws FileNotFoundException {
        File file = new File(path);
        PrintWriter printWriter = new PrintWriter(file);
        for (AbiturientBean abiturientBean : stream.collect(Collectors.toList())) {
            printWriter.println(abiturientBean);
        }
        printWriter.close();
    }
}
