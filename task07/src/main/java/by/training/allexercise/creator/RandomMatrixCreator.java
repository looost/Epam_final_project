package by.training.allexercise.creator;


import by.training.allexercise.entity.Matrix;
import by.training.allexercise.exeption.MatrixException;

import java.util.Random;

public class RandomMatrixCreator {

    public static int[][] randomMatrix(int x, int y) {
        Random random = new Random();
        int[][] arr = new int[x][y];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = random.nextInt(20);
            }
        }
        return arr;
    }

    public static void fillRandomValue(Matrix matrix) {
        Random random = new Random();
        for (int i = 0; i < matrix.getVerticalSize(); i++) {
            for (int j = 0; j < matrix.getHorizontalSize(); j++) {
                try {
                    matrix.setElement(i, j, random.nextInt(20));
                } catch (MatrixException e) {
                }
            }
        }
    }

}
