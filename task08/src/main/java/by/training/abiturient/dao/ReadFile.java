package by.training.abiturient.dao;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReadFile {

    public List<String> readFromFile(String path) throws IOException {
        Stream<String> streamFromFiles = Files.lines(Paths.get(path));
        return streamFromFiles.collect(Collectors.toList());
    }
}
