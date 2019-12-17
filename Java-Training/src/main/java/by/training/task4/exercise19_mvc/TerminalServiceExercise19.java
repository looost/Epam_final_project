package by.training.task4.exercise19_mvc;

import java.util.ArrayList;

public class TerminalServiceExercise19 {

    TerminalServiceExercise19(int number) {
        this.number = number;
    }

    private int number;
    // private int n = howManyNumber();

    public int getNumber() {
        return number;
    }

    ArrayList<Integer> numberArray() {
        int n = howManyNumber(number);
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = n; n != 0; n--) {
            arr.add((number / (int) Math.pow(10, n - 1)) % 10);
        }
        return arr;
    }

    boolean tolkoNechetnie() {
        ArrayList<Integer> arr = numberArray();
        System.out.println(arr);
        for (Integer num : arr
        ) {
            if (num % 2 == 0) {
                return false;
            }
        }
        return true;
    }

    public int howManyNumber(int number) {
        int count = 0;
        while (number % 10 != 0) {
            number /= 10;
            count++;
        }
        return count;
    }

    public int howManyChetni() {
        ArrayList<Integer> arr = numberArray();
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
        TerminalServiceExercise19 first_number = new TerminalServiceExercise19(1323);
        TerminalServiceExercise19 second_number = new TerminalServiceExercise19(7595);
        System.out.println(first_number.tolkoNechetnie());
        System.out.println(second_number.tolkoNechetnie());
        TerminalServiceExercise19 summa = new TerminalServiceExercise19(first_number.getNumber() + second_number.getNumber());
        System.out.println(summa.getNumber());
        System.out.println(summa.howManyChetni());
    }
}
