package by.training.exercisestate.entity;

import java.util.List;

public class State {
    private String name;
    private City capital;
    private int area;
    private List <Region> regions;

    public State(String name, City capital, int area, List<Region> regions) {
        this.name = name;
        this.capital = capital;
        this.area = area;
        this.regions = regions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public City getCapital() {
        return capital;
    }

    public void setCapital(City capital) {
        this.capital = capital;
    }

    public List<Region> getRegions() {
        return regions;
    }

    public void setRegions(List<Region> regions) {
        this.regions = regions;
    }

    public Region getRegion(int index) {
        return regions.get(index);
    }

    public void setRegion(int index, Region region) {
        this.regions.set(index,region);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        State state = (State) o;

        if (name != null ? !name.equals(state.name) : state.name != null) return false;
        if (capital != null ? !capital.equals(state.capital) : state.capital != null)
            return false;
        return regions != null ? regions.equals(state.regions) : state.regions == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (capital != null ? capital.hashCode() : 0);
        result = 31 * result + (regions != null ? regions.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "State{" +
                "name='" + name + '\'' +
                ", nameOfCapital='" + capital + '\'' +
                ", regions=" + regions +
                '}';
    }
}