package by.training.exercise4_mvc;

public class ControllerExercise4 {
    public void execute() {
        ViewEercise4 viewEercise4 = new ConsoleViewExercise4();
        ModelLayerExercise4 exercise4 = new TerminalLayerExercise4();
        viewEercise4.showTask();
        viewEercise4.showResult(exercise4.getResult());
    }
}
