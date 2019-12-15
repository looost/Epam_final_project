package by.training.task3.exercise14_mvc;

public class ConsoleViewExercise14 implements ViewExercise14 {
    @Override
    public void showTask() {
        System.out.println("Дано натуральное n. вычислить: 1 + 1/2 + 1/3 + 1/4 + ... + 1/n");
    }

    @Override
    public void showResult(double value) {
        System.out.println("Результат: " + value);
    }
}
