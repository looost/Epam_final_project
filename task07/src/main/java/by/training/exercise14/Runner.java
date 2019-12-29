package by.training.exercise14;

import by.training.View;

import java.util.Scanner;

/*

 Сформировать квадратную матрицу порядка n по заданному образцу(n - четное):
                                         {0 0 0 0 0 1}
                                         {0 0 0 0 2 0}
                                         {0 0 0 3 0 0}
                                         {...........}
                                         {0 n-1 0 0 0}
                                         {n 0 0 0 0 0}
 */


public class Runner {
    private static Scanner scanner = new Scanner(System.in);
    private static View view = new View();

    private static int checkValue() {
        while (!scanner.hasNextInt()) {
            view.showMessage("Введите целое число - ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    public static void main(String[] args) {
        Controller controller = new Controller();
        view.showMessage("Введите n - ");
        view.showMatrix(controller.execute(checkValue()));
    }
}
