package by.training.allexercise.service;

import by.training.allexercise.entity.Matrix;
import by.training.allexercise.exeption.MatrixException;

import java.util.Random;

public class MatrixService {

    public Matrix firstAndLastLines(Matrix matrix) throws MatrixException {
        Matrix newMatrix = new Matrix(2, matrix.getHorizontalSize());
        int count = 0;
        for (int i = 0; i < matrix.getVerticalSize(); i += matrix.getVerticalSize() - 1) {
            for (int j = 0; j < matrix.getHorizontalSize(); j++) {
                    newMatrix.setElement(count, j, matrix.getElement(i, j));
            }
            count = 1;
        }
        return newMatrix;
    }

    public Matrix createMatrixOrderN(int n) throws MatrixException {
        Matrix matrix = new Matrix(n, n);
        int count = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i + j != n - 1) {
                    matrix.setElement(i, j, 0);
                } else {
                    matrix.setElement(i, j, count++);
                }
            }
        }
        return matrix;
    }

    public Matrix swapMatrixColumn(Matrix matrix, int column1, int column2) throws MatrixException {
        int tpm = 0;
        int jPosition = 0;
        if (column1 > column2) {
            tpm = column1;
            column1 = column2;
            column2 = tpm;
        }
        if (column1 > matrix.getHorizontalSize() || column2 > matrix.getHorizontalSize()) {
            throw new MatrixException("Неверное значение строки или столбца");
        }
        for (int i = 0; i < matrix.getVerticalSize(); i++) {
            for (int j = 0; j < matrix.getHorizontalSize(); j++) {
                if (j == column1 - 1) {
                    jPosition = j;
                }
                if (j == column2 - 1) {
                        tpm = matrix.getElement(i, j);
                        matrix.setElement(i, j, matrix.getElement(i, jPosition));
                        matrix.setElement(i, jPosition, tpm);
                }
            }
        }
        return matrix;
    }

    public Matrix swapRandomMatrixStrings(Matrix matrix) throws MatrixException {
        Random random = new Random();
        int tpm = 0;
        int rand = 0;

        for (int i = 0; i < matrix.getVerticalSize(); i++) {
            rand = random.nextInt(matrix.getVerticalSize());
            for (int j = 0; j < matrix.getHorizontalSize(); j++) {
                    tpm = matrix.getElement(i, j);
                    matrix.setElement(i, j, matrix.getElement(rand, j));
                    matrix.setElement(rand, j, tpm);
            }
        }
        return matrix;
    }
}
