package by.training.task6.selectionsort;


import java.util.Random;

public class Runner {
    public static void main(String[] args) {
        Runner runner = new Runner();
        int[] arr = runner.getRandomArray();
        System.out.println("Origin array: ");
        runner.viewArray(arr);
        System.out.println();
        System.out.println("Sort array: ");
        runner.viewArray(runner.sort(arr));
    }

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

    int[] getRandomArray() {
        Random random = new Random();
        int[] arr = new int[10];
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


