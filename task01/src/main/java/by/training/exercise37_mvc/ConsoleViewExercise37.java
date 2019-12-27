package by.training.exercise37_mvc;

public class ConsoleViewExercise37 implements ViewExercise37 {

    @Override
    public void showFullTask() {
        System.out.println("Составить линейную программу, печатающую значение true, если указанное высказывание является истинным, и\n" +
                "false — в противном случае:\n" +
                "• Целое число N является четным двузначным числом.\n" +
                "• Сумма двух первых цифр заданного четырехзначного числа равна сумме двух его последних цифр.\n" +
                "• Сумма цифр данного трехзначного числа N является четным числом.\n" +
                "• Точка с координатами (х, у) принадлежит части плоскости, лежащей между прямыми х= т, х= п (т<п).\n" +
                "• Квадрат заданного трехзначного числа равен кубу суммы цифр этого числа.\n" +
                "• Треугольник со сторонами а,b,с является равнобедренным.\n" +
                "• Сумма каких-либо двух цифр заданного трехзначного натурального числа N равна третьей цифре.\n" +
                "• Заданное число N является степенью числа а (показатель степени может находиться в диапазоне от 0 до 4).\n" +
                "• График функции у = ах2 + bх+ с проходит через заданную точку с координатами (m, п).");
    }

    @Override
    public void showTask1() {
        System.out.println("Целое число N является четным двузначным числом.");
    }

    @Override
    public void showTask2() {
        System.out.println("Сумма двух первых цифр заданного четырехзначного числа равна сумме двух его последних цифр.");
    }

    @Override
    public void showTask3() {
        System.out.println("Сумма цифр данного трехзначного числа N является четным числом.");
    }

    @Override
    public void showTask4() {
        System.out.println("Точка с координатами (х, у) принадлежит части плоскости, лежащей между прямыми х= т, х= п (т<п).");
    }

    @Override
    public void showTask5() {
        System.out.println("Квадрат заданного трехзначного числа равен кубу суммы цифр этого числа.");
    }

    @Override
    public void showTask6() {
        System.out.println("Треугольник со сторонами а,b,с является равнобедренным.");
    }

    @Override
    public void showTask7() {
        System.out.println("Сумма каких-либо двух цифр заданного трехзначного натурального числа N равна третьей цифре.");
    }

    @Override
    public void showTask8() {
        System.out.println("Заданное число N является степенью числа а (показатель степени может находиться в диапазоне от 0 до 4).");
    }

    @Override
    public void showTask9() {
        System.out.println("График функции у = ах2 + bх+ с проходит через заданную точку с координатами (m, п).");
    }
}