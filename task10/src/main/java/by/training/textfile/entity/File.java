package by.training.textfile.entity;

public class File {
    private Directory directory;
    private String name;

    public File(String name, Directory directory) {
        this.directory = directory;
        this.name = name;
    }

    public Directory getDirectory() {
        return directory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
