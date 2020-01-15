package by.training.abstracttextfile.entity;

import java.util.ArrayList;
import java.util.List;

public class Directory {

    private List<File> fileList = new ArrayList<>();
    private List<Directory> subDirectory = new ArrayList<>();
    private String path;

    public Directory(String path) {
        this.path = path;
    }

    public List<File> getFileList() {
        return fileList;
    }

    public void addFile(File file) {
        this.fileList.add(file);
    }

    public List<Directory> getSubDirectory() {
        return subDirectory;
    }

    public void addSubDirectory(Directory subDirectory) {
        this.subDirectory.add(subDirectory);
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
