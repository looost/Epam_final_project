package by.training.textfile.entity;

import java.util.ArrayList;
import java.util.List;

public class Directory {

    private final String path;
    private List<Directory> subDirectory;
    private List<File> files;

    public Directory(String path) {
        this.path = path;
        this.subDirectory = new ArrayList<>();
        this.files = new ArrayList<>();
    }


    public String getPath() {
        return path;
    }

    public List<Directory> getSubDirectory() {
        return subDirectory;
    }

    public List<File> getFiles() {
        return files;
    }

    public void addSubDirectory(Directory directory) {
        this.subDirectory.add(directory);
    }

    public void addFile(File file) {
        this.files.add(file);
    }
}
