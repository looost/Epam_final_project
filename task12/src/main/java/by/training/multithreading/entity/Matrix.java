package by.training.multithreading.entity;


public class Matrix {
    private Element[][] a;

    public Matrix(int n, int m) {
        a = new Element[n][m];
    }

    public int getVerticalSize() {
        return a.length;
    }

    public int getHorizontalSize() {
        return a[0].length;
    }

    public Element getElement(int i, int j) {
        return a[i][j];
    }

    public void setElement(int i, int j, int value) {
        a[i][j] = new Element(value);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("\nMatrix : " + a.length + "x" + a[0].length + "\n");
        for (Element[] row : a) {
            for (Element value : row) {
                s.append(value.getValue() + " ");
            }
            s.append("\n");
        }
        return s.toString();
    }
}
