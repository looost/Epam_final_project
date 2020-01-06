package by.training.exercisestate.entity;

import java.util.List;

public class Area {
    private String name;
    private List <City> cities;

    public Area() {
    }

    public Area(String name, List<City> cities) {
        this.name = name;
        this.cities = cities;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City getCity(int index) {
        return cities.get(index);
    }

    public void setCity(int index, City city) {
        this.cities.set(index,city);
    }

    public void addCity(City city) {
        this.cities.add(city);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Area area = (Area) o;

        if (name != null ? !name.equals(area.name) : area.name != null) return false;
        return cities != null ? cities.equals(area.cities) : area.cities == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (cities != null ? cities.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Area{" +
                "name='" + name + '\'' +
                ", cities=" + cities +
                '}';
    }
}

