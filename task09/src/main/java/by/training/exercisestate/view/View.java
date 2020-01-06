package by.training.exercisestate.view;

import by.training.exercisestate.entity.Region;
import by.training.exercisestate.entity.State;

public class View {

    public void showCapital(State state) {
        System.out.println("Capital of " + state.getName() + " - " + state.getNameOfCapital());
    }

    public void showNumberOfArea(State state) {
        System.out.println("Number of area - " + state.getRegions().size());
    }

    public void showCountryArea(State state) {
        System.out.println("Country area - " + state.getArea());
    }

    public void showRegionalCenter(State state) {
        for (Region reg: state.getRegions()
        ) {
            System.out.println("Regional center " + reg.getName() + " - " + reg.getCapitalOfRegion());
        }
    }
}
