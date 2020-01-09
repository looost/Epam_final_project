package by.training.allexercise.command.iml;

import by.training.allexercise.command.Command;
import by.training.allexercise.creator.RandomMatrixCreator;
import by.training.allexercise.entity.Matrix;
import by.training.allexercise.exeption.MatrixException;
import by.training.allexercise.service.MatrixService;
import by.training.view.View;

import java.util.HashMap;
import java.util.Map;

public class Exercise37Command implements Command {

    @Override
    public void execute(String request) {
        try {
            View view = new View();
            String[] params = request.split(" ");
            MatrixService matrixService = new MatrixService();
            Matrix matrix = new Matrix(Integer.parseInt(params[1]), Integer.parseInt(params[2]));
            RandomMatrixCreator.fillRandomValue(matrix);

            view.showMessage("Оригинальная матрица: ");
            view.showMatrix(matrix);
            view.showMessage("Матрица, где строки поменяли случайным образом местами: ");
            view.showMatrix(matrixService.swapRandomMatrixStrings(matrix));
        } catch (MatrixException e) {
        }
    }
}
