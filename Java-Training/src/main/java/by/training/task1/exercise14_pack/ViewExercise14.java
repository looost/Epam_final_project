package by.training.task1.exercise14_pack;

public interface ViewExercise14 {
    default void showResult(double result, String mess) {
        System.out.println(mess + ": " + result);
    }

    void showTask();
}
