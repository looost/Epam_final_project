package by.training.allexercise.command.iml;

import by.training.allexercise.command.Command;
import by.training.allexercise.creator.RandomMatrixCreator;
import by.training.allexercise.entity.Matrix;
import by.training.allexercise.exeption.MatrixException;
import by.training.allexercise.service.MatrixService;
import by.training.view.View;

import java.util.HashMap;
import java.util.Map;

public class Exercise27Command implements Command {
    @Override
    public void execute(String request) {
        try {
            View view = new View();
            String[] params = request.split(" ");
            Matrix matrix = new Matrix(Integer.parseInt(params[1]), Integer.parseInt(params[2]));
            RandomMatrixCreator.fillRandomValue(matrix);
            MatrixService matrixService = new MatrixService();
            view.showMessage("Оригинальная матрица: ");
            view.showMatrix(matrix);
            view.showMessage("Матрица, где столбцы " + params[3] + " и " + params[4] + " поменяли местами: ");
            view.showMatrix(matrixService.swapMatrixColumn(matrix, Integer.parseInt(params[3]), Integer.parseInt(params[4])));
        } catch (MatrixException e) {
        }
    }
}
