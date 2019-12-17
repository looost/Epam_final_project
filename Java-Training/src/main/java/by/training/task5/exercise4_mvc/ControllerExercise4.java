package by.training.task5.exercise4_mvc;

import by.training.view.ViewLayer;

public class ControllerExercise4 {
    void execute() {
        ViewLayer view = new ViewExercise4();
        ServiceExercise4 exercise4 = new ServiceExercise4();
        view.showTask();
        view.showResult(exercise4.isRising(exercise4.getArray()));
    }
}
