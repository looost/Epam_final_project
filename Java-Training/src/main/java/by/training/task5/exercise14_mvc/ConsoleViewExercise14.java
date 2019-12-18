package by.training.task5.exercise14_mvc;

public class ConsoleViewExercise14 implements ViewExercise14 {
    @Override
    public void showTask() {
        System.out.println("Дан одномерный массив A[N]. Найти:\n" +
                "max(a2, a4, a6, a2n) + min( a1, a3, a5, an+1) ");
    }

    @Override
    public void showArray(int[] arr) {
        System.out.print("Array - [");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("]");
    }

    @Override
    public void showResult(int a) {
        System.out.println("Result - " + a);
    }
}
