package by.training.exercise19_mvc;

public class ConsoleViewExercise19 implements ViewExercise19 {
    @Override
    public void showTask() {
        System.out.println(" Написать программу, определяющую сумму n - значных чисел, содержащих только нечетные цифры. Определить\n" +
                "также, сколько четных цифр в найденной сумме. Для решения задачи использовать декомпозицию.");
    }

    @Override
    public void showResult(boolean arrFirstNumbers, boolean arrSecondNumbers, int howManyEven) {
        System.out.println("Are only even digits in first number: " + arrFirstNumbers);
        System.out.println("Are only even digits in second number: " + arrSecondNumbers);
        System.out.println("Count of even digits in sum: " + howManyEven);
    }
}
