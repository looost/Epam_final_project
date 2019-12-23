package by.training.abiturient.view;

import by.training.abiturient.entity.AbiturientBean;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class View {
    public void showTerminal(Stream <AbiturientBean> stream) {
        stream.forEach(System.out::println);
    }

    public void writeInFile(Stream<AbiturientBean> stream) throws FileNotFoundException {
        File file = new File("src\\main\\resources\\result.txt");
        PrintWriter printWriter = new PrintWriter(file);
        List<AbiturientBean> arr = stream.collect(Collectors.toList());
        for (AbiturientBean abiturientBean : arr) {
            printWriter.println(abiturientBean);
        }
        printWriter.close();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
