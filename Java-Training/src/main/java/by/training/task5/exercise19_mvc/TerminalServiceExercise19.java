package by.training.task5.exercise19_mvc;

public class TerminalServiceExercise19 {
    public static void main(String[] args) {
        int[] arr = {3, 6, 1, 6, 3, 7, 6, 3, 3, 6};
        System.out.println(getResult(arr));
    }

    static int getResult(int[] arr) {
        int countMax = 1;
        int maxCount = arr[0];
        for (int i = 0; i < arr.length; i++) {
            int countCurrent = 1;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] == arr[j]) {
                    countCurrent++;
                    if (countCurrent > countMax) {
                        countMax = countCurrent;
                        maxCount = arr[i];
                    } else if (countCurrent == countMax && arr[i] < maxCount) {
                        maxCount = arr[i];
                    }
                }
            }
        }
        return maxCount;
    }

}
