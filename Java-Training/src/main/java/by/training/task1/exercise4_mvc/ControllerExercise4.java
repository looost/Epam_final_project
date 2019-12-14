package by.training.task1.exercise4_mvc;


public class ControllerExercise4 {

    void execute() {
        ModelLayerExercise4 modelLayerExercise4 = new TerminalLayerExercise4();
        ViewExercise4 viewExercise4 = new ConsoleViewExercise4();
        viewExercise4.showTask();
        double a = modelLayerExercise4.getTerminalDate("a");
        double b = modelLayerExercise4.getTerminalDate("b");
        double c = modelLayerExercise4.getTerminalDate("c");
        viewExercise4.showResult(modelLayerExercise4.getResult(a, b, c));
    }
}
