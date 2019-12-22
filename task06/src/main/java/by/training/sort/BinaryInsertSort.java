package by.training.sort;

import java.util.Arrays;
import java.util.Random;

public class BinaryInsertSort implements Sort {

    public static void main(String[] args) {
        BinaryInsertSort runner = new BinaryInsertSort();
        int[] arr = runner.getRandomArray();
        int[] arr1 = {2, 5, 1, 7};
        System.out.println("Origin array: ");
        System.out.println(Arrays.toString(arr));
        System.out.println("Sort array: ");
        System.out.println(Arrays.toString(runner.sort2(arr)));
    }

    @Override
    public int[] sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int tmp = arr[i];
            int left = 0;
            int right = i - 1;
            int border = i;
            // ищем подходящую позицию для вставки элемента на i позицию
            while (left < right) {
                border = (left + right) / 2;
                if (arr[border] > tmp) {
                    right = border;
                } else {
                    left = border + 1;
                }
            }
            //сдвигаем все элементы (освобождаем места для элемента который будем вставлять)
            for (int j = i; j > border; j--) {
                arr[j] = arr[j - 1];
            }
            arr[border] = tmp;
        }
        return arr;
    }

    int[] sort2(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int val = arr[i];
            int left = 0;
            int right = i;
            // ищем подходящую позицию для вставки элемента на i позицию
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (val < arr[mid]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            for (int j = i; j > left; j--) {
                arr[j] = arr[j - 1];
            }
            arr[left] = val;
        }
        return arr;
    }

}
