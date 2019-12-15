package by.training.task3.exercise27_mvc;

public class ControllerExercise27 {
    void execute() {
        ViewExercise27 viewExercise27 = new ConsoleViewExercise27();
        ModelLayerExercise27 exercise27 = new TerminalLayerExercise27();
        viewExercise27.showTask();
        viewExercise27.showResult(exercise27.getResult(exercise27.getTerminalDate("m"), exercise27.getTerminalDate("n")));
    }
}
