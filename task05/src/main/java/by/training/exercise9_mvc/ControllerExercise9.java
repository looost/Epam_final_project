package by.training.exercise9_mvc;

public class ControllerExercise9 {
    void execute() {
        ViewExercise9 viewExercise9 = new ConsoleViewExercise9();
        TerminalServiceExercise9 exercise9 = new TerminalServiceExercise9();
        viewExercise9.showTask();
        int[] arr = exercise9.getArray();
        viewExercise9.showOriginArray(arr);
        viewExercise9.showResult(exercise9.getResult(arr));
    }
}
