package by.training.multithreadingv2.service.creator;


import by.training.multithreadingv2.entity.Element;
import by.training.multithreadingv2.entity.Matrix;

public class MatrixCreator {

    private MatrixCreator() {
    }

    public static Matrix createMatrix(String[] strings) {
        int count = 0;
        Matrix matrix = new Matrix(10, 10);
        Element element;
        for (int i = 0; i < matrix.getVerticalSize(); i++) {
            for (int j = 0; j < matrix.getHorizontalSize(); j++) {
                matrix.setElement(i, j, Integer.parseInt(strings[count++]));
            }
        }
        return matrix;
    }
}
