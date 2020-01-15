package by.training.abstracttextfile.entity;

public class File {

    private Directory directory;
    private String name;
    private String expansion;

    public File(Directory directory, String name) {
        this.directory = directory;
        this.name = name;
        this.expansion = name.substring(name.lastIndexOf(".") + 1);
    }

    public String getDirectory() {
        return directory.getPath();
    }

    public String getNameWithDirectory() {
        return directory.getPath() + "\\" + this.name;
    }

    public void setDirectory(Directory directory) {
        this.directory = directory;
    }

    public String getName() {
        return name;
    }

    public void rename(String name) {
        this.name = name + '.' + this.expansion;
    }

    public String getExpansion() {
        return expansion;
    }

    public void setExpansion(String expansion) {
        this.expansion = expansion;
    }
}
