package by.training.exercise19_mvc;

import java.util.ArrayList;

public class ControllerExercise19 {

    void execute() {
        ViewExercise19 viewExercise19 = new ConsoleViewExercise19();
        TerminalServiceExercise19 exercise19 = new TerminalServiceExercise19();
        viewExercise19.showTask();

        TerminalServiceExercise19 numbersService = new TerminalServiceExercise19();
        int firstNumber = exercise19.getTerminalDate("first number");
        int secondNumber = exercise19.getTerminalDate("second number");
        int lengthFirst = numbersService.howManyNumber(firstNumber);
        int lengthSecond = numbersService.howManyNumber(secondNumber);
        System.out.println("first: " + firstNumber + ", second: " + secondNumber);
        ArrayList<Integer> arrFirstNumbers = numbersService.numberArray(firstNumber, lengthFirst);
        ArrayList<Integer> arrSecondNumbers = numbersService.numberArray(secondNumber, lengthSecond);

        int sum = numbersService.getSum(firstNumber, secondNumber);
        System.out.println("Sum: " + sum);
        viewExercise19.showResult(numbersService.justEven(arrFirstNumbers), numbersService.justEven(arrSecondNumbers),
                numbersService.howManyEven(numbersService.numberArray(sum, numbersService.howManyNumber(sum))));


    }
}
