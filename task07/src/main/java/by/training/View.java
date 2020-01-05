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

    public void showMenu() {
        System.out.println("---------------------Меню---------------------");
        System.out.println("1 - Задача номер 4");
        System.out.println("2 - Задача номер 14");
        System.out.println("3 - Задача номер 27");
        System.out.println("4 - Задача номер 37");
        System.out.println("0 - Выход");
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
