package by.training.sort;

import java.util.Arrays;
import java.util.Random;

public class ShakerSort implements Sort {
    public static void main(String[] args) {
        ShakerSort shakerSort = new ShakerSort();
        int[] arr = shakerSort.getRandomArray();
        System.out.println("Original arr: ");
        System.out.println(Arrays.toString(arr));
        System.out.println("Sorted arr: ");
        System.out.println(Arrays.toString(shakerSort.sort(arr)));
    }
    @Override
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
}
