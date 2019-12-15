package by.training.task3.exercise14_mvc;

public class ControllerExercise14 {
    void execute() {
        ViewExercise14 viewExercise14 = new ConsoleViewExercise14();
        ModelLayerExercise14 exercise14 = new TerminalLayerExercise14();
        viewExercise14.showTask();
        viewExercise14.showResult(exercise14.getResult(exercise14.getTerminalDate("n")));
    }
}
