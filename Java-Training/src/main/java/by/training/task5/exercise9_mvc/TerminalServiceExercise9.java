package by.training.task5.exercise9_mvc;

import java.util.Scanner;

public class TerminalServiceExercise9 {
    int[] getArray() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Задай длинну массива - ");
        while (!sc.hasNextInt()) {
            System.out.print("Введите int: ");
            sc.next();
        }
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Задайте значение " + i + "-ому элементу: ");
            while (!sc.hasNextInt()) {
                System.out.print("Введите int: ");
                sc.next();
            }
            arr[i] = sc.nextInt();
        }
        return arr;
    }

    int[] getResult(int[] arr) {
        int min = arr[0];
        int indexMin = 0;
        int max = arr[0];
        int indexMax = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
                indexMax = i;
            }
            if (arr[i] < min) {
                min = arr[i];
                indexMin = i;
            }
        }
        int emp = arr[indexMin];
        arr[indexMin] = arr[indexMax];
        arr[indexMax] = emp;
        return arr;
    }
}
