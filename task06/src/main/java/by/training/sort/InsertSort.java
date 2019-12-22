package by.training.sort;

import java.util.Arrays;
import java.util.Random;

public class InsertSort implements Sort {

    public static void main(String[] args) {
        InsertSort runner = new InsertSort();
        int[] arr = runner.getRandomArray();
        int[] arr1 = {2, 5, 3, 1, 5, 2, 12};
        System.out.println("Origin array: ");
        System.out.println(Arrays.toString(arr));
        System.out.println("Sort array: ");
        System.out.println(Arrays.toString(runner.sort(arr)));
    }

   @Override
    public int[] sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j - 1] > arr[j]) {
                    swap(arr, j, j - 1);
                }
            }
        }
        return arr;
    }
}
