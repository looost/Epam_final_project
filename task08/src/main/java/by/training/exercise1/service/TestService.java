package by.training.exercise1.service;

import by.training.exercise1.entity.Test1;

public class TestService {
    public void update(Test1 test1, Integer a, Integer b) {
        test1.setA(a);
        test1.setB(b);
    }
    public int calSum(Test1 test1) {
        return test1.getA()+test1.getB();
    }
    public int calMax(Test1 test1) {
        return test1.getA()>test1.getB()?test1.getA():test1.getB();
    }
}
