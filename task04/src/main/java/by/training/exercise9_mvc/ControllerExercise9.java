package by.training.exercise9_mvc;

public class ControllerExercise9 {
    void execute() {
        ViewExercise9 viewExercise9 = new ConsoleViewExercise9();
        ModelLayerExercise9 exercise9 = new TerminalDateExercise9();
        viewExercise9.showTask();
        int a = exercise9.getTerminalDate("a");
        int b = exercise9.getTerminalDate("b");
        int c = exercise9.getTerminalDate("c");
        viewExercise9.showResult(exercise9.getResult(a, b, c));
        //viewExercise9.showResult(exercise9.getNOD(a,b));
    }
}
