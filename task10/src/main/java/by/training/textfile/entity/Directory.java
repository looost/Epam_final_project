package by.training.textfile.entity;

import java.util.ArrayList;
import java.util.List;

public class Directory {

    private String name;
    private List<Directory> subDirectory;
    private List<File> files;

    public Directory(String name) {
        this.name = name;
        this.subDirectory = new ArrayList<>();
    }

    public Directory(String name, List<Directory> subDirectory) {
        this(name);
        this.subDirectory = subDirectory;
        this.files = new ArrayList<>();
    }

    public Directory(String name, List<Directory> subDirectory, List<File> files) {
        this(name, subDirectory);
        this.files = files;
    }

    public Directory(Directory directory) {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Directory> getSubDirectory() {
        return subDirectory;
    }

    public void addSubDirectory(Directory subDirectory) {
        this.subDirectory.add(subDirectory);
    }

    public List<File> getFiles() {
        return files;
    }

    public void addFile(File file) {
        this.files.add(file);
    }
}
