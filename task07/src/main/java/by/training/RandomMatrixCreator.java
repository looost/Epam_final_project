package by.training;


import java.util.Random;

public class RandomMatrixCreator {

    public static int [][] randomMatrix(int x, int y) {
        Random random = new Random();
        int [][] arr = new int[x][y];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = random.nextInt(20);
            }
        }
        return arr;
    }
}
