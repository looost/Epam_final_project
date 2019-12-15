package by.training.task3.exercise37_mvc;

import java.util.ArrayList;

public class TerminalLayerExercise37 implements ModelLayerExercise37 {
    @Override
    public ArrayList<Integer> getResult() {
        ArrayList<Integer> arr = new ArrayList<>();
        for (int a = 10; a < 100; a++) {
            for (int b = 10; b < 100; b++) {
                int value1 = ((a / 10) % 10) * 1000 + (a % 10) * 100 + ((b / 10) % 10) * 10 + (b % 10);
                int value2 = ((b / 10) % 10) * 1000 + (b % 10) * 100 + ((a / 10) % 10) * 10 + (a % 10);

                if (value1 % 99 == 0 && value2 % 49 == 0) {
                    arr.add(((a / 10) % 10) * 10 + (a % 10));
                    arr.add(((b / 10) % 10) * 10 + (b % 10));
                }
            }
        }
        return arr;
    }
}
