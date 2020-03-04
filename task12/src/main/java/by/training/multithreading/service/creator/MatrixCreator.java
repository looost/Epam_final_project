package by.training.multithreading.service.creator;

import by.training.multithreading.entity.Matrix;

public class MatrixCreator {

    private MatrixCreator() {
    }

    public static Matrix createMatrix(String[] strings, int matrixSize) {
        int count = 0;
        Matrix matrix = new Matrix(matrixSize, matrixSize);
        for (int i = 0; i < matrix.getVerticalSize(); i++) {
            for (int j = 0; j < matrix.getHorizontalSize(); j++) {
                matrix.setElement(i, j, Integer.parseInt(strings[count++]));
            }
        }
        return matrix;
    }
}
