package by.training.task2.lessonIfHome;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadDate {
    public static List<Integer> red() {
        Scanner scanner = new Scanner(System.in);
        List<Integer> list = new ArrayList<Integer>();
        System.out.print("Введите дату - ");
        int buff = scanner.nextInt();
        list.add(buff);
        System.out.print("Введите месяц - ");
        buff = scanner.nextInt();
        list.add(buff);
        System.out.print("Введите год - ");
        buff = scanner.nextInt();
        list.add(buff);
        scanner.close();
        return list;
    }
}