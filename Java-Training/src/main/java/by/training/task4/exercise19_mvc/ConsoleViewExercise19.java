package by.training.task4.exercise19_mvc;

import java.util.ArrayList;

public class ConsoleViewExercise19 implements ViewExercise19 {
    @Override
    public void showTask() {

    }

    @Override
    public void showResult(boolean arrFirstNumbers, boolean arrSecondNumbers, int howManyEven) {
        System.out.println("Are only even numbers: " + arrFirstNumbers);
        System.out.println("Are only even numbers: " + arrSecondNumbers);
        System.out.println("Count of even digits: " + howManyEven);
    }
}
