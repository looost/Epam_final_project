package by.training.task2.exercise14_mvc;

public class ConsoleViewExercise14 implements ViewExercise14 {
    @Override
    public void showTask() {
        System.out.println("Даны два угла треугольника (в градусах). Определить, существует ли такой треугольник," +
                " и если да, то будет ли он прямоугольным.");
    }
}
