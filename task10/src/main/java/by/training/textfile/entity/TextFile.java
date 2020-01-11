package by.training.textfile.entity;

import by.training.textfile.exception.FileException;

public class TextFile extends File {

    private Expansion expansion;
    private Directory directory;

    public TextFile(Directory directory, String name) throws FileException {
        super(directory, name);
        this.directory = directory;
        if (contains(getName().substring(getName().lastIndexOf(".") + 1))) {
            this.expansion = Expansion.valueOf(getName().substring(getName().lastIndexOf(".") + 1).toUpperCase());
        } else {
            throw new FileException("Файл не является текстовым");
        }
    }

    public String getDirectory() {
        return directory.getPath();
    }

    public String getExpansion() {
        return expansion.name().toLowerCase();
    }

    private enum Expansion {
        TXT,
        PDF,
        DOC,
        FB2;
    }

    private boolean contains(String name) {
        for (Expansion e : Expansion.values()
        ) {
            if (e.name().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

}
