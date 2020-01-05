package by.training.exercise37;

import java.util.Random;

public class Service {


    public int [][] swapRandomMatrixStrings(int [][] arr) {
        Random random = new Random();
        int tpm = 0;
        int rand = 0;

        for (int i = 0; i < arr.length; i++) {
            rand = random.nextInt(arr.length);
            for (int j = 0; j < arr[i].length; j++) {
                tpm = arr[i][j];
                arr[i][j] = arr[rand][j];
                arr[rand][j] = tpm;
            }
        }
        return arr;
    }
}
