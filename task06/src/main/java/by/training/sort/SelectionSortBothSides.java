package by.training.sort;

import java.util.Arrays;
import java.util.Random;

public class SelectionSortBothSides implements Sort {
    public static void main(String[] args) {
        SelectionSortBothSides runner = new SelectionSortBothSides();
        int[] arr = runner.getRandomArray();
        System.out.println("Origin array: ");
        System.out.println(Arrays.toString(arr));
        System.out.println("Sort array: ");
        System.out.println(Arrays.toString(runner.sort(arr)));
    }

    @Override
    public int[] sort(int[] arr) {
        for (int i = 0; i < arr.length / 2; i++) {
            int indexMin = i;
            int indexMax = i;
            for (int j = i; j < arr.length - i; j++) {
                if (arr[j] > arr[indexMax]) {
                    indexMax = j;
                }

                if (arr[j] < arr[indexMin]) {
                    indexMin = j;
                }
            }
            if (i != indexMin) {
                swap(arr, i, indexMin);
            }
            if ((arr.length - i - 1) != indexMax) {
                if (i != indexMax) {
                    swap(arr, arr.length - i - 1, indexMax);
                } else {
                    swap(arr, arr.length - i - 1, indexMin);
                }
            }
        }
        return arr;
    }
}
