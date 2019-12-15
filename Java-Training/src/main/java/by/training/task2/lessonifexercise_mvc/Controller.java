package by.training.task2.lessonifexercise_mvc;

public class Controller {
    void execute() {
        ViewLayer view = new ViewTerminal();
        ModelLayer model = new TerminalLayer();
        Date date = model.getTerminalDate();
        view.getEnteredDay(date);
        view.getNextDay(model.getResult(date));
    }
}
