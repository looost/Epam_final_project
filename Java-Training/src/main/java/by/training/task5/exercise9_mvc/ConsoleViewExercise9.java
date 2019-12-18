package by.training.task5.exercise9_mvc;

import java.util.ArrayList;

public class ConsoleViewExercise9 implements ViewExercise9 {
    @Override
    public void showOriginArray(int[] arr) {
        System.out.print("Изначальный массив - [");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("]");
    }

    @Override
    public void showTask() {
        System.out.println("Задание");
    }

    @Override
    public void showResult(int[] arr) {
        System.out.print("Измененный массив - [");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("]");
    }
}
