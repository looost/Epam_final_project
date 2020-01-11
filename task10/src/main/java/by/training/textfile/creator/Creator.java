package by.training.textfile.creator;

import by.training.textfile.entity.Directory;
import by.training.textfile.entity.File;
import by.training.textfile.entity.TextFile;
import by.training.textfile.exception.FileException;
import by.training.textfile.service.TextFileService;

import java.io.IOException;
import java.util.Random;


public class Creator {
    private static TextFileService fileService = new TextFileService();


    public static Directory createDirectory() throws IOException {
        Directory directory = new Directory("D:\\test\\");
        Directory subDirectory1 = new Directory("D:\\test\\sub1\\");
        Directory subDirectory2 = new Directory("D:\\test\\sub2\\");

        directory.addSubDirectory(subDirectory1);
        directory.addSubDirectory(subDirectory2);


        try {
            TextFile textFile1 = new TextFile(directory, "text file 1.txt");
            TextFile textFile2 = new TextFile(subDirectory2, "pdf file 1.pdf");
            TextFile textFile3 = new TextFile(subDirectory2, "doc file 1.doc");

            textFile1.createNewFile();
            textFile2.createNewFile();
            textFile3.createNewFile();


        } catch (FileException e) {
            System.out.println(e.getMessage());
        }

        File file1 = new File(directory, "test file 1");
        File file2 = new File(subDirectory1, "test file 2");

        file1.createNewFile();
        file2.createNewFile();

        return directory;

    }

    public static void writeFile(TextFile file) throws IOException {
        Random random = new Random();
        char c;
        String str = "";
        for (int i = 0; i < 10; i++) {
            str = "";
            for (int j = 0; j < 20; j++) {
                c = (char) random.nextInt(265);
                str += c;
            }
            fileService.addData(file, str, true);
        }
    }


}
