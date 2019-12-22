package by.training.sort;

import java.util.Arrays;
import java.util.Random;

public class OddEvenSort implements Sort {

    @Override
    public int[] sort(int[] arr) {
        boolean flag = true;
        while (flag) {
            flag = false;
            for (int i = 0; i < arr.length-1; i+=2) {
                if (arr[i] > arr[i+1]) {
                    swap(arr, i, i+1);
                }
            }
            for (int i = 1; i < arr.length-1; i+=2) {
                if (arr[i] > arr[i+1]) {
                    swap(arr, i, i+1);
                    flag=true;
                }
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        OddEvenSort runner = new OddEvenSort();
        int[] arr = runner.getRandomArray();
        int[] arr1 = {2, 5, 1, 7};
        System.out.println("Origin array: ");
        System.out.println(Arrays.toString(arr));;
        System.out.println("Sorted array: ");
        System.out.println(Arrays.toString(runner.sort(arr)));
    }
}
