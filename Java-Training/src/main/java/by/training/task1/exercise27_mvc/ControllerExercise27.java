package by.training.task1.exercise27_mvc;

public class ControllerExercise27 {
    void execute() {
        ModelLayerExercise27 exercise27 = new TerminalLayerExercise27();
        ViewExercise27 viewExercise27 = new ConsoleViewExercise27();
        viewExercise27.showTask();
        double value = exercise27.getTerminalDate("a");
        viewExercise27.showResult(exercise27.getResultIn3Operation(value), "Результат за 3 операции");
        viewExercise27.showResult(exercise27.getResultIn4Operation(value), "Результат за 3 операции");
    }
}
