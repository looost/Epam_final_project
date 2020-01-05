package by.training.allexercise;

public class Controller {
    private MatrixService matrixService = new MatrixService();


    public void executeExercise4 (int [][] arr) {
        matrixService.firstAndLastLines(arr);
    }

    public int[][] executeExercise14(int n) {
        return matrixService.createMatrixOrderN(n);
    }

    public int[][] executeExercise27(int [][] arr, int column1, int column2) {
        return matrixService.swapMatrixColumn(arr,column1,column2);
    }

    public int[][] executeExercise37(int [][] arr) {
        return matrixService.swapRandomMatrixStrings(arr);
    }

}
