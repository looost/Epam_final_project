package by.training.task2.lessonifexercise_mvc;

public class ViewTerminal implements ViewLayer {
    @Override
    public void getEnteredDay(Date date) {
        System.out.println("Entered day - " + date.getDay() + "." + date.getMonth() + "." + date.getYear());
    }

    @Override
    public void getNextDay(Date date) {
        System.out.println("Next day - " + date.getDay() + "." + date.getMonth() + "." + date.getYear());
    }
}
