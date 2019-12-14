package by.training.task1.exercise27_pack;

public interface ViewExercise27 {
    default void showResult(double result, String mess) {
        System.out.println(mess + ": " + result);
    }

    void showTask();
}
