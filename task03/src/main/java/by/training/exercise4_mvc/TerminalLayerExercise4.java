package by.training.exercise4_mvc;

import java.util.ArrayList;

public class TerminalLayerExercise4 implements ModelLayerExercise4 {
    @Override
    public ArrayList<Integer> getResult() {
        ArrayList<Integer> arr = new ArrayList<>() {
        };
        int value = 2;
        while (value <= 100) {
            arr.add(value);
            value += 2;
        }
        return arr;
    }
}
