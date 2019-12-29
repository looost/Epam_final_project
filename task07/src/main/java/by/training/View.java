package by.training;

public class View {
    public void showMatrix(int [][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.printf("arr[%d][%d] = %-6d", i,j,arr[i][j]);
            }
            System.out.println();
        }
    }
    public void showMessage(String message) {
        System.out.println(message);
    }
}
