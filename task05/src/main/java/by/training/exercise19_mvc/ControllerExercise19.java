package by.training.exercise19_mvc;

public class ControllerExercise19 {
    void execute() {
        ConsoleViewExercise19 viewExercise19 = new ConsoleViewExercise19();
        TerminalServiceExercise19 exercise19 = new TerminalServiceExercise19();
        viewExercise19.showTask();
        int [] arr = exercise19.getArray();
        viewExercise19.showArray(arr);
        viewExercise19.showFrequent(exercise19.getResult(arr));
    }
}
