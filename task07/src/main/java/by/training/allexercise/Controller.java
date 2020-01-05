package by.training.allexercise;

import by.training.View;

public class Controller {
    private MatrixService matrixService = new MatrixService();
    private View view = new View();

    public void executeExercise4 (int [][] arr) {
        view.showMessage("Оригинальная матрица: ");
        view.showMatrix(arr);
        view.showMessage("Первая и последня строка матрицы: ");
        matrixService.firstAndLastLines(arr);
    }

    public void executeExercise14(int n) {
        view.showMatrix(matrixService.createMatrixOrderN(n));
    }

    public void executeExercise27(int [][] arr, int column1, int column2) {
        view.showMessage("Оригинальная матрица ");
        view.showMatrix(arr);
        view.showMessage("Матрица, где столбцы " + column1 + " и " + column2 + " поменяли местами - ");
        view.showMatrix(matrixService.swapMatrixColumn(arr,column1,column2));
    }

    public void executeExercise37(int [][] arr) {
        view.showMessage("Оригинальная матрица ");
        view.showMatrix(arr);
        view.showMessage("Матрица, где строки случайным образом поменяли местами");
        view.showMatrix(matrixService.swapRandomMatrixStrings(arr));
    }

}
