package by.training.exercise1.controller;

import by.training.exercise1.entity.Test1;
import by.training.exercise1.service.TestService;
import by.training.exercise1.view.UInterface;

public class Runner {
    public static void main(String[] args) {
        UInterface view = new UInterface();
        TestService service = new TestService();
        Test1 test1 = new Test1(13,6);
        view.show( "Сумма",service.calSum(test1));
    }
}
