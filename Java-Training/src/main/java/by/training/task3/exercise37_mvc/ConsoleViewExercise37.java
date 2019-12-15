package by.training.task3.exercise37_mvc;

import java.util.ArrayList;

public class ConsoleViewExercise37 implements ViewExercise37 {
    @Override
    public void showTask() {
        System.out.println("Даны два двузначных числа А и В. Из этих чисел составили 2 четырехзначных числа: первое число\n" +
                "получили путем написания сначала числа А, затем В. Для получения второго числа сначала записали\n" +
                "число В, затем А. Найти числа А и В если известно , что первое четырехзначное число нацело делится\n" +
                "на 99, а второе на 49.");
    }

    @Override
    public void showResult(ArrayList<Integer> arr) {
        System.out.println("Число A - " + arr.get(0));
        System.out.println("Число B - " + arr.get(1));
    }
}
