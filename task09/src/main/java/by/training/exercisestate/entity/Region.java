package by.training.exercisestate.entity;

import java.util.List;

public class Region {
    private String name;
    private int population;
    private String capitalOfRegion;
    private List <Area> areas;

    public Region(String name, String capitalOfRegion,int population, List<Area> areas) {
        this.name = name;
        this.population = population;
        this.capitalOfRegion = capitalOfRegion;
        this.areas = areas;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getCapitalOfRegion() {
        return capitalOfRegion;
    }

    public void setCapitalOfRegion(String capitalOfRegion) {
        this.capitalOfRegion = capitalOfRegion;
    }

    public Area getArea(int index) {
        return areas.get(index);
    }

    public void setArea(int index, Area area) {
        this.areas.set(index,area);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Region region = (Region) o;

        if (population != region.population) return false;
        if (name != null ? !name.equals(region.name) : region.name != null) return false;
        if (capitalOfRegion != null ? !capitalOfRegion.equals(region.capitalOfRegion) : region.capitalOfRegion != null)
            return false;
        return areas != null ? areas.equals(region.areas) : region.areas == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + population;
        result = 31 * result + (capitalOfRegion != null ? capitalOfRegion.hashCode() : 0);
        result = 31 * result + (areas != null ? areas.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Region{" +
                "name='" + name + '\'' +
                ", population=" + population +
                ", capitalOfRegion='" + capitalOfRegion + '\'' +
                ", areas=" + areas +
                '}';
    }
}
