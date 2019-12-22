package by.training.exercise14_mvc;

public class ConsoleViewExercise14 implements ViewExercise14 {
    @Override
    public void showTask() {
        System.out.println("Написать метод(методы), определяющий, в каком из данных двух чисел больше цифр");
    }

    @Override
    public void showResult(int a) {
        if (a == -1) {
            System.out.println("У чисел одинаковое количество цифр");
        } else {
            System.out.println("В числе " + a + " больше цифр");
        }
    }
}
