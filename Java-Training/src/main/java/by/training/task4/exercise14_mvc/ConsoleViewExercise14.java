package by.training.task4.exercise14_mvc;

public class ConsoleViewExercise14 implements ViewExercise14 {
    @Override
    public void showTask() {
        System.out.println("");
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
