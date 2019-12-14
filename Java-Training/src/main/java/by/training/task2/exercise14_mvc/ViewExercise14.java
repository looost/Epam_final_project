package by.training.task2.exercise14_mvc;

public interface ViewExercise14 {
    default void showResult(String result) {
        System.out.println(result);
    }

    void showTask();
}
