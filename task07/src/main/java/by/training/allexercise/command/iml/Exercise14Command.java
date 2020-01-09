package by.training.allexercise.command.iml;

import by.training.allexercise.command.Command;
import by.training.allexercise.creator.RandomMatrixCreator;
import by.training.allexercise.entity.Matrix;
import by.training.allexercise.exeption.MatrixException;
import by.training.allexercise.service.MatrixService;
import by.training.view.View;

import java.util.HashMap;
import java.util.Map;

public class Exercise14Command implements Command {

    @Override
    public void execute(String request) {
        try {
            Map<String, Matrix> map = new HashMap<>();
            String[] params = request.split(" ");
            MatrixService matrixService = new MatrixService();
            View view = new View();
            view.showMessage("Матрица: ");
            view.showMatrix(matrixService.createMatrixOrderN(Integer.parseInt(params[1])));
        } catch (MatrixException e) {
        }
    }
}
