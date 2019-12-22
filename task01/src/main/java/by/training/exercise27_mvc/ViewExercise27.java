package by.training.exercise27_mvc;

public interface ViewExercise27 {
    default void showResult(double result, String mess) {
        System.out.println(mess + ": " + result);
    }

    void showTask();
}
