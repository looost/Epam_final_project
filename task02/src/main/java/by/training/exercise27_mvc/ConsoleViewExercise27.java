package by.training.exercise27_mvc;

public class ConsoleViewExercise27 implements ViewExercise27 {
    @Override
    public void showResult(double result) {
        System.out.println("Результат: " + result);
    }

    @Override
    public void showTask() {
        System.out.println("Найти max{min(a,b), min(c,d)}");
    }
}
