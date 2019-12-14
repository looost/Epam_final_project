package by.training.task2.exercise4_mvc;

public interface ViewExercise4 {
    default void showResult(boolean result) {
        if (result) {
            System.out.println("Числа равны");
        } else {
            System.out.println("Числа не равны");
        }
    }

    void showTask();
}
