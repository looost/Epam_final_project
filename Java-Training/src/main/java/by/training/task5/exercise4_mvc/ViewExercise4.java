package by.training.task5.exercise4_mvc;

import by.training.view.ViewLayer;

public class ViewExercise4 implements ViewLayer {
    @Override
    public void showTask() {
        System.out.println("Дана последовательность действительных чисел а1 а2 ,..., аn . Выяснить, будет ли она возрастающей.");
    }

    @Override
    public void showResult(boolean result) {
        if (result) {
            System.out.println("Последовательность возрастающая");
        } else {
            System.out.println("Последовательность не является возрастающей");
        }
    }
}
