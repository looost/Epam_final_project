package by.training.task6.selectionsort;

import java.util.Random;

public class BubbleSort {
    public static void main(String[] args) {
        BubbleSort bubbleSort = new BubbleSort();
        int[] arr = bubbleSort.getRandomArray();
        System.out.println("Original arr: ");
        bubbleSort.viewArray(arr);
        System.out.println();
        System.out.println("Bubble arr: ");
        bubbleSort.viewArray(bubbleSort.sort(arr));
        System.out.println();
        System.out.println("Bubble arr2: ");
        bubbleSort.viewArray(bubbleSort.sort2(arr));
    }

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
