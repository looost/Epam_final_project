package by.training.sort;

import java.util.Random;

public interface Sort {
    default int[] getRandomArray() {
        Random random = new Random();
        int[] arr = new int[8];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100);
        }
        return arr;
    }

    default void swap(int[] arr, int i, int j) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

    int [] sort(int [] arr);
}
