package by.training.allexercise;

import java.util.Random;

public class MatrixService {

    public static int [][] randomMatrix(int x, int y) {
        if (x<= 0 || y<=0) {
            throw new IllegalArgumentException("Кол-во строк или столбцов не может быть меньше либо равно нулю!");
        }
        Random random = new Random();
        int [][] arr = new int[x][y];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = random.nextInt(20);
            }
        }
        return arr;
    }

    public void firstAndLastLines(int [][] arr) {
        for (int i = 0; i < arr.length; i+=arr.length-1) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.printf("arr[%d][%d] = %-6d", i,j,arr[i][j]);
            }
            System.out.println();
        }
    }

    public int [][] createMatrixOrderN(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Размерность матрицы не может быть меньше либо равна нулю!");
        }
        int [][] arr = new int[n][n];
        int count = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i+j != n-1) {
                    arr[i][j] = 0;
                } else {
                    arr[i][j] = count++;
                }
            }
        }
        return arr;
    }

    public int [][] swapMatrixColumn(int [][] arr, int column1, int column2) {
        int tpm = 0;
        if (column1>column2) {
            tpm = column1;
            column1 = column2;
            column2 = tpm;
        }
        if (column1 > arr[0].length || column2> arr[0].length) {
            throw new IllegalArgumentException("Неверное значение столбца для обмена");
        }
        int jPosition = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (j == column1-1) {
                    jPosition = j;
                }
                if (j == column2-1) {
                    tpm = arr[i][j];
                    arr[i][j] = arr[i][jPosition];
                    arr[i][jPosition] = tpm;
                }
            }
        }
        return arr;
    }

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
