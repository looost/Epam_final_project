package by.training.textfile.entity;

import by.training.textfile.FileException;

import java.util.ArrayList;
import java.util.List;

public class TextFile extends File {

    private List<String> data;
    private Expansion expansion;

    public TextFile(String name, String expansion, Directory directory) throws FileException {
        super(name, directory);
        data = new ArrayList<>();
        if (contains(expansion.toUpperCase())) {
            this.expansion = Expansion.valueOf(expansion.toUpperCase());
        } else {
            throw new FileException("Некорректное расширение файла");
        }
    }

    public List<String> getData() {
        return data;
    }

    public String getExpansion() {
        return expansion.name().toLowerCase();
    }

    private enum Expansion {
        DOC,
        PDF,
        TXT,
        FB2;
    }

    private boolean contains(String name) {
        for (Expansion e : Expansion.values()
        ) {
            if (e.name().equals(name.toUpperCase())) {
                return true;
            }
        }
        return false;
    }
}


