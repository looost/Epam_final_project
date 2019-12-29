package by.training.exercise27;

public class Contorller {
    private Service service = new Service();
    public int [][] execute(int [][] arr, int column1, int colum2) {
        return service.swapMatrixColumn(arr,column1,colum2);
    }
}
