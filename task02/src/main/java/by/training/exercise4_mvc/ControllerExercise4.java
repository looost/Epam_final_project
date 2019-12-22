package by.training.exercise4_mvc;


public class ControllerExercise4 {

    void execute() {
        ViewExercise4 viewExercise4 = new ConsoleViewExercise4();
        ModelLayerExercise4 exercise4 = new TerminalLayerExercise4();
        viewExercise4.showTask();
        double a = exercise4.getTerminalDate("a");
        double b = exercise4.getTerminalDate("b");
        viewExercise4.showResult(exercise4.getResult(a, b));

    }
}
