package by.training.multithreading.entity;


import by.training.multithreading.entity.exception.MatrixException;

public class Matrix {
    private int[][] a;

    public Matrix(int n, int m) throws MatrixException {
        if ((n < 1) || (m < 1)) {
            throw new MatrixException("Значение строки или столбца не может быть меньше 1!");
        }
        a = new int[n][m];
    }

    public int getVerticalSize() {
        return a.length;
    }

    public int getHorizontalSize() {
        return a[0].length;
    }

    public int getElement(int i, int j) throws MatrixException {
        if (checkRange(i, j)) { // проверка i и j
            return a[i][j];
        } else {
            throw new MatrixException("Такого элемента не существует!");
        }
    }

    public void setElement(int i, int j, int value) throws MatrixException {
        if (checkRange(i, j)) { // проверка i и j
            a[i][j] = value;
        } else {
            throw new MatrixException("Такого элемента не существует!");
        }
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("\nMatrix : " + a.length + "x" + a[0].length + "\n");
        for (int[] row : a) {
            for (int value : row) {
                s.append(value + " ");
            }
            s.append("\n");
        }
        return s.toString();
    }

    private boolean checkRange(int i, int j) {
        if (i >= 0 && i < a.length && j >= 0 && j < a[0].length) {
            return true;
        } else {
            return false;
        }
    }
}
