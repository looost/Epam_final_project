package by.training.textfile.entity;

import java.io.IOException;

public class File {

    private java.io.File file;
    private Directory directory;

    public File(Directory directory, String name) {
        this.directory = directory;
        this.directory.addFile(this);
        this.file = new java.io.File(directory.getPath() + "\\" + name);
    }

    public java.io.File getFile() {
        return this.file;
    }

    public String getName() {
        return file.getName();
    }

    public String getPath() {
        return file.getPath();
    }

    public boolean delete() {
        return this.file.delete();
    }

    public String[] list() {
        return this.file.list();
    }

    public boolean exists() {
        return this.file.exists();
    }

    public boolean canRead() {
        return this.file.canRead();
    }

    public boolean canWrite() {
        return this.file.canWrite();
    }

    public boolean createNewFile() throws IOException {
        return this.file.createNewFile();
    }

    public boolean renameTo(String newName) {
        return this.file.renameTo(new java.io.File(this.getPath().substring(0, this.getPath()
                .lastIndexOf('\\')) + "\\" + newName));
    }

}
