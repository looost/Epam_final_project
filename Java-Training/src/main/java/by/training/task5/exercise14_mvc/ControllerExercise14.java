package by.training.task5.exercise14_mvc;

public class ControllerExercise14 {
    void execute() {
        ViewExercise14 viewExercise14 = new ConsoleViewExercise14();
        TerminalServiceExercise14 exercise14 = new TerminalServiceExercise14();
        viewExercise14.showTask();
        int[] arr = exercise14.getArray();
        viewExercise14.showArray(arr);
        viewExercise14.showResult(exercise14.getResult(arr));
    }
}
