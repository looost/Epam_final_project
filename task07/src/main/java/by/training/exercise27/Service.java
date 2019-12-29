package by.training.exercise27;

public class Service {

    public int [][] swapMatrixColumn(int [][] arr, int column1, int column2) {
        int tpm = 0;
        if (column1>column2) {
            tpm = column1;
            column1 = column2;
            column2 = tpm;
        }
        if (column1 > arr[0].length || column2> arr[0].length) {
            throw new IllegalArgumentException("Неверное значение столбца");
        }
        int jPosition = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (j == column1-1) {
                    jPosition = j;
                }
                if (j == column2-1) {
                    tpm = arr[i][j];
                    arr[i][j] = arr[i][jPosition];
                    arr[i][jPosition] = tpm;
                }
            }
        }
        return arr;
    }

}
