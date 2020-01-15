package by.training.abstracttextfile.entity;

import java.util.ArrayList;
import java.util.List;

public class TextFile extends File {

    private List<String> data = new ArrayList<>();

    public TextFile(Directory directory, String name) {
        super(directory, name);
        if (!contains(this.getExpansion().toUpperCase())) {
            throw new IllegalArgumentException("Файл не текстовый!");
        }
    }

    public List<String> getData() {
        return data;
    }

    public void addData(String data) {
        this.data.add(data);
    }

    public void addData(List<String> data) {
        this.data.addAll(data);
    }

    public void removeData(String data) {
        this.data.remove(data);
    }


    public enum Expansion {
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
