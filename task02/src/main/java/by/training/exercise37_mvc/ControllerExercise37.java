package by.training.exercise37_mvc;


public class ControllerExercise37 {
    void execute() {
        ViewExercise37 viewExercise37 = new ConsoleViewExercise37();
        ModelLayerExercise37 exercise37 = new TerminalLayerExercise37();
        viewExercise37.showTask();
        double x = exercise37.getTerminalDate("x");
        viewExercise37.showResult(x, exercise37.getResult(x));
    }
}
