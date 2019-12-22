package by.training.sort;


import java.util.Arrays;
import java.util.Random;

public class SelectionSort implements Sort{
    public static void main(String[] args) {
        SelectionSort runner = new SelectionSort();
        int[] arr = runner.getRandomArray();
        System.out.println("Origin array: ");
        System.out.println(Arrays.toString(arr));
        System.out.println("Sort array: ");
        System.out.println(Arrays.toString(runner.sort(arr)));
    }

    @Override
    public int[] sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int indexMin = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[indexMin]) {
                    indexMin = j;
                }
            }
            int a = arr[i];
            arr[i] = arr[indexMin];
            arr[indexMin] = a;
        }
        return arr;
    }
}


