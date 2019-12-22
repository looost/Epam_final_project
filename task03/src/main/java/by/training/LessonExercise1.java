package by.training;

import java.util.ArrayList;

public class LessonExercise1 {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(i + 1);
        }
        int sum = 0;
        for (Integer i : list
        ) {
            sum += i;
        }
        System.out.println(sum);
        int s = sum(list, list.size() - 1);
        System.out.println(s);
    }

    private static int sum(ArrayList<Integer> numbers, int counter) {
        if (counter == 0) {
            return numbers.get(counter);
        } else {
            return sum(numbers, counter - 1) + numbers.get(counter);
        }

    }
}
