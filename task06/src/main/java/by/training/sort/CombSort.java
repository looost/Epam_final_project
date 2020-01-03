package by.training.sort;

import java.util.Arrays;

public class CombSort implements Sort{
    @Override
    public int[] sort(int[] arr) {
        int gap = arr.length;
        boolean flag = true;
        while (gap > 1 || flag) {
            gap = (int) (gap/1.25);
            flag = false;
            for (int i = 0; i < arr.length-gap; i++) {
                if (arr[i] > arr[i+gap]) {
                    swap(arr, i, i+gap);
                    flag = true;
                }
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        CombSort combSort = new CombSort();
        int [] arr = combSort.getRandomArray();
        System.out.println("Original arr: ");
        System.out.println(Arrays.toString(arr));
        System.out.println("Bubble arr: ");
        System.out.println(Arrays.toString(combSort.sort(arr)));
    }
}
