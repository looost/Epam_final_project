package by.training.task4.exercise19_mvc;

import java.util.ArrayList;

public class ConsoleViewExercise19 implements ViewExercise19 {
    @Override
    public void showTask() {

    }

    @Override
    public void showResult(boolean arrFirstNumbers, boolean arrSecondNumbers, int howManyEven) {
        System.out.println("Are only even digits in first number: " + arrFirstNumbers);
        System.out.println("Are only even digits in second number: " + arrSecondNumbers);
        System.out.println("Count of even digits in sum: " + howManyEven);
    }
}
