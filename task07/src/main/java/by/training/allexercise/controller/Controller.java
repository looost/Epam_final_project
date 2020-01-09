package by.training.allexercise.controller;

import by.training.view.View;
import by.training.allexercise.entity.Matrix;
import by.training.allexercise.exeption.MatrixException;
import by.training.allexercise.service.MatrixService;

public class Controller {
    private MatrixService matrixService = new MatrixService();
    private View view = new View();

    public void executeExercise4(Matrix matrix) {
        try {
            view.showMessage("Оригинальная матрица: ");
            view.showMatrix(matrix);
            view.showMessage("Первая и последня строка матрицы: ");
            view.showMatrix(matrixService.firstAndLastLines(matrix));
        } catch (MatrixException e) {
        }
    }

    public void executeExercise14(int n) {
        try {
            view.showMatrix(matrixService.createMatrixOrderN(n));
        } catch (MatrixException e) {
            view.showMessage("Неверные значения!");
        }

    }

    public void executeExercise27(Matrix matrix, int column1, int column2) {
        try {
            view.showMessage("Оригинальная матрица ");
            view.showMatrix(matrix);
            view.showMessage("Матрица, где столбцы " + column1 + " и " + column2 + " поменяли местами - ");
            view.showMatrix(matrixService.swapMatrixColumn(matrix, column1, column2));
        } catch (MatrixException e) {
            view.showMessage(e.getMessage());
        }

    }

    public void executeExercise37(Matrix matrix) {
        try {
            view.showMessage("Оригинальная матрица ");
            view.showMatrix(matrix);
            view.showMessage("Матрица, где строки случайным образом поменяли местами");
            view.showMatrix(matrixService.swapRandomMatrixStrings(matrix));
        } catch (MatrixException e) {
        }
    }

}
