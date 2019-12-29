package by.training.exercise14;


public class Service {

    public int [][] createMatrixOrderN(int n) {
        int [][] arr = new int[n][n];
        int count = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i+j != n-1) {
                    arr[i][j] = 0;
                } else {
                    arr[i][j] = count++;
                }
            }
        }
        return arr;
    }
}
