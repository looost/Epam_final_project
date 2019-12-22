package by.training.exercise14_mvc;

import java.util.Scanner;

public class TerminalServiceExercise14 {
    public int getTerminalDate(String message) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите значение " + message + ": ");
        while (!sc.hasNextInt()) {
            System.out.print("Введите int: ");
            sc.next();
        }
        return sc.nextInt();
    }

    public int howManyNumber(int number) {
        int count = 0;
        while (number % 10 != 0) {
            number /= 10;
            count++;
        }
        return count;
    }

    public int maxAmountNumbers(int a, int b) {
        if (a == b) {
            return -1;
        }
        return Math.max(a, b);
    }
}
