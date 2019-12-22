package by.training.lessonifexercise_mvc;

public class Controller {
    void execute() {
        ViewLayer view = new ViewTerminal();
        ModelLayer model = new TerminalLayer();
        Date date = model.getTerminalDate();
        view.showEnteredDay(date);
        view.showNextDay(model.getResult(date));
    }
}
