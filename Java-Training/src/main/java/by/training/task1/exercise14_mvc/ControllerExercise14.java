package by.training.task1.exercise14_mvc;

public class ControllerExercise14 {
    void execute() {
        ModelLayerExercise14 exercise14 = new TerminalLayerExercise14();
        ViewExercise14 viewExercise14 = new ConsoleViewExercise14();
        viewExercise14.showTask();
        double r = exercise14.getTerminalDate("R");
        viewExercise14.showResult(exercise14.getResultArea(r), "Площадь");
        viewExercise14.showResult(exercise14.getResultLength(r), "Радиус");
    }
}
