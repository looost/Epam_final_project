package by.training.textfile.service;


import by.training.textfile.entity.TextFile;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TextFileService {

    private FileWriter fileWriter;
    private FileReader fileReader;
    private Scanner scanner;

    public List<String> getData(TextFile file) throws IOException {
        fileReader = new FileReader(file.getFile());
        scanner = new Scanner(fileReader);
        List<String> list = new ArrayList<>();
        while (scanner.hasNextLine()) {
            list.add(scanner.nextLine());
        }
        fileReader.close();
        return list;
    }

    public void addData(TextFile file, String data, boolean append) throws IOException {
        fileWriter = new FileWriter(file.getFile(), append);
        fileWriter.write(data + "\r\n");
        fileWriter.close();
    }
}
