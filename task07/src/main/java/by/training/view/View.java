package by.training.view;

import by.training.allexercise.entity.Matrix;
import by.training.allexercise.exeption.MatrixException;

public class View {
    public void showMatrix(Matrix matrix) throws MatrixException {
        for (int i = 0; i < matrix.getVerticalSize(); i++) {
            for (int j = 0; j < matrix.getHorizontalSize(); j++) {
                System.out.printf("arr[%d][%d] = %-6d", i, j, matrix.getElement(i, j));
            }
            System.out.println();
        }
    }

    public void showMessage(String message) {
        System.out.println(message);
    }


    public void task() {
        System.out.println("Доступные задания:");
        System.out.println("exercise4     exercise14     exercise27     exercise37");
        System.out.println("---> Для выбора задания введите его название в консоль");
    }


    public void showTaskExercise4() {
        System.out.println("Дана матрица. Вывести на экран первую и последнюю строки.");
    }

    public void showTaskExercise14() {
        System.out.println("Сформировать квадратную матрицу порядка n по заданному образцу(n - четное):\n" +
                "                                         {0 0 0 0 0 1}\n" +
                "                                         {0 0 0 0 2 0}\n" +
                "                                         {0 0 0 3 0 0}\n" +
                "                                         {...........}\n" +
                "                                         {0 n-1 0 0 0}\n" +
                "                                         {n 0 0 0 0 0}");
    }

    public void showTaskExercise27() {
        System.out.println("В числовой матрице поменять местами два столбца любых столбца, т. е. все элементы одного столбца поставить на\n" +
                "соответствующие им позиции другого, а его элементы второго переместить в первый. Номера столбцов вводит\n" +
                "пользователь с клавиатуры.");
    }

    public void showTaskExercise37() {
        System.out.println("Переставить строки матрицы случайным образом.");
    }
}
