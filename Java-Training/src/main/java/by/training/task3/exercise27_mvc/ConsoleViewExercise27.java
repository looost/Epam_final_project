package by.training.task3.exercise27_mvc;

import java.util.ArrayList;

public class ConsoleViewExercise27 implements ViewExercise27 {
    @Override
    public void showTask() {
        System.out.println("Для каждого натурального числа в промежутке от m до n вывести все делители, кроме единицы и\n" +
                "самого числа. m и n вводятся с клавиатуры.");
    }

    @Override
    public void showResult(ArrayList<Integer> arr) {
        for (Integer value : arr
        ) {
            System.out.print(value + " ");
        }
    }
}
