package by.training.task4.exercise19_mvc;

import java.util.ArrayList;

public class TerminalServiceExercise19 {
//    private int number;
//    private int n;
//    private ArrayList<Integer> arr;

//    TerminalServiceExercise19(int number) {
//        this.number = number;
//        this.n = howManyNumber(this.number);
//        this.arr = numberArray();
//    }

//    private int getNumber() {
//        return this.number;
//    }

    private ArrayList<Integer> numberArray(int number, int length) {
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = length; length != 0; length--) {
            arr.add((number / (int) Math.pow(10, length - 1)) % 10);
        }
        return arr;
    }

    private boolean tolkoNechetnie(ArrayList<Integer> arr) {
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

    private int howManyChetni(ArrayList<Integer> arr) {
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

    private int getSum(int f, int s) {
        return f + s;
    }


    public static void main(String[] args) {
        TerminalServiceExercise19 numbersService = new TerminalServiceExercise19();

        int firstNumber = 5313;
        int secondNumber = 3235;
        System.out.println("first: " + firstNumber + ", second: " + secondNumber);

        int lengthFirst = numbersService.howManyNumber(firstNumber);
        int lengthSecond = numbersService.howManyNumber(secondNumber);

        ArrayList<Integer> arrFirstNumbers = numbersService.numberArray(firstNumber, lengthFirst);
        ArrayList<Integer> arrSecondNumbers = numbersService.numberArray(secondNumber, lengthSecond);

        System.out.println("Are only even numbers: " + numbersService.tolkoNechetnie(arrFirstNumbers));
        System.out.println("Are only even numbers: " + numbersService.tolkoNechetnie(arrSecondNumbers));

        int sum = numbersService.getSum(firstNumber, secondNumber);

        System.out.println("Sum: " + sum);

        System.out.println("Count of even digits: " + numbersService.howManyChetni(numbersService.numberArray(sum, numbersService.howManyNumber(sum))));

//        System.out.println(first_number.tolkoNechetnie());
//        System.out.println(second_number.tolkoNechetnie());
//        TerminalServiceExercise19 summa = new TerminalServiceExercise19(first_number.getNumber() + second_number.getNumber());
//        System.out.println(summa.getNumber());
//        System.out.println(summa.howManyChetni());
//        System.out.println(summa.tolkoNechetnie());
    }
}
