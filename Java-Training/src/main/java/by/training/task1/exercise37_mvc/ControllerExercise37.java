package by.training.task1.exercise37_mvc;


public class ControllerExercise37 {
    void execute() {
        ViewExercise37 viewExercise37 = new ConsoleViewExercise37();
        ModelLayerExercise37 exercise37 = new TerminalLayerExercise37();
        viewExercise37.showFullTask();

        viewExercise37.showTask1();
        boolean res1 = exercise37.getResultInTask1(exercise37.getTerminalDateInt("N"));
        viewExercise37.showResult(res1);
        viewExercise37.showTask2();
        boolean res2 = exercise37.getResultInTask2(exercise37.getTerminalDateInt("число"));

        viewExercise37.showResult(res2);
        viewExercise37.showTask3();
        boolean res3 = exercise37.getResultInTask3(exercise37.getTerminalDateInt("N"));
        viewExercise37.showResult(res3);
        viewExercise37.showTask4();
        boolean res4 = exercise37.getResultInTask4(exercise37.getTerminalDateDouble("x"), exercise37.getTerminalDateDouble("y"),
                exercise37.getTerminalDateDouble("t"), exercise37.getTerminalDateDouble("p"));
        viewExercise37.showResult(res4);
        viewExercise37.showTask5();
        boolean res5 = exercise37.getResultInTask5(exercise37.getTerminalDateInt("число"));
        viewExercise37.showResult(res5);
        viewExercise37.showTask6();
        boolean res6 = exercise37.getResultInTask6(exercise37.getTerminalDateDouble("a"), exercise37.getTerminalDateDouble("b"),
                exercise37.getTerminalDateDouble("c"));
        viewExercise37.showResult(res6);
        viewExercise37.showTask7();
        boolean res7 = exercise37.getResultInTask7(exercise37.getTerminalDateInt("N"));
        viewExercise37.showResult(res7);
        viewExercise37.showTask8();
        boolean res8 = exercise37.getResultInTask8(exercise37.getTerminalDateInt("n"), exercise37.getTerminalDateInt("a"));
        viewExercise37.showResult(res8);
        viewExercise37.showTask9();
        boolean res9 = exercise37.getResultInTask9(exercise37.getTerminalDateDouble("a"), exercise37.getTerminalDateDouble("b"),
                exercise37.getTerminalDateDouble("c"), exercise37.getTerminalDateDouble("m"),
                exercise37.getTerminalDateDouble("n"));
        viewExercise37.showResult(res9);
    }
}
