package by.training.exercise37_mvc;

public class ConsoleViewExercise37 implements ViewExercise37 {

    @Override
    public void showTask() {
        System.out.println("Вычеслить значение функции:\n" +
                "    F(x) = -x^2 + 3x + 9 , если x>=3;\n" +
                "    F(x) = 1/(x^3 - 6) , если x<3;");
    }
}
