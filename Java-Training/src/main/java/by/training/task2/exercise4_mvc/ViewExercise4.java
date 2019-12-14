package by.training.task2.exercise4_mvc;

public interface ViewExercise4 {
    default void showResult(boolean result) {
        System.out.println(result);
    }

    void showTask();
}
