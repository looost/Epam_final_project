package by.training.task2.exercise4_mvc;

public class ConsoleViewExercise4 implements ViewExercise4 {
    @Override
    public void showTask() {
        System.out.println("Даны два угла треугольника (в градусах). Определить, существует ли такой треугольник," +
                " и если да, то будет ли он прямоугольным.");
    }

    public void triangleDoesNotExist() {
        System.out.println("Такого треугольника не существует");
    }

    public void triangleIsRectangular() {
        System.out.println("Треугольник прямоугольный");
    }

    public void triangleExistsNotRectangular() {
        System.out.println("Такого треугольника не существует");
    }
}
