package by.training.exercise19_mvc;

import java.util.Scanner;

public class TerminalServiceExercise19 {
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

    int getResult(int [] arr) {
        int maxCount = 1;
        int maxCountValue = arr[0];
        for (int i = 0; i < arr.length; i++) {
            int countCurrent = 1;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] == arr[j]) {
                    countCurrent++;
                    if (countCurrent > maxCount) {
                        maxCount = countCurrent;
                        maxCountValue = arr[i];
                    } else if (countCurrent == maxCount && arr[i] < maxCountValue) {
                        maxCountValue = arr[i];
                    }
                }
            }
        }
        return maxCountValue;
    }
}
