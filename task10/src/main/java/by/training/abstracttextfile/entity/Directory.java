package by.training.abstracttextfile.entity;

import java.util.ArrayList;
import java.util.List;

public class Directory {

    private List<File> fileList = new ArrayList<>();
    private List<Directory> subDirectory = new ArrayList<>();
    private String path = "C:\\";
    private String name;

    public Directory(String name) {
        this.name = name;
        this.path += name + "\\";
    }


    public List<File> getFiles() {
        return fileList;
    }

    public void addFile(File file) {
        this.fileList.add(file);
    }

    public List<Directory> getSubDirectory() {
        return subDirectory;
    }

    public Directory getDirectory(String name) {
        for (Directory d : subDirectory
        ) {
            if (d.name.equalsIgnoreCase(name)) {
                return d;
            }
        }
        return this;
    }

    public void addSubDirectory(Directory subDirectory) {
        subDirectory.setPath(this.path + subDirectory.getName() + "\\");
        this.subDirectory.add(subDirectory);
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
