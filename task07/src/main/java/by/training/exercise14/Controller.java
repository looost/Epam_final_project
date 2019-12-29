package by.training.exercise14;

public class Controller {
    private Service service = new Service();

    public int [][] execute(int n) {
        return service.createMatrixOrderN(n);
    }
}
