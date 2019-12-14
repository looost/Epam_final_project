package by.training.task1.exercise4_pack;

public interface ViewExercise4 {
    default void showResult(double result) {
        System.out.println("Результат: " + result);
    }

    void showTask();
}
