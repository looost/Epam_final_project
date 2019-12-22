package by.training;

import java.util.ArrayList;
import java.util.Random;

public class LessonExerciseSwap {

    public static void main(String[] args) {
        LessonExerciseSwap exerciseSwap = new LessonExerciseSwap();
        ArrayList<Integer> arrayList = exerciseSwap.createArray(11);
        System.out.println("Изначальный массив - " + arrayList);
        exerciseSwap.swapArray(arrayList);
        System.out.println("Перевернутый массив - " + arrayList);
    }

    private void swapArray(ArrayList<Integer> arr) {
        for (int i = 0; i < arr.size() / 2; i++) {
            int emp = arr.get(i);
            arr.set(i, arr.get(arr.size() - i - 1));
            arr.set(arr.size() - i - 1, emp);
        }
    }

    private ArrayList<Integer> createArray(int n) {
        Random random = new Random();
        ArrayList<Integer> arrayList = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            arrayList.add(i, random.nextInt(10));
        }
        return arrayList;
    }


}
