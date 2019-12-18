package by.training.task6.selectionsort;

import java.util.Arrays;
import java.util.Random;

public class SelectionSortBothSides {
    public static void main(String[] args) {
        SelectionSortBothSides runner = new SelectionSortBothSides();
        int[] arr = runner.getRandomArray();
        int[] arr1 = {2, 5, 3, 1, 5, 2, 12};
        System.out.println("Origin array: ");
        runner.viewArray(arr);
        System.out.println();
        System.out.println("Sort array: ");
        runner.viewArray(runner.sort(arr));
    }

    public int[] sort(int[] arr) {

        int halSize = arr.length / 2;
        for (int i = 0; i < halSize; i++) {
            int indexMin = 0;
            int indexMax = 0;
            for (int j = i + 1; j < arr.length - i; j++) {
                if (arr[i] > arr[indexMax]) {
                    indexMax = i;
                }
                if (arr[i] < arr[indexMin]) {
                    indexMin = i;
                }
                int empMin = arr[indexMin];
                arr[indexMin] = arr[indexMax];
                arr[indexMax] = empMin;
            }

        }
        return arr;
    }

    int[] getRandomArray() {
        Random random = new Random();
        int[] arr = new int[9];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100);
        }
        return arr;
    }

    void viewArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
