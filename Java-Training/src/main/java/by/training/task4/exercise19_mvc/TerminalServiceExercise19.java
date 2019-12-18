package by.training.task4.exercise19_mvc;

import java.util.ArrayList;
import java.util.Scanner;

public class TerminalServiceExercise19 {

    public int getTerminalDate(String message) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter value " + message + ": ");
        while (!sc.hasNextInt()) {
            System.out.print("Enter int: ");
            sc.next();
        }
        return sc.nextInt();
    }

    ArrayList<Integer> numberArray(int number, int length) {
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = length; length != 0; length--) {
            arr.add((number / (int) Math.pow(10, length - 1)) % 10);
        }
        return arr;
    }

    boolean justEven(ArrayList<Integer> arr) {
        for (Integer num : arr
        ) {
            if (num % 2 == 0) {
                return false;
            }
        }
        return true;
    }

    int howManyNumber(int number) {
        int count = 0;
        while (number % 10 != 0) {
            number /= 10;
            count++;
        }
        return count;
    }

    int howManyEven(ArrayList<Integer> arr) {
        int count = 0;
        for (Integer num : arr
        ) {
            if (num % 2 == 0) {
                count++;
            }
        }
        return count;
    }

    int getSum(int f, int s) {
        return f + s;
    }
}
