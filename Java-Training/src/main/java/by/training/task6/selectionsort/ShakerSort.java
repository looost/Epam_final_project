package by.training.task6.selectionsort;

import java.util.Random;

public class ShakerSort {
    public static void main(String[] args) {
        ShakerSort shakerSort = new ShakerSort();
        int[] arr = shakerSort.getRandomArray();
        System.out.println("Original arr: ");
        shakerSort.viewArray(arr);
        System.out.println();
        System.out.println("Sorted arr: ");
        shakerSort.viewArray(shakerSort.sort(arr));
    }

    public int[] sort(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        int k = 0; // индекс последнего обмена
        while (left < right) {
            for (int j = left; j < right; j++) {
                if (arr[j] > arr[j + 1]) {
                    int emp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = emp;
                    k = j;
                }
            }
            right = k;
            for (int j = right - 1; j >= left; j--) {
                if (arr[j] > arr[j + 1]) {
                    int emp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = emp;
                    k = j;
                }
            }
            left = k + 1;
        }
        return arr;
    }


    int[] getRandomArray() {
        Random random = new Random();
        int[] arr = new int[7];
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
