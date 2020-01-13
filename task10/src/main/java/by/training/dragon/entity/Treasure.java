package by.training.dragon.entity;

import java.io.Serializable;
import java.util.Objects;

public class Treasure implements Serializable {

    private String name;
    private int price;


    public Treasure() {
        this.name = "default";
        this.price = 0;
    }

    public Treasure(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Treasure treasure = (Treasure) o;

        if (price != treasure.price) return false;
        return name != null ? name.equals(treasure.name) : treasure.name == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + price;
        return result;
    }

    @Override
    public String toString() {
        return "Treasure{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
