package by.training.sort;

import java.util.Arrays;
import java.util.Random;

public class BubbleSort implements Sort {
    public static void main(String[] args) {
        BubbleSort bubbleSort = new BubbleSort();
        int[] arr = bubbleSort.getRandomArray();
        System.out.println("Original arr: ");
        System.out.println(Arrays.toString(arr));
        System.out.println("Bubble arr: ");
        System.out.println(Arrays.toString(bubbleSort.sort(arr)));
        System.out.println("Bubble arr2: ");
        System.out.println(Arrays.toString(bubbleSort.sort2(arr)));
    }

    @Override
    public int[] sort(int[] arr) {
        boolean flag = true;
        while (flag) {
            flag = false;
            for (int i = 0; i < arr.length - 1; i++) {
                if (arr[i] > arr[i + 1]) {
                    int emp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = emp;
                    flag = true;
                }
            }
        }
        return arr;
    }

    public int[] sort2(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[i] > arr[i + 1]) {
                    int emp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = emp;
                }
            }
        }
        return arr;
    }
}
