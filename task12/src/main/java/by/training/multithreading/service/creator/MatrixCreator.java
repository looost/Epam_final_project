package by.training.multithreading.service.creator;

import by.training.multithreading.entity.Matrix;
import by.training.multithreading.entity.exception.MatrixException;
import by.training.multithreading.service.exception.ServiceException;

public class MatrixCreator {
    public static Matrix createMatrix(String[] strings) throws ServiceException {
        try {
            int count = 0;
            Matrix matrix = new Matrix(10, 10);
            for (int i = 0; i < matrix.getVerticalSize(); i++) {
                for (int j = 0; j < matrix.getHorizontalSize(); j++) {
                    matrix.setElement(i, j, Integer.parseInt(strings[count++]));
                }
            }
            return matrix;
        } catch (MatrixException e) {
            throw new ServiceException("Cannot create matrix", e);
        }
    }
}
