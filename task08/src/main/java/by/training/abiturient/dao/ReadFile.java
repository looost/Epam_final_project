package by.training.abiturient.dao;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReadFile {
    public ArrayList<String> readFile(String path) throws IOException {
        Stream<String> streamFromFiles = Files.lines(Paths.get(path));
        return new ArrayList<>(streamFromFiles.collect(Collectors.toList()));
    }
}
