package by.training.exercise4_mvc;

public class ControllerExercise4 {
    void execute() {
        ViewExercise4 viewExercise4 = new ConsoleViewExercise4();
        ModelLayerExercise4 exercise4 = new TerminalLayerExercise4();
        viewExercise4.showTask();
        int a = exercise4.getTerminalInt("a");
        int b = exercise4.getTerminalInt("b");
        int c = exercise4.getTerminalInt("c");
        viewExercise4.showResult(exercise4.getResult(a, b, c));
    }
}
