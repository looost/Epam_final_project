package by.training.task4.exercise19_mvc;

import java.util.ArrayList;

public class TerminalServiceExercise19 {
    private int number;
    private int n;
    private ArrayList<Integer> arr;

    TerminalServiceExercise19(int number) {
        this.number = number;
        this.n = howManyNumber(this.number);
        this.arr = numberArray();
    }

    private int getNumber() {
        return number;
    }

    private ArrayList<Integer> numberArray() {
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = n; n != 0; n--) {
            arr.add((number / (int) Math.pow(10, n - 1)) % 10);
        }
        return arr;
    }

    private boolean tolkoNechetnie() {
        System.out.println(arr);
        for (Integer num : arr
        ) {
            if (num % 2 == 0) {
                return false;
            }
        }
        return true;
    }

    private int howManyNumber(int number) {
        int count = 0;
        while (number % 10 != 0) {
            number /= 10;
            count++;
        }
        return count;
    }

    private int howManyChetni() {
        System.out.println(arr);
        int count = 0;
        for (Integer num : arr
        ) {
            if (num % 2 == 0) {
                count++;
            }
        }
        return count;
    }


    public static void main(String[] args) {
        TerminalServiceExercise19 first_number = new TerminalServiceExercise19(264);
        TerminalServiceExercise19 second_number = new TerminalServiceExercise19(323);
        System.out.println(first_number.tolkoNechetnie());
        System.out.println(second_number.tolkoNechetnie());
        TerminalServiceExercise19 summa = new TerminalServiceExercise19(first_number.getNumber() + second_number.getNumber());
        System.out.println(summa.getNumber());
        System.out.println(summa.howManyChetni());
        System.out.println(summa.tolkoNechetnie());
    }
}
