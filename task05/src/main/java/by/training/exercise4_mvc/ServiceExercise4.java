package by.training.exercise4_mvc;

import java.util.Scanner;

public class ServiceExercise4 {

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


    boolean isRising(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i + 1] < arr[i]) {
                return false;
            }
        }
        return true;
    }
}
