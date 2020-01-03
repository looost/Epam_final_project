package by.training.exercise37;

public class Controller {
    private Service service = new Service();
    public int [][] execute(int [][] arr) {
        return service.swapRandomMatrixStrings(arr);
    }
}
