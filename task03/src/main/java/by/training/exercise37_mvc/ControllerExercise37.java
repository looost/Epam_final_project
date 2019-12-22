package by.training.exercise37_mvc;

public class ControllerExercise37 {
    void execute() {
        ViewExercise37 viewExercise37 = new ConsoleViewExercise37();
        ModelLayerExercise37 exercise37 = new TerminalLayerExercise37();
        viewExercise37.showTask();
        viewExercise37.showResult(exercise37.getResult());
    }
}
