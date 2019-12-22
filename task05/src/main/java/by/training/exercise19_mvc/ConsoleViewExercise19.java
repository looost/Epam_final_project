package by.training.exercise19_mvc;

import java.util.Arrays;

public class ConsoleViewExercise19 {
    void showTask() {
        System.out.println(" В массиве целых чисел с количеством элементов n найти наиболее часто встречающееся число. Если таких чисел\n" +
                "несколько, то определить наименьшее из них.\n");
    }
    void showArray(int [] arr) {
        System.out.println("Текущий массив - " + Arrays.toString(arr));
    }
    void showFrequent (int value) {
        System.out.println("Наиболее часто встречающееся число - " + value);
    }
}
