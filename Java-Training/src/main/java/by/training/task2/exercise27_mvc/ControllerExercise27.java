package by.training.task2.exercise27_mvc;

public class ControllerExercise27 {
    void execute() {
        ModelLayerExercise27 exercise27 = new TerminalLayerExercise27();
        ViewExercise27 viewExercise27 = new ConsoleViewExercise27();
        viewExercise27.showTask();
        double a = exercise27.getTerminalDate("a");
        double b = exercise27.getTerminalDate("b");
        double c = exercise27.getTerminalDate("c");
        double d = exercise27.getTerminalDate("d");
        viewExercise27.showResult(exercise27.getResult(a, b, c, d));
    }
}
