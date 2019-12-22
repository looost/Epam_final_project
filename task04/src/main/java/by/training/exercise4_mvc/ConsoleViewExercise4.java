package by.training.exercise4_mvc;

public class ConsoleViewExercise4 implements ViewExercise4 {
    @Override
    public void showTask() {
        System.out.println("Написать метод(методы) для нахождения наименьшего общего кратного трех натуральных чисел.");
    }

    @Override
    public void showResult(int result) {
        System.out.println("Наименьшее общее кратное - " + result);
    }
}
