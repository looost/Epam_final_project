package by.training.task3.exercise4_mvc;

import java.util.ArrayList;

public class ConsoleViewExercise4 implements ViewEercise4 {
    @Override
    public void showTask() {
        System.out.println(" С помощью оператора while напишите программу вывода всех четных чисел в диапазоне от 2 до 100\n" +
                "включительно.");
    }

    @Override
    public void showResult(ArrayList<Integer> arr) {
        for (Integer value : arr
        ) {
            System.out.println(value);
        }
    }
}
