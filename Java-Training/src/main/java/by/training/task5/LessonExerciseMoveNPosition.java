package by.training.task5;

import java.util.ArrayList;
import java.util.Random;

public class LessonExerciseMoveNPosition {
    public static void main(String[] args) {
        LessonExerciseMoveNPosition exerciseMoveNPosition = new LessonExerciseMoveNPosition();
        ArrayList<Integer> arrayList = exerciseMoveNPosition.createArray(10);
        System.out.println("Изначальный массив - " + arrayList);
        exerciseMoveNPosition.moveNPositionLeft(arrayList, 3);
        System.out.println("Массив сдвинутый влево на 3 позиции - " + arrayList);
        exerciseMoveNPosition.moveNPositionRight(arrayList, 5);
        System.out.println("Массив сдвинутый вправо на 5 позиции - " + arrayList);
    }

    private ArrayList<Integer> createArray(int n) {
        Random random = new Random();
        ArrayList<Integer> arrayList = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            arrayList.add(i, random.nextInt(10));
        }
        return arrayList;
    }

    void moveOnePositionRight(ArrayList<Integer> arrayList) {
        int emp = arrayList.get(arrayList.size() - 1);
        for (int i = arrayList.size() - 1; i > 0; i--) {
            arrayList.set(i, arrayList.get(i - 1));
        }
        arrayList.set(0, emp);
    }

    void moveOnePositionLeft(ArrayList<Integer> arrayList) {
        int emp = arrayList.get(0);
        for (int i = 0; i < arrayList.size() - 1; i++) {
            arrayList.set(i, arrayList.get(i + 1));
        }
        arrayList.set(arrayList.size() - 1, emp);
    }

    void moveNPositionRight(ArrayList<Integer> arrayList, int n) {
        for (int i = 1; i <= n; i++) {
            moveOnePositionRight(arrayList);
        }
    }

    void moveNPositionLeft(ArrayList<Integer> arrayList, int n) {
        for (int i = 1; i <= n; i++) {
            moveOnePositionLeft(arrayList);
        }
    }
}
