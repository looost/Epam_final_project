package by.training.main;

import by.training.View;
import by.training.entity.Area;
import by.training.entity.Region;
import by.training.entity.State;

import java.util.ArrayList;

public class Runner {
    public static void main(String[] args) {
        Region minskRegion = new Region("Minsk region", "Minsk", 1_414_000, new ArrayList<Area>());
        Region brestRegion = new Region("Brest region", "Brest", 1_380_000, new ArrayList<Area>());
        Region vitebskRegion = new Region("Vitebsk region", "Vitebsk", 1_171_000, new ArrayList<Area>());
        Region gomelRegion = new Region("Gomel region", "Gomel", 1_409_000, new ArrayList<Area>());
        Region grodnoRegion = new Region("Grodno region", "Grodno", 1_039_000, new ArrayList<Area>());
        Region mogilevRegion = new Region("Mogilev region", "Mogilev", 1_052_000, new ArrayList<Area>());

        ArrayList <Region> arrayList = new ArrayList<>();
        arrayList.add(minskRegion);
        arrayList.add(brestRegion);
        arrayList.add(vitebskRegion);
        arrayList.add(gomelRegion);
        arrayList.add(grodnoRegion);
        arrayList.add(mogilevRegion);

        State state = new State("Belarus", "Minsk", 207_595, arrayList);

        View view = new View();

        view.showCapital(state);
        view.showNumberOfArea(state);
        view.showCountryArea(state);
        view.showRegionalCenter(state);

    }
}
