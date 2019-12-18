package by.training.task5.exercise9_mvc;

import java.util.ArrayList;

public class ConsoleViewExercise9 implements ViewExercise9 {
    @Override
    public void showOriginArray(int[] arr) {
        System.out.print("Original array - [");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("]");
    }

    @Override
    public void showTask() {
        System.out.println("Даны действительные числа а1 ,а2 ,..., аn . Поменять местами наибольший и наименьший элементы.");
    }

    @Override
    public void showResult(int[] arr) {
        System.out.print("Modified array - [");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("]");
    }
}
