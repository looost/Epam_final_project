package by.training.exercise4;

import by.training.RandomMatrixCreator;
import by.training.View;

/*
        Дана матрица. Вывести на экран первую и последнюю строки.
 */

public class Service {
    private static View view = new View();

    public static void firstAndLastLines(int [][] arr) {
        for (int i = 0; i < arr.length; i+=arr.length-1) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.printf("arr[%d][%d] = %-6d", i,j,arr[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int [][] arr = RandomMatrixCreator.randomMatrix(4,3);
        System.out.println("Изначальная матрица:");
        view.showMatrix(arr);
        System.out.println("Первая и поледняя строки матрицы:");
        firstAndLastLines(arr);
    }
}
