package by.training.exercise9_mvc;

public class ConsoleViewExercise9 implements ViewExercise9 {
    @Override
    public void showTask() {
        System.out.println("Написать метод(методы), проверяющий, являются ли данные три числа взаимно простыми.\n");
    }

    @Override
    public void showResult(boolean result) {
        if (result) {
            System.out.println("Числа являются взаимно простыми");
        } else {
            System.out.println("Числа не являются взаимно простыми");
        }
    }
}
