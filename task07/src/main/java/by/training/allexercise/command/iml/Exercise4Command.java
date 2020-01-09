package by.training.allexercise.command.iml;

import by.training.allexercise.command.Command;
import by.training.allexercise.creator.RandomMatrixCreator;
import by.training.allexercise.entity.Matrix;
import by.training.allexercise.exeption.MatrixException;
import by.training.allexercise.service.MatrixService;
import by.training.view.View;


import java.util.HashMap;
import java.util.Map;

public class Exercise4Command implements Command {
    // request = exercise4 5 2
    @Override
    public void execute(String request) {
        View view = new View();
        try {
            String[] params = request.split(" ");
            Matrix matrix = new Matrix(Integer.parseInt(params[1]), Integer.parseInt(params[2]));
            RandomMatrixCreator.fillRandomValue(matrix);
            MatrixService matrixService = new MatrixService();
            view.showMessage("Оригинальная матрица: ");
            view.showMatrix(matrix);
            view.showMessage("Первая и последняя строки матрицы: ");
            view.showMatrix(matrixService.firstAndLastLines(matrix));
        } catch (MatrixException e) {
            view.showMessage(e.getMessage());
        } catch (NumberFormatException e) {
            view.showMessage("Значения введены неверно!");
        }
    }
}
