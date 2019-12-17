package by.training.task4.exercise14_mvc;

public class ControllerExercise14 {
    void execute() {
        ViewExercise14 viewExercise14 = new ConsoleViewExercise14();
        TerminalServiceExercise14 exercise14 = new TerminalServiceExercise14();
        viewExercise14.showTask();
        int first_number = exercise14.getTerminalDate("первого числа");
        int second_number = exercise14.getTerminalDate("второго числа");

        int amountNumberFirst = exercise14.howManyNumber(first_number);
        int amountNumberSecond = exercise14.howManyNumber(second_number);

        int max = exercise14.maxAmountNumbers(amountNumberFirst, amountNumberSecond);

        if (max == amountNumberFirst) {
            viewExercise14.showResult(first_number);
        } else if (max == amountNumberSecond) {
            viewExercise14.showResult(second_number);
        } else if (max == -1) {
            viewExercise14.showResult(-1);
        }
    }
}
