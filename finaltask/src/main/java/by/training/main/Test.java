package by.training.main;

public class Test {
    public static void main(String[] args) {

    }

}

@FunctionalInterface
interface CheckCar {
    boolean test(Test t);

    default boolean test1(Test t1) {
        return true;
    }
}