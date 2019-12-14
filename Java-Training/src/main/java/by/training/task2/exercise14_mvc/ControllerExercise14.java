package by.training.task2.exercise14_mvc;

public class ControllerExercise14 {
    void execute() {
        ViewExercise14 viewExercise14 = new ConsoleViewExercise14();
        ModelLayerExercise14 exercise14 = new TerminalLayerExercise14();
        viewExercise14.showTask();
        double a = exercise14.getTerminalDate("a");
        double b = exercise14.getTerminalDate("b");
        viewExercise14.showResult(exercise14.getResult(a, b));
    }
}
