package by.training.exercise14_mvc;

public interface ViewExercise14 {
    default void showResult(double result, String mess) {
        System.out.println(mess + ": " + result);
    }

    void showTask();
}
