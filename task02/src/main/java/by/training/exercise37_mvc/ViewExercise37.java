package by.training.exercise37_mvc;

public interface ViewExercise37 {
    default void showResult(double value, double result) {
        System.out.println("F(" + value + ") = " + result);
    }

    void showTask();

}