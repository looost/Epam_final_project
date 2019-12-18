package by.training.task5.exercise14_mvc;

import java.util.Scanner;

public class TerminalServiceExercise14 {
    int[] getArray() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter length array - ");
        while (!sc.hasNextInt()) {
            System.out.print("Enter int: ");
            sc.next();
        }
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Enter value " + i + " element: ");
            while (!sc.hasNextInt()) {
                System.out.print("Enter int: ");
                sc.next();
            }
            arr[i] = sc.nextInt();
        }
        return arr;
    }

    int getResult(int[] arr) {
        int max = arr[0];
        int min = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if ((i + 1) % 2 == 0 && arr[i] > max) {
                max = arr[i];
            } else if ((i + 1) % 2 != 0 && arr[i] < min) {
                min = arr[i];
            }
        }
        return max + min;
    }


}
